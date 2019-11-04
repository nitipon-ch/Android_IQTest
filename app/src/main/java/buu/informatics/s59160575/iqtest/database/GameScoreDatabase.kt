package buu.informatics.s59160575.iqtest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GameScore::class], version = 1, exportSchema = false)
abstract class GameScoreDatabase : RoomDatabase()  {
    abstract val gameScoreDatabaseDao: GameScoreDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: GameScoreDatabase? = null

        fun getInstance(context: Context): GameScoreDatabase {
            synchronized(this) {

                var instance = INSTANCE

                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GameScoreDatabase::class.java,
                        "sleep_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}