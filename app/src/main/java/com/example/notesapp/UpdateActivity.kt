package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    lateinit var binding : ActivityUpdateBinding
    lateinit var vm : NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var name  = intent.getStringExtra("name")
        var desc = intent.getStringExtra("desc")
        var id =intent.getIntExtra("id",0)

     binding.name.setText(name)
      binding.desc.setText(desc)

        val repository =
            NoteRepository(NoteDatabase.getInstance(application).noteDao())

        //  noteDatabase = NoteDatabase.getInstance(application)
        val viewModelFactory = NoteViewModelFactory(repository)
        vm = ViewModelProvider(this,viewModelFactory)[NoteViewModel::class.java]

        binding.btnSave.setOnClickListener {

            vm.updateUser(
                NoteModel(
                    id = id,
                    name = binding.name.text.toString(),
                    desc = binding.desc.text.toString()
                )
            )

            Toast.makeText(this,"Updated successfully",Toast.LENGTH_SHORT).show()

            finish()

        }






    }
}