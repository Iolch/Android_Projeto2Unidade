package com.example.project2uni

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class NameNoteDialogFragment(context: Context): DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            builder.setView(inflater.inflate(R.layout.dialog_name_note, null))
                    .setPositiveButton("Ok",
                        DialogInterface.OnClickListener { dialog, id ->
                            (context as EditNoteActivity).onDialogOKPressed()
                            dismiss()
                        })
                    .setTitle("Nome da Nota")
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}