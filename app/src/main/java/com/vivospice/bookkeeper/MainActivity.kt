package com.vivospice.bookkeeper

/**
 * https://developer.android.com/reference/androidx/room/package-summary
 */

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.IllegalArgumentException

class MainActivity : AppCompatActivity() {

    private val bookViewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val intent = Intent(this, NewBookActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }


        //bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)
        //val model: MyViewModel by viewModels()
        //        model.getUsers().observe(this, Observer<List<User>>{ users ->
        //            // update UI
        //        })

       // bookViewModel.?????.observe(this, Observer<String> {  })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            if( data == null) throw IllegalArgumentException ("data is null for author, book or something like that")
                val id = UUID.randomUUID().toString()
                val authorName = data.getStringExtra(NewBookActivity.NEW_AUTHOR)
                val bookName = data.getStringExtra(NewBookActivity.NEW_BOOK)

            if (authorName == null || bookName == null) throw IllegalArgumentException ("authorName or bookName is null")
                val book = Book(id, authorName, bookName)


            bookViewModel.insert(book)

            Toast.makeText(applicationContext, "Saved", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(applicationContext, "Book not saved", Toast.LENGTH_LONG).show()
        }

    }

    companion object {
        private const val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
    }

}
