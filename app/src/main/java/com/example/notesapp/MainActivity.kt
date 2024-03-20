package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),ClickHandler {
    lateinit  var binding:ActivityMainBinding
    lateinit var noteDatabase: NoteDatabase
    lateinit var noteDao: NoteDao
    lateinit var adapter:NoteAdapter
    lateinit var vm : NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
//        val repository =
//            noteR(UserDatabase.getDatabaseInstance(applicationContext).userDao())
//
        noteDatabase = NoteDatabase.getInstance(application)
      //  val vm = ViewModelProvider(this)[NoteViewModel::class.java]
        val repository =
            NoteRepository(NoteDatabase.getInstance(application).noteDao())

        //  noteDatabase = NoteDatabase.getInstance(application)
        val viewModelFactory = NoteViewModelFactory(repository)
        vm = ViewModelProvider(this,viewModelFactory)[NoteViewModel::class.java]
        adapter = NoteAdapter(repository.getAllNotes(),this)


        vm.resultNoteModel.observe(this){
            if(it.isEmpty()){
                repository.getAllNotes()
            }else{
                adapter.setValue(it)
            }
        }

        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = adapter

        binding.floatingActionButton.setOnClickListener{
            startActivity(Intent(this,NotesActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        vm.callAllUsers()

    }

    override fun deleteNote(noteModel: NoteModel) {
        vm.deleteNote(noteModel)
    }

    override fun updateHandler(noteModel: NoteModel) {
        vm.updateUser(noteModel)
    }

}