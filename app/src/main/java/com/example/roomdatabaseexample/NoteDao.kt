package com.example.roomdatabaseexample

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    fun insert(note:Note)

    @Update
    fun update(note:Note)

    @Delete
    fun delete(note:Note)

    @Query("SELECT * FROM Note ORDER BY p DESC")
    fun  getliveData() : LiveData<List<Note>>

}