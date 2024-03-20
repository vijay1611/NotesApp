package com.example.notesapp

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [NoteModel::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase(){

    abstract fun noteDao(): NoteDao

    companion object {
        private val LOCK = Any()
        val DB_NAME = "NoteDatabase.db"

        @Volatile
        public var dbInstance: NoteDatabase? = null

        fun getInstance(application: Application):NoteDatabase{
            synchronized(LOCK) {
                if (dbInstance == null) {
                    dbInstance = Room.databaseBuilder(application, NoteDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return dbInstance!!
        }
    }
}