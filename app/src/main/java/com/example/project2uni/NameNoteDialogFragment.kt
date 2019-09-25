package com.example.project2uni

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager


class NameNoteDialogFragment: DialogFragment(){

    private var editText: EditText? = null
    private var listener: OnTextListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = androidx.appcompat.app.AlertDialog.Builder(activity!!)
        builder.setTitle("Nome da Nota")
        builder.setPositiveButton("OK") { dialogInterface, i ->
            if (listener != null) {
                val text = editText!!.text.toString()
                listener!!.onSetTExt(text)
            }
        }

        builder.setNegativeButton("Cancelar") { dialogInterface, i -> dismiss() }

        val view = activity!!.layoutInflater.inflate(R.layout.activity_edit_note, null)
        editText = view.findViewById(R.id.note_text)
        builder.setView(view)
        return builder.create()
    }
    interface OnTextListener {
        fun onSetTExt(text: String)
    }
    companion object {
        fun show(fm: FragmentManager, listener: OnTextListener) {

            val dialog = NameNoteDialogFragment()
            dialog.listener = listener
            dialog.show(fm, "textDialog")

        }
    }
}