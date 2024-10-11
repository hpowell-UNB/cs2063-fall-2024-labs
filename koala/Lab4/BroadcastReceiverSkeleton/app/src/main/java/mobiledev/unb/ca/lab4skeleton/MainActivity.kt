package mobiledev.unb.ca.lab4skeleton

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.*
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    // Attributes for storing the file photo path
    private lateinit var currentPhotoPath: String
    private lateinit var imageFileName: String

    // Activity listeners
    private var cameraActivityResultLauncher: ActivityResultLauncher<Intent>? = null

    // Attributes for working with an alarm
    private var alarmManager: AlarmManager? = null
    private lateinit var alarmReceiverIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val cameraButton = findViewById<Button>(R.id.button)
        cameraButton.setOnClickListener { dispatchTakePhotoIntent() }

        // Ensure the permissions are set for posting notifications
        // TODO: Uncomment to enable permissions check
        // checkNotificationPermissions()

        // Register the activity listener
        setCameraActivityResultLauncher()
    }

    override fun onDestroy() {
        super.onDestroy()

        // TODO: Unregister the battery receivers to avoid memory leaks
    }

    /**
     * Checks for the appropriate permissions
     */
    private fun checkNotificationPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestRuntimePermissions()
            }
        }
    }

    /**
     * Grants the appropriate permissions
     */
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestRuntimePermissions() {
        ActivityCompat.requestPermissions(this, arrayOf(
            Manifest.permission.POST_NOTIFICATIONS
        ), Constants.NOTIFICATION_REQUEST_ID)
    }

    // Battery info receiver functions
    private val batteryInfoReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // TODO: Define the battery intent handers 
        }
    }

    // Camera helper functions
    private fun setCameraActivityResultLauncher() {
        cameraActivityResultLauncher = registerForActivityResult(
            StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                galleryAddPic()
            }
        }
    }

    private fun dispatchTakePhotoIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there is a camera activity to handle the intent
            try {
                // Set the File object used to save the photo
                var photoFile: File? = null
                try {
                    photoFile = createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    Log.e(TAG, "Exception found when creating the photo save file")
                }

                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI = FileProvider.getUriForFile(
                        this,
                        "mobiledev.unb.ca.lab4skeleton.provider",
                        photoFile
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)

                    // Calling this method allows us to capture the return code
                    cameraActivityResultLauncher!!.launch(takePictureIntent)
                }
            } catch (ex: ActivityNotFoundException) {
                Log.e(TAG, "Unable to load activity", ex)
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat(TIME_STAMP_FORMAT, Locale.getDefault()).format(Date())
        imageFileName = "IMG_" + timeStamp + "_"
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            imageFileName,  // prefix
            ".jpg",  // suffix
            storageDir // directory
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    private fun galleryAddPic() {
        Log.d(TAG, "Saving image to the gallery")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Android 10 and above
            mediaStoreAddPicToGallery()
        } else {
            // Pre Android 10
            mediaScannerAddPicToGallery()
        }
        Log.i(TAG, "Image saved!")
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun mediaStoreAddPicToGallery() {
        val name = imageFileName
        val bitmap = BitmapFactory.decodeFile(currentPhotoPath)

        val contentValues = ContentValues()
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "$name.jpg")
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)

        val resolver = contentResolver
        val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        saveImageToStream(bitmap, imageUri?.let { resolver.openOutputStream(it) })
    }

    private fun saveImageToStream(bitmap: Bitmap, outputStream: OutputStream?) {
        try {
            outputStream?.let {
                bitmap.compress(
                    Bitmap.CompressFormat.JPEG,
                    100,
                    it
                )
            }
        } catch (e: IOException) {
            Log.e(TAG, "Error saving the file ", e)
        }
    }

    private fun mediaScannerAddPicToGallery() {
        val file = File(currentPhotoPath)
        MediaScannerConnection.scanFile(this@MainActivity,
            arrayOf(file.toString()),
            arrayOf(file.name),
            null)
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val TIME_STAMP_FORMAT = "yyyyMMdd_HHmmss"
    }
}