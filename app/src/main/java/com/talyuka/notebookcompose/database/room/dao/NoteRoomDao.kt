package com.talyuka.notebookcompose.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.talyuka.notebookcompose.model.Note

@Dao
interface NoteRoomDao {
    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): LiveData<List<Note>>
    @Insert
    fun addNote(note: Note)
    @Update
    fun updateNote(note: Note)
    @Delete
    fun deleteNote(note: Note)
}