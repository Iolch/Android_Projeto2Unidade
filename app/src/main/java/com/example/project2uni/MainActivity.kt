package com.example.project2uni

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), OnDeleteListener, OnEditListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var notes: ArrayList<Note> = arrayListOf(Note("Nota 1", "Esta é a primeira nota!"), Note("Nota 2", "Esta é a segunda nota!"))
    private lateinit var listnotes: ArrayList<Note>;
    private val EDIT: Int = 1;
    private val STORE: Int = 2;
    private val database: SQLiteRepository = SQLiteRepository(this);
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
                updateNote(data!!.getIntExtra("note_position", 0), data!!.getStringExtra("text"))
            }
            if(requestCode == STORE){
                storeNote(data!!.getStringExtra("note_name"), data!!.getStringExtra("text"))
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        database.save(Note("teste", "funfeeee"))
        listnotes = database.selectAll();
        viewManager = LinearLayoutManager(this)
        viewAdapter = NotesListAdapter(listnotes, this, this, this)


        recyclerView = findViewById<RecyclerView>(R.id.note_list_recyclerview).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun deleteNote(index: Int) {
        database.remove(listnotes[index])
        listnotes.removeAt(index)
        viewAdapter.notifyDataSetChanged()
    }
    private fun storeNote(name: String, text: String)
    {
        listnotes.add(Note(name, text))
        val newposition = listnotes.size - 1;
        database.save(listnotes[newposition])
//        val id = database.save(listnotes[newposition])
//        listnotes[newposition].id = id;
        viewAdapter.notifyItemInserted(newposition)
        viewAdapter.notifyDataSetChanged()
    }

    fun updateNote(position: Int, text: String) {

        val edtnote:Note = Note(listnotes[position].title, text, listnotes[position].id)
        listnotes[position] = edtnote
        database.save(edtnote)
//        val id = database.save(edtnote)
        viewAdapter.notifyDataSetChanged()
    }
    override fun editNote(position: Int, text: String)
    {
        val it = Intent(this, EditNoteActivity::class.java)
        it.putExtra("note_position", position)
        it.putExtra("text", text)
        startActivityForResult(it, EDIT)
    }
}
