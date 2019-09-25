package com.example.project2uni

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*
import android.widget.Toast



//TURORIALLLLL MEDIUMMMM
//https://medium.com/collabcode/criando-lista-com-recyclerview-no-android-com-kotlin-85cb76f3775d


class NotesListAdapter (private val notes: ArrayList <Note>, private val context: Context): RecyclerView.Adapter<NotesListAdapter.ViewHolder>() {

    var onItemClick: ((Note) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val noteview = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)

        return ViewHolder(noteview, context)
    }

    override fun getItemCount(): Int {
        return notes.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.title.text = note.title
        holder.description.text = note.description
    }

    inner class ViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView){

        val title = itemView.note_item_title
        val description = itemView.note_item_description
        init {
            itemView.setOnClickListener()
            {
                (context as MainActivity).editNote(layoutPosition, description.text.toString())
            }
        }
    }
}