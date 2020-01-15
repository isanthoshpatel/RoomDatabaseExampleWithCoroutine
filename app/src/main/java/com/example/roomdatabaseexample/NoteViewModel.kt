package com.example.roomdatabaseexample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NoteViewModel(a: Application) : AndroidViewModel(a) {

    //Coroutine Job i.e, launch returntype!! and Job must be canceled!! on viewmodel onCleared()
    private var parentJOb = Job()

    //CoroutineContext = job + dispacher
    private val coroutineContext: CoroutineContext
        get() = parentJOb + Dispatchers.Main

    //CoroutineScope(with coroutineContext)
    private val scope = CoroutineScope(coroutineContext)

    //all notes
    private val repository: NoteRepository
    val allNotes: LiveData<List<Note>>

    //database,dao->repository->allnotes
    init {
        val n = NoteDatabase.getDatabase(a).getDao()
        repository = NoteRepository(n)
        allNotes = repository.allNotes

    }

    //notes form MainActivity->repository.insert note!!
    fun insert(note:Note) = scope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    override fun onCleared() {
        super.onCleared()
        parentJOb.cancel()
    }
}