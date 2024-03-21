package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
        adapter.notifyDataSetChanged()


        vm.resultNoteModel.observe(this){
                adapter.setValue(it)
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
        alert(noteModel)
    }

    override fun updateHandler(noteModel: NoteModel) {
        vm.updateUser(noteModel)
    }

    fun alert(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(this)

        // Set title and message
        builder.setTitle("Confirm Delete")
            .setMessage("This is an alert message!")

        // Set positive button and its OnClickListener
        builder.setPositiveButton("Delete") { dialog, which ->
            vm.deleteNote(noteModel)
            Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show()
            //adapter.notifyDataSetChanged()
        }

        // Set negative button and its OnClickListener
        builder.setNegativeButton("Cancel") { dialog, which ->
            return@setNegativeButton
        }

        // Create and show the AlertDialog
        val alertDialog = builder.create()
        alertDialog.show()

    }



}