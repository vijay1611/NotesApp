package com.example.notesapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesData")
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var name:String,
    var desc : String
)