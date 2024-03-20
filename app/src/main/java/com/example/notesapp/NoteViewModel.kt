package com.example.notesapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.math.log


class NoteViewModel( private val noteRepository: NoteRepository):ViewModel() {

    private val _resultNoteModel : MutableLiveData<List<NoteModel>> = MutableLiveData()
    val resultNoteModel : LiveData<List<NoteModel>> = _resultNoteModel

    fun callAllUsers()  {
        viewModelScope.launch {
           _resultNoteModel.postValue(noteRepository.getAllNotes().toMutableList())
        }
    }

    fun insertUser(note: NoteModel) {
        viewModelScope.launch {
           noteRepository.insertNote(note)
            Log.e("data*", note.toString() )
        }
    }
    fun updateUser(note: NoteModel) {
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    }
    fun deleteNote(note: NoteModel) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
            callAllUsers()
        }
    }
}



//class UserViewModel(
//    private val userRepository: UserRepository
//) : ViewModel() {
//    fun getAllUsers(): List<User> {
//        viewModelScope.launch {
//            return userRepository.getAllUsers()
//        }
//    }
//
//    fun insertUser(user: User) {
//        viewModelScope.launch {
//            userRepository.insertUser(user)
//        }
//    }
//
//    fun updateUser(user: User) {
//        viewModelScope.launch {
//            userRepository.updateUser(user)
//        }
//    }
//
//    fun deleteUser(user: User) {
//        viewModelScope.launch {
//            userRepository.deleteUser(user)
//        }
//    }
//
//    fun deleteAll() {
//        viewModelScope.launch {
//            userRepository.deleteAll()
//        }
//    }
