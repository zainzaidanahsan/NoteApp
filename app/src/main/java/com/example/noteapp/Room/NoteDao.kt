package com.example.noteapp.Room

import androidx.room.*
@Dao
interface NoteDao{
    @Insert
    fun insertNote(note: DataNote)

    @Query("SELECT * FROM DataNote ORDER BY id DESC ")
    fun getDataNote() : List<DataNote>

    @Delete
    fun deleteNote(note: DataNote)

    @Update
    fun updateNote(note: DataNote)

}