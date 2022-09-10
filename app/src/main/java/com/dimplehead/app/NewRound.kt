package com.dimplehead.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.VISIBLE
import android.widget.ArrayAdapter
import android.widget.RelativeLayout
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_new_round.*

class NewRound : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_round)

        setupCourseSpinner()

        addplayerbtn.setOnClickListener {
            player4RL.visibility = VISIBLE
        }

        playaroundBTNiv.setOnClickListener {
           // val intent = Intent(this, ActiveRound::class.java)
           // startActivity(intent)
        }
    }

    private fun setupCourseSpinner()
    {
        val adapter =  ArrayAdapter.createFromResource(
            this,
            R.array.courseNames,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        newroundCourseSpinner.adapter = adapter
    }
}