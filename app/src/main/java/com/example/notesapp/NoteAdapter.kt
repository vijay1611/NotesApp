package com.example.notesapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView


class NoteAdapter(
    private var note: List<NoteModel>,private val clickHandler: ClickHandler
) : RecyclerView.Adapter<NoteAdapter.UserViewModel>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewModel {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_row_item, parent, false)
        return UserViewModel(view)
    }

    override fun getItemCount() = note.size

    override fun onBindViewHolder(holder: UserViewModel, position: Int) {
        holder.bind(note[position])
        holder.username.text = note[position].name
        holder.useremail.text = note[position].desc

        holder.updateBtn.setOnClickListener{
        clickHandler.updateHandler(note[position])
           val intent = Intent(holder.itemView.context,UpdateActivity::class.java)
            intent.putExtra("id",note[position].id)
            intent.putExtra("name",note[position].name)
            intent.putExtra("desc",note[position].desc)
            holder.itemView.context.startActivity(intent)
        }
        holder.deleeIcon.setOnClickListener{
            clickHandler.deleteNote(note[position])
           //  Toast.makeText(this,"working",Toast.LENGTH_SHORT).
        }
    }

    fun setValue(it:List<NoteModel>){
        if(it !=null){
            note =it
        }
        notifyDataSetChanged()
    }

    inner class UserViewModel(userview: View) : RecyclerView.ViewHolder(userview) {

         val username: TextView = userview.findViewById(R.id.name)
         val useremail: TextView = userview.findViewById(R.id.desc)
        var updateBtn = userview.findViewById<ImageView>(R.id.updateIcon)
        var deleeIcon = userview.findViewById<ImageView>(R.id.deleteIcon)


        fun bind(user: NoteModel) {
            username.text = user.name
            useremail.text = user.desc

        }

        fun update(newData:List<NoteModel>){
            note = newData
        }
    }
}
interface ClickHandler{
    fun deleteNote(noteModel: NoteModel)
    fun updateHandler(noteModel: NoteModel)
}
