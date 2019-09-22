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
    private val pageintent = getIntent();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)
        if(pageintent.hasExtra("position")){
            val txt: TextView = findViewById(R.id.note_text);
            txt.setText(pageintent.getStringExtra("text"))
        }
    }
    fun cancel (view: View)
    {
        val it = Intent(this, MainActivity::class.java)
        startActivity(it)
    }
    fun onDialogOKPressed(notename: String)
    {
        val it = Intent(this, MainActivity::class.java)
        if(notetext.text != null) it.putExtra("text", notetext.text)
        it.putExtra("note_name", notename)
        startActivity(it)

    }
    fun save (view: View)
    {
        if(!pageintent.hasExtra("position")) {
            val newFragment = NameNoteDialogFragment()
            newFragment.show(supportFragmentManager, "missiles")
        }else{
            val it = Intent(this, MainActivity::class.java)
            it.putExtra("text", notetext.text)
            it.putExtra("note_position", pageintent.getIntExtra("note_position", -1))
            startActivity(it)
        }
    }

}
