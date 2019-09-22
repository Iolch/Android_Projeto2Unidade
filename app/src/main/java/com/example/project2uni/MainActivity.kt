package com.example.project2uni

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var notes: List <Note> = listOf(Note("teste", "123123"))

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        val it:Intent = getIntent()
        if(it.hasExtra("note_name") && it.hasExtra("text")) storeNote(it.getStringExtra("note_name"), it.getStringExtra("text"))
        if(it.hasExtra("position")) updateNote(it.getIntExtra("position",-1), it.getStringExtra("text"))
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
            var i:Int = notes.size
//            notes.set(i, Note(name, text))
    }
    private fun updateNote(position:Int, text:String)
    {
            notes.get(position).description = text
    }
}
