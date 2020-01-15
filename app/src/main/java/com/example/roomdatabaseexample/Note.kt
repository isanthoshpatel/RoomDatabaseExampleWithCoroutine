package com.example.roomdatabaseexample

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity
class Note(@PrimaryKey var title:String, var desc:String,var p:Int)