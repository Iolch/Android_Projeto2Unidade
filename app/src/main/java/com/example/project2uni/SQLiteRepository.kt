package com.example.project2uni

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class SQLiteRepository(context: Context): NoteRepository {
    private val helper: NoteSqlHelper = NoteSqlHelper(context)

    private fun insert(note: Note): Long
    {
        val db = helper.writableDatabase
        val cv = ContentValues().apply {
            put(COLOUMN_NAME, note.title)
            put(COLOUMN_TEXT, note.description)
        }
        val id = db.insert(TABLE_NAME, null, cv)
        if(id != 1L){
            note.id = id;
        }
        db.close()
//        return id;
    }

    private fun update(note: Note)
    {
        val db = helper.writableDatabase
        val cv = ContentValues().apply {
            put(COLOUMN_NAME, note.title)
            put(COLOUMN_TEXT, note.description)
        }
        db.update(TABLE_NAME, cv, "$COLOUMN_ID = ?", arrayOf(note.id.toString()))
        db.close()
//        return note.id;
    }

    override fun save(note: Note){
        if(note.id == 0L)
        {
//            return insert(note)
            insert(note)
        }else{
            update(note)
//            return update(note)
        }
    }

    override fun remove(vararg notes: Note) {
        val db = helper.writableDatabase
        for(note in notes){
            db.delete(TABLE_NAME, "$COLOUMN_ID = ?", arrayOf(note.id.toString()))
        }
        db.close()
    }

    override fun noteById(id: Long, callback: (Note?) -> Unit) {
        val db = helper.writableDatabase
        val sql = "SELECT * FROM $TABLE_NAME WHERE $COLOUMN_ID = ?"
        val cursor = db.rawQuery(sql, arrayOf(id.toString()))
        val note = if(cursor.moveToNext()) noteFromCursor(cursor) else null
        callback(note)
    }

     fun selectAll(): ArrayList<Note>{

        val db = helper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val notes = ArrayList<Note>()
        while (cursor.moveToNext()){
            val note = noteFromCursor(cursor)
            notes.add(note)
        }
        cursor.close()
        db.close()
        return notes
    }
    override fun search(term: String, callback: (List<Note>?) -> Unit) {
        var sql = "SELECT * FROM $TABLE_NAME"
        var args: Array<String>? = null

        if(term.isNotEmpty()){
            sql += "WHERE $COLOUMN_NAME LIKE ?"
            args = arrayOf("%$term%")
        }

        sql += "ORDER BY $COLOUMN_NAME"
        val db = helper.readableDatabase
        val cursor = db.rawQuery(sql, args)
        val notes = ArrayList<Note>()
        while (cursor.moveToNext()){
            val note = noteFromCursor(cursor)
            notes.add(note)
        }
        cursor.close()
        db.close()
        callback(notes)
    }

    private  fun noteFromCursor(cursor: Cursor): Note{
        val id = cursor.getLong(cursor.getColumnIndex(COLOUMN_ID))
        val name = cursor.getString(cursor.getColumnIndex(COLOUMN_NAME))
        val text = cursor.getString(cursor.getColumnIndex(COLOUMN_TEXT))
        return Note(name, text, id)
    }
}
