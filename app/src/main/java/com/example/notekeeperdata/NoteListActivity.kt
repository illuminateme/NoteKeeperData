package com.example.notekeeperdata

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.notekeeperdata.databinding.ActivityNoteListBinding
import com.google.android.material.snackbar.Snackbar

private lateinit var binding: ActivityNoteListBinding

class NoteListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNoteListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            val activityIntent = Intent(this, MainActivity::class.java)
            startActivity(activityIntent)
        }

        binding.listNotes.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, DataManager.notes)
    }
}