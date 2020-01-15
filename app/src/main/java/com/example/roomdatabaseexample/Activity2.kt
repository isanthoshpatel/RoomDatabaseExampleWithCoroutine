package com.example.roomdatabaseexample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.isEmpty
import kotlinx.android.synthetic.main.activity_2.*

class Activity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        np.minValue = 1
        np.maxValue = 2

        var i = intent
        if (i.hasExtra("addnote")) {
            var title = i.getStringExtra("addnote")
            setTitle(title)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity2_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.savenote -> saveNote()
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    fun saveNote() {
        var title = et_title.text.toString().trim()
        var desc = et_desc.text.toString().trim()
        var p = np.value

        if (title.isEmpty() || desc.isEmpty()) {
            Toast.makeText(this, "Don't leave fields empty!", Toast.LENGTH_LONG).show()
            return
        }

        var i = Intent()
        i.putExtra("title", title)
        i.putExtra("desc", desc)
        i.putExtra("p", p)
        if (intent.hasExtra("addnote")) {
            setResult(Activity.RESULT_OK, i)
        }
        finish()
    }

}
