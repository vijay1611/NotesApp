package com.example.notesapp

class NoteRepository(
    private val noteDao: NoteDao
){
    suspend fun insertNote(note: NoteModel){
        noteDao.insertNote(note)
    }

     fun getAllNotes(): List<NoteModel>{
        return noteDao.getAllNotes()
    }

    suspend fun updateNote(note: NoteModel) {
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: NoteModel) {
        noteDao.deleteNote(note)
    }

//    suspend fun deleteAll() {
//        noteDao.deleteAll()
//    }
}
