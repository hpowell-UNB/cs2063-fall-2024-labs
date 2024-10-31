package mobiledev.unb.ca.roompersistencelab.repository

import mobiledev.unb.ca.roompersistencelab.db.AppDatabase.Companion.getDatabase
import android.app.Application
import mobiledev.unb.ca.roompersistencelab.dao.ItemDao

class ItemRepository(application: Application) {
    private val itemDao: ItemDao? = getDatabase(application).itemDao()

    // TODO Add query specific methods
    //  HINT 1:
    //   The insert operation will use the Runnable interface as there are no return values
    //  HINT 2:
    //   The search operation will use the Callable interface with Future as there are return values
    //  HINT 3:
    //    LiveData does not work as well for this; consider using an object list to return the search results
    //  See the example project file at
    //  https://github.com/hpowell-UNB/cs2063-fall-2024-examples/blob/main/data_storage/RoomPersistenceLibraryDemo/app/src/main/java/mobiledev/unb/ca/roompersistencetest/repository/ItemRepository.kt
    //  to see examples of how to work with the Executor Service along with Runnables and Callables
}