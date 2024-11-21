package mobiledev.unb.ca.graphicslab

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit
import kotlin.math.pow
import kotlin.math.sqrt

// Extension functions for the screen display size
val Context.displayWidth: Int
    get() = resources.displayMetrics.widthPixels

val Context.displayHeight: Int
    get() = resources.displayMetrics.heightPixels

class BubbleView(context: Context) : View(context) {
    // Listener object to submit requests back to the parent activity
    private lateinit var listener: BubbleListener

    private val painter = Paint()
    private var scheduledFuture: ScheduledFuture<*>? = null
    private var scaledBitmap: Bitmap? = null

    // location and direction of the bubble
    private var xPosition: Float = 0.0f
    private var yPosition: Float = 0.0f
    private val radius: Float

    // Speed of bubble
    private var mDx: Float = 0.0f
    private var mDy: Float = 0.0f

    // Rotation and speed of rotation of the bubble
    private var rotate: Long = 0
    private var speedOfRotation: Long = 0

    init {
        // Creates the bubble bitmap for this BubbleView
        val scaledBitmapSize = createScaledBitmap()

        // Radius of the Bitmap
        radius = (scaledBitmapSize / 2).toFloat()

        // Set the BubbleView's speed and direction
        setSpeedAndDirection()

        // Set the BubbleView's rotation
        setRotation()

        // Smooth out the edges
        painter.isAntiAlias = true
    }

    private fun setRotation() {
        // Set value for speedOfRotation in the range of [1..5]
        speedOfRotation = generateRandomNumberInRange(1, 5).toLong()
    }

    private fun setSpeedAndDirection() {
        // TODO
        //  Set mDx and mDy to indicate movement direction and speed
        //  Limit speed in the x and y direction to [-3..3] pixels per movement
    }

    private fun createScaledBitmap(): Int {
        // Retrieve the original bitmap resource
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.b64)

        //  Set scaled bitmap size (scaledBitmapSize) in range [2..4] * BITMAP_SIZE
        val randomSize = generateRandomNumberInRange(2, 4)
        val scaledBitmapSize = BITMAP_SIZE * randomSize

        // TODO
        //  Set the scaled bitmap (scaledBitmap) value using size set above

        //  Return the scaledBitmapSize
        return scaledBitmapSize
    }

    fun setListener(listener: BubbleListener) {
        this.listener = listener
    }

    // Start moving the BubbleView & updating the display
    fun startMovement(startXPosition: Float,
                      startYPosition: Float) {
        // Adjust position to center the bubble under user's finger
        xPosition = startXPosition - radius
        yPosition = startYPosition - radius

        // Creates a WorkerThread
        val executor = Executors.newScheduledThreadPool(1)

        // Execute the run() in Worker Thread every REFRESH_RATE milliseconds
        // Save reference to this job in mMoverFuture
        scheduledFuture = executor.scheduleWithFixedDelay({
            // TODO
            //  Implement movement logic.
            //  Each time this method is run the BubbleView should
            //  move one step. (Use moveWhileOnScreen() to do this.)
            //  If the BubbleView exits the display, stop the BubbleView's
            //  Worker Thread. (Use stopMovement() to do this.) Otherwise,
            //  request that the BubbleView be redrawn.

        }, 0, REFRESH_RATE.toLong(), TimeUnit.MILLISECONDS)
    }

    // Returns true if the BubbleView intersects position (x,y)
    @Synchronized
    fun intersects(x: Float?, y: Float?): Boolean {
        val centerX = xPosition + radius
        val centerY = yPosition + radius

        // TODO
        //  Return true if the BubbleView intersects position (x,y)

        // Remove this when you're done the above TODO
        return true
    }

    // Stops the Bubble's movement
    fun stopMovement() {
        if (null != scheduledFuture) {
            if (!scheduledFuture!!.isDone) {
                scheduledFuture!!.cancel(true)
            }

            // TODO
            //  Remove the bubble view from tha parent activity
            //  HINT: Use the listener onBubbleViewRemoved() function to do this
        }
    }

    // Change the Bubble's speed and direction
    @Synchronized
    fun deflect(velocityX: Float, velocityY: Float) {
        mDx = velocityX / REFRESH_RATE
        mDy = velocityY / REFRESH_RATE
    }

    // Draw the Bubble at its current location
    @Synchronized
    override fun onDraw(canvas: Canvas) {
        // TODO
        //  Save the canvas

        // TODO
        //  Increase the rotation of the original image by the
        //  value of speedOfRotation

        // TODO
        //  Rotate the canvas by current rotation
        //  Hint - Rotate around the bubble's center, not its position

        // TODO
        //  Draw the bitmap at it's new location

        // TODO
        //  Restore the canvas
    }

    // Returns true if the BubbleView is still on the screen after the move
    // operation
    @Synchronized
    private fun moveWhileOnScreen(): Boolean {
        // Move the BubbleView
        xPosition += mDx
        yPosition += mDy

        return xPosition <= context.displayWidth &&
                xPosition + radius * 2 >= 0 &&
                yPosition <= context.displayHeight &&
                yPosition + radius * 2 >= 0
    }

    private fun generateRandomNumberInRange(min: Int, max: Int): Int {
        return (min..max).random()
    }

    companion object {
        private const val BITMAP_SIZE = 64
        private const val REFRESH_RATE = 40
    }
}