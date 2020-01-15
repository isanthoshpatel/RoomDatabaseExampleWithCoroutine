package com.example.roomdatabaseexample

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao:NoteDao) {

    var allNotes:LiveData<List<Note>> = noteDao.getliveData()

    @WorkerThread
    suspend fun insert(note:Note){
        noteDao.insert(note)
    }

}