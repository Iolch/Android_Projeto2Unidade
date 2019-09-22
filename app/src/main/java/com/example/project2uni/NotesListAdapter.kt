package com.example.project2uni

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*

//TURORIALLLLL MEDIUMMMM
//https://medium.com/collabcode/criando-lista-com-recyclerview-no-android-com-kotlin-85cb76f3775d


class NotesListAdapter (private val notes: List <Note>, private val context: Context): RecyclerView.Adapter<NotesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val noteview = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(noteview)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.title.text = note.title
        holder.description.text = note.description

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val title = itemView.note_item_title
        val description = itemView.note_item_description

    }
}