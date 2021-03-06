package com.example.notepad.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.notepad.R
import com.example.notepad.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_notepad.*
import kotlinx.android.synthetic.main.content_notepad.*

class NoteActivity : AppCompatActivity() {

    private val noteViewModel: NoteViewModel by viewModels() // Use viewModels because of lazy

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notepad)
        setSupportActionBar(toolbar)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        fab.setOnClickListener {
            val intent = Intent(this, EditNoteActivity::class.java)
            intent.putExtra(EditNoteActivity.EXTRA_NOTE, noteViewModel.note.value)
            startActivity(intent)
        }
    }

    private fun initViewModel() {
        noteViewModel.note.observe(this, Observer { note ->
            if (note != null) {
                tvTitle.text = note.noteTitle
                tvLastUpdated.text = getString(R.string.last_updated, note.lastUpdated.toString())
                tvNote.text = note.noteText
            }
        })
    }

}
