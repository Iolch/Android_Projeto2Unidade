package com.example.project2uni

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class NotesListActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = note_list_recyclerview

    }
    private fun notes(): List<Note> {
        return listOf(
            Note("Leitura",
                "Livro de Kotlin com Android"),
            Note("Pesquisa",
                "Como posso melhorar o código dos meus projetos"),
            Note("Estudo",
                "Como sincronizar minha App com um Web Service"))
    }
}