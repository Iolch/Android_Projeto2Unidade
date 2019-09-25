package com.example.project2uni

import android.app.Activity
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
    private val EDIT: Int = 1;
    private val STORE: Int = 2;
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val it = Intent(this, EditNoteActivity::class.java)
        startActivityForResult(it, STORE)

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == EDIT){
                updateNote(data!!.getIntExtra("position", 0), data!!.getStringExtra("text"))
            }
            if(requestCode == STORE){
                storeNote(data!!.getStringExtra("note_name"), data!!.getStringExtra("text"))
            }
        }
        if(resultCode == Activity.RESULT_CANCELED){
            updateNote(0, "1")
        }
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
        Log.e("teste", "vo guarda")
        notes.add(Note(name, text))
    }
    private fun updateNote(position:Int, text:String)
    {
            notes.get(position).description = text
    }
    fun editNote(position: Int, text: String)
    {

        Log.e("teste", "vo alterar")
        val it = Intent(this, EditNoteActivity::class.java)
        it.putExtra("position", position)
        it.putExtra("text", text)

        startActivityForResult(it, EDIT)
    }
}
