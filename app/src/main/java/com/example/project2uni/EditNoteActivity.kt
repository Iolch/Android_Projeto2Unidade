package com.example.project2uni

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView

class EditNoteActivity : AppCompatActivity(){
    private lateinit var notetext: EditText
    private lateinit var notename: String
    private lateinit var pageintent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        notetext = findViewById(R.id.note_text)
        pageintent = getIntent()

        if(pageintent.hasExtra("position")){
            notetext.setText(pageintent.getStringExtra("text"))
        }
    }
    fun cancel (view: View)
    {
        val it = Intent(this, MainActivity::class.java)
        startActivity(it)
    }
    fun onDialogOKPressed()
    {
        val it = Intent(this, MainActivity::class.java)
        val notename: EditText = findViewById(R.id.note_name)
        it.putExtra("text", notetext.text)
        it.putExtra("note_name", notename.text)
        startActivity(it)
    }

    fun save (view: View)
    {
        if(!pageintent.hasExtra("position")) {
            val newFragment = NameNoteDialogFragment(this)
            newFragment.show(supportFragmentManager, "notes")
        }else{
            val it = Intent(this, MainActivity::class.java)
            it.putExtra("text", notetext.text)
            it.putExtra("note_position", pageintent.getIntExtra("note_position", 0))
            startActivity(it)
        }
    }

}
