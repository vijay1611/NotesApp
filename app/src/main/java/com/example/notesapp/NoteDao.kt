package com.example.notesapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import androidx.room.Update

@Dao
interface NoteDao {
    @Query("Select * from notesData")
     fun getAllNotes(): List<NoteModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(noteItem: NoteModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(noteItemList: List<NoteModel>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(noteItem: NoteModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(noteItemList: List<NoteModel>)

    @Delete
    suspend fun deleteNote(noteItem: NoteModel)

//    @Delete
//    suspend fun deleteUser(noteItem: NoteModel)




}


