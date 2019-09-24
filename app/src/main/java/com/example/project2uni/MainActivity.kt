package com.example.project2uni

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var notes: ArrayList <Note> = arrayListOf(Note("teste", "123123"))

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {

        val it:Intent = getIntent()
        if(it.hasExtra("note_name") && it.hasExtra("text")) storeNote(it.getStringExtra("note_name"), it.getStringExtra("text"))
        if(it.hasExtra("position")) updateNote(it.getIntExtra("position",0), it.getStringExtra("text"))
        super.onCreate(savedInstanceState, persistentState)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val it = Intent(this, EditNoteActivity::class.java)
        startActivity(it)

        return super.onOptionsItemSelected(item)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = NotesListAdapter(notes, this)


        recyclerView = findViewById<RecyclerView>(R.id.note_list_recyclerview).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }


    }
    private fun storeNote(name: String, text: String)
    {
        notes.add(Note(name, text))
    }
    private fun updateNote(position:Int, text:String)
    {
            Log.e("teste", "cheguqieee")
            notes.get(position).description = text
    }
    fun editNote(position: Int, text: String)
    {
        val it = Intent(this, EditNoteActivity::class.java)
        it.putExtra("position", position)
        it.putExtra("text", text)

        startActivity(it)
    }
}
