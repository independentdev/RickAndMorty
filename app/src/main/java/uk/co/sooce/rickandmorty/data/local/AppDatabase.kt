package uk.co.sooce.rickandmorty.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uk.co.sooce.rickandmorty.data.local.dao.CharacterDao
import uk.co.sooce.rickandmorty.data.model.MovieCharacter

@Database(entities = [MovieCharacter::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {

        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "characters")
                .fallbackToDestructiveMigration()
                .build()
    }
}