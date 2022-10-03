package com.example.noteapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.noteapp.Room.DataNote
import com.example.noteapp.Room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class NoteViewModel(app : Application): AndroidViewModel(app) {
    var allNote: MutableLiveData<List<DataNote>>

    init {
        allNote = MutableLiveData()
        getAllNote()
    }

    fun getAllNoteObservers(): MutableLiveData<List<DataNote>> {
        return allNote
    }


    fun getAllNote() {
        GlobalScope.launch {
            val userDao = NoteDatabase.getInstance(getApplication())!!.noteDao()
            val listnote = userDao.getDataNote()
            allNote.postValue(listnote)
        }
    }

    fun insertNote(entity: DataNote){
        GlobalScope.async {
            val noteDao = NoteDatabase.getInstance(getApplication())!!.noteDao()
            noteDao.insertNote(entity)
            getAllNote()
        }

    }

    fun deleteNote(entity: DataNote) {
        GlobalScope.async {
            val noteDao = NoteDatabase.getInstance(getApplication())?.noteDao()
            noteDao?.deleteNote(entity)
            getAllNote()
        }
    }

    fun updateNote(entity: DataNote) {
        GlobalScope.async {
            val noteDao = NoteDatabase.getInstance(getApplication())?.noteDao()
            noteDao?.updateNote(entity)
            getAllNote()
        }
    }
}


