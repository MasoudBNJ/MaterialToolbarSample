package com.example.toolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    var actionMode: ActionMode? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        val callback  = object : ActionMode.Callback {
            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                menuInflater.inflate(R.menu.contextual_action_bar, menu)
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return when(item?.itemId) {
                    R.id.share -> {
                        // Handle share icon press
                        true
                    }
                    R.id.delete -> {
                        // Handle delete icon press
                        true
                    }
                    R.id.more -> {
                        // Handle more item (inside overflow menu) press
                        true
                    }
                    else -> false
                }
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                actionMode = null
            }
        }
        
        findViewById<Button>(R.id.toggle_action).setOnClickListener {
            if (actionMode != null) {
                actionMode?.finish()
            } else {
                actionMode = toolbar.startActionMode(callback)
                actionMode?.title = "1"
            }
        }

        findViewById<Button>(R.id.select_more_items).setOnClickListener {
            if (actionMode != null) {
                actionMode?.title = "2"
            } else {
                actionMode = toolbar.startActionMode(callback)
                actionMode?.title = "1"
            }
        }
    }
}