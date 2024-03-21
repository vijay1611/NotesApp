package com.example.notesapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.databinding.ActivityNotesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesActivity : AppCompatActivity() {
    lateinit  var binding: ActivityNotesBinding
    lateinit var noteDatabase: NoteDatabase
    lateinit var noteDao: NoteDao
    lateinit var repository:NoteRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_notes)
         repository =
            NoteRepository(NoteDatabase.getInstance(application).noteDao())

      //  noteDatabase = NoteDatabase.getInstance(application)
        val viewModelFactory = NoteViewModelFactory(repository)
        val vm = ViewModelProvider(this,viewModelFactory)[NoteViewModel::class.java]

//        var name = binding.name.text.toString()
//        var desc = binding.desc.text.toString()

        binding.btnSave.setOnClickListener{
//            vm.resultNoteModel.observe(this){it->
//                vm.insertUser(NoteModel(name=binding.name.text.toString(), desc =  binding.desc.text.toString()))
            getData()
         //   finish()
            }



    }
    fun Activity.hideKeyboard() {
        val view: View? = currentFocus
        view?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
    fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.insertNote(
                NoteModel(
                    name = binding.name.text.toString(),
                    desc = binding.desc.text.toString()
                )
            )

        }
        CoroutineScope(Dispatchers.IO).launch {
            var data2 = repository.getAllNotes()
            Log.e("get**", data2.toString())

        }
        hideKeyboard()
        Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
        finish()
    }
        }


