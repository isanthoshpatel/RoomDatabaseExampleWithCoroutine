package com.example.roomdatabaseexample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ExampleAdaptor.MyInterface {
lateinit var vm:NoteViewModel
    lateinit var list:MutableList<Note>
    lateinit var adaptor: ExampleAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list = mutableListOf()


        //viewmodel instance
        vm = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        //observe list items from livedata and add them to Activity list
        vm.allNotes.observe(this, Observer {l ->
            l.forEach {
                list.add(it)
            }
            Toast.makeText(this,"hi....",Toast.LENGTH_LONG).show()
        })

        rv.setHasFixedSize(true)
        rv.layoutManager = GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false)
        adaptor = ExampleAdaptor(this,list)
        rv.adapter = adaptor


        fab.setOnClickListener {
            var i = Intent(this,Activity2::class.java)
            i.putExtra("addnote","AddNote")
            startActivityForResult(i,1)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            var title = data?.getStringExtra("title")
            var desc = data?.getStringExtra("desc")
            var p = data?.getIntExtra("p",1)
            vm.insert(Note(title!!,desc!!,p!!))
            adaptor.notifyDataSetChanged()


        }
    }

    override fun myWork(p: Int) {
        //update the note
    }

}
