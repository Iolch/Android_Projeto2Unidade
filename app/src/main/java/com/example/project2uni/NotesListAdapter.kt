package com.example.project2uni

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    }
}