package com.example.project2uni

interface NoteRepository {
    fun save(note: Note);
    fun remove(vararg notes: Note);
    fun noteById(id: Long, callback: (Note?) -> Unit);
    fun search(term: String, callback: (List<Note>?) -> Unit);

}