package at.rbratschun.mse.movies.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private var instance: MovieDatabase? = null
        private val LOG_TAG = MovieDatabase::class.simpleName

        fun getInstance(context: Context): MovieDatabase? {
            Log.d(LOG_TAG, "getting movie database instance")
            synchronized(MovieDatabase::class) {
                if (instance == null) {
                    Log.d(LOG_TAG, "creating new instance")
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        "movies.db"
                    )
                        .build()
                } else {
                    Log.d(LOG_TAG, "using existing instance")
                }
            }
            return instance
        }
    }
}