package com.dimplehead.app

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.VISIBLE
import android.view.Window
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_new_round.*
import kotlinx.android.synthetic.main.fragment_home.*

class NewRound : AppCompatActivity() {

    var numOfPlayers = 1
    var userBestScore: String? = null
    var userName: String? = null
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_round)

        var user: Users? = null
        database = Firebase.database.reference
        database.child("Users").child(FirebaseAuth.getInstance().uid.toString()).get().addOnSuccessListener {
            user = it.getValue(Users::class.java)!!
            userBestScore = user?.userBestScore
            userName = user?.userName
            usersNamePlayers.text = userName
        }.addOnFailureListener{
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }


        setupCourseSpinner()
        val spinny = findViewById<Spinner>(R.id.newroundCourseSpinner)

        addplayerbtn.setOnClickListener {
            showAddPlayerDialog()
        }

        playaroundBTNiv.setOnClickListener {
            val intent = Intent(this, ActiveRound::class.java)
            intent.putExtra("NumberOfPlayers", numOfPlayers)
            intent.putExtra("UserBestScore", userBestScore)
            val nameOfCourse = spinny.selectedItem.toString()
            intent.putExtra("NameOfCourse", nameOfCourse)
            intent.putExtra("UserName", userName)

            val nameOfPlayerTwoString : String = player2name.text.toString()
            val nameOfPlayerThreeString : String = player3name.text.toString()
            val nameOfPlayerFourString : String = player4name.text.toString()

            if(numOfPlayers == 2)
            {
                intent.putExtra("NameOfPlayerTwo", nameOfPlayerTwoString)
            }
            else if (numOfPlayers == 3)
            {
                intent.putExtra("NameOfPlayerTwo", nameOfPlayerTwoString)
                intent.putExtra("NameOfPlayerThree", nameOfPlayerThreeString)
            }
            else if (numOfPlayers == 4)
            {
                intent.putExtra("NameOfPlayerTwo", nameOfPlayerTwoString)
                intent.putExtra("NameOfPlayerThree", nameOfPlayerThreeString)
                intent.putExtra("NameOfPlayerFour", nameOfPlayerFourString)
            }
            if(nameOfCourse == "Select A Course")
            {
                Toast.makeText(this, "Please Select A Course", Toast.LENGTH_SHORT).show()
            }
            else
            {
                startActivity(intent)
            }
        }
    }

    private fun showAddPlayerDialog() {

        val dialog = Dialog(this)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.activity_add_player_dialog)

        val playerToAddET = dialog.findViewById<EditText>(R.id.playersnametoaddET)
        val addPlayerBTN = dialog.findViewById<Button>(R.id.addplayerdialogbutton)

        addPlayerBTN.setOnClickListener {
            numOfPlayers++
            val playername = playerToAddET.text
            val plyr2RL = findViewById<RelativeLayout>(R.id.player2RL)
            val plyr3RL = findViewById<RelativeLayout>(R.id.player3RL)
            val plyr4RL = findViewById<RelativeLayout>(R.id.player4RL)
            val plyr2tv = findViewById<TextView>(R.id.player2name)
            val plyr3tv = findViewById<TextView>(R.id.player3name)
            val plyr4tv = findViewById<TextView>(R.id.player4name)

            if(numOfPlayers == 2)
            {
                plyr2RL.visibility = VISIBLE
                plyr2tv.text = playername
                playaroundBTNiv.setPadding(60,60,60,60)
            }
            else if (numOfPlayers == 3)
            {
                plyr3RL.visibility = VISIBLE
                plyr3tv.text = playername
                playaroundBTNiv.setPadding(40,40,40,40)
            }
            else if (numOfPlayers == 4)
            {
                plyr4RL.visibility = VISIBLE
                plyr4tv.text = playername
                playaroundBTNiv.setPadding(20,20,20,20)
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
        numOfPlayers = 0
    }
}