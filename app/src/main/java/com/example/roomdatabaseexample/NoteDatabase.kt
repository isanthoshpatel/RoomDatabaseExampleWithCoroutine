package com.example.roomdatabaseexample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(c: Context): NoteDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    c.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).fallbackToDestructiveMigration()
                    // .addCallback(NoteCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
/*
        private class NoteCallback(private val scope:CoroutineScope):RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE.let {
                    scope.launch {
                        var d = INSTANCE?.getDao()
                        d?.insert(Note( "title1","desc1"))
                        d?.insert(Note("title2","desc2"))
                    }
                }
            }
        }
    }

} */