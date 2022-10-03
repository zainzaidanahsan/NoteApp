package com.example.noteapp.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class DataNote(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    @ColumnInfo(name = "tittle")
    var tittle : String,
    @ColumnInfo(name = "note")
    var note : String
): Serializable