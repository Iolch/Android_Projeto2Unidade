package com.example.project2uni

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.TextView

class EditNoteActivity : AppCompatActivity() {

    private val notetext: TextView = findViewById(R.id.note_text);
    private lateinit var notename: String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)
    }
    fun cancel (view: View)
    {
        val it = Intent(this, MainActivity::class.java)
        startActivity(it)
    }
    fun onDialogOKPressed(notename: Editable)
    {
        val it = Intent(this, MainActivity::class.java)
        if(notetext.text != null) it.putExtra("text", notetext.text)
        it.putExtra("note_name", notename)
        startActivity(it)

    }
    fun save (view: View)
    {
        val newFragment = NameNoteDialogFragment()
        newFragment.show(supportFragmentManager, "missiles")
    }

}
