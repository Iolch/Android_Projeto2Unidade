package com.example.project2uni

import android.app.Activity
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
        val it = Intent()
        setResult(Activity.RESULT_CANCELED, it)
        finish()
    }


    fun save (view: View)
    {
        if(!pageintent.hasExtra("position")) {

            NameNoteDialogFragment.show(supportFragmentManager, object : NameNoteDialogFragment.OnTextListener {
                override fun onSetTExt(text: String) {
                    val it = Intent();
                    it.putExtra("text", notetext.text.toString())
                    it.putExtra("note_name", text)
                    setResult(Activity.RESULT_OK, it)
                    finish()

                }
            })

        }else{
            val it = Intent()
            it.putExtra("text", notetext.text.toString())
            it.putExtra("note_position", pageintent.getIntExtra("note_position", 0))
            setResult(Activity.RESULT_OK, it)
            finish()
        }
    }

}
