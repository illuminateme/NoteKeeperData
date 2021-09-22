package com.example.notekeeperdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.example.notekeeperdata.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
private var notePosition = POSITION_NOT_SET

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // val dm = DataManager()
        val adapterCourses = ArrayAdapter<CourseInfo>(
            this,
            android.R.layout.simple_spinner_item, DataManager.courses.values.toList()
        )
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerCourses.adapter = adapterCourses

        notePosition = intent.getIntExtra(EXTRA_NOTE_POSITION, POSITION_NOT_SET)
        if (notePosition != POSITION_NOT_SET)
            displayNote() else {
            DataManager.notes.add(NoteInfo())
            notePosition = DataManager.notes.lastIndex
        }

    }

    private fun displayNote() {
        val note = DataManager.notes[notePosition]
        binding.textNoteTitle.setText(note.title)
        binding.textNoteText.setText(note.text)

        val coursePosition = DataManager.courses.values.indexOf(note.courses)
        binding.spinnerCourses.setSelection(coursePosition)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                moveNext()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }


    }

    private fun moveNext() {
        ++notePosition
        displayNote()

        invalidateOptionsMenu()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (notePosition >= DataManager.notes.lastIndex) {
            val menuItem = menu?.findItem(R.id.action_next)
            if (menuItem != null) {
                menuItem.icon = getDrawable(R.drawable.ic_baseline_block_24)
                menuItem.isEnabled = false
            }
        }

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
        val note = DataManager.notes[notePosition]
        note.title = binding.textNoteTitle.text.toString()
        note.text = binding.textNoteText.text.toString()
        note.courses = binding.spinnerCourses.selectedItem as CourseInfo
    }
}