package com.dimplehead.app

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.VISIBLE
import android.view.Window
import android.widget.*
import kotlinx.android.synthetic.main.activity_new_round.*

class NewRound : AppCompatActivity() {

    var x = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_round)

        setupCourseSpinner()

        addplayerbtn.setOnClickListener {
            showCustomDialog()
        }

        playaroundBTNiv.setOnClickListener {
           // val intent = Intent(this, ActiveRound::class.java)
           // startActivity(intent)
        }
    }

    private fun showCustomDialog() {

        val dialog = Dialog(this)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.activity_add_player_dialog)

        val playerToAddET = dialog.findViewById<EditText>(R.id.playersnametoaddET)
        val addPlayerBTN = dialog.findViewById<Button>(R.id.addplayerdialogbutton)

        addPlayerBTN.setOnClickListener {
            x++
            val playername = playerToAddET.text
            val plyr2RL = findViewById<RelativeLayout>(R.id.player2RL)
            val plyr3RL = findViewById<RelativeLayout>(R.id.player3RL)
            val plyr4RL = findViewById<RelativeLayout>(R.id.player4RL)
            val plyr2tv = findViewById<TextView>(R.id.player2name)
            val plyr3tv = findViewById<TextView>(R.id.player3name)
            val plyr4tv = findViewById<TextView>(R.id.player4name)

            if(x == 2)
            {
                plyr2RL.visibility = VISIBLE
                plyr2tv.text = playername
            }
            else if (x == 3)
            {
                plyr3RL.visibility = VISIBLE
                plyr3tv.text = playername
            }
            else if (x == 4)
            {
                plyr4RL.visibility = VISIBLE
                plyr4tv.text = playername
            }

            dialog.dismiss()
        }

        dialog.show()
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

    override fun onDestroy() {
        super.onDestroy()
        x = 0
    }
}