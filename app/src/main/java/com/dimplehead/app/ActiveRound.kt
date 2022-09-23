package com.dimplehead.app

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.Window
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_active_round.*

class ActiveRound : AppCompatActivity() {

    var x = 0;
    var parMap = HashMap<Int, String>()
    var p1score = HashMap<Int, String>()
    var p2score = HashMap<Int, String>()
    var p3score = HashMap<Int, String>()
    var p4score = HashMap<Int, String>()
    var nameOfPlayer2: String? = null
    var nameOfPlayer3: String? = null
    var nameOfPlayer4: String? = null
    var nameOfCourse: String? = null
    var coursePar: String? = null
    var userBestScore: String? = null
    var userName: String? = null
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_round)
        val changeNineBtn = findViewById<Button>(R.id.prevORnext9btn)
        val h1 = findViewById<TextView>(R.id.h1)
        val h2 = findViewById<TextView>(R.id.h2)
        val h3 = findViewById<TextView>(R.id.h3)
        val h4 = findViewById<TextView>(R.id.h4)
        val h5 = findViewById<TextView>(R.id.h5)
        val h6 = findViewById<TextView>(R.id.h6)
        val h7 = findViewById<TextView>(R.id.h7)
        val h8 = findViewById<TextView>(R.id.h8)
        val h9 = findViewById<TextView>(R.id.h9)
        val bundle: Bundle? = intent.extras
        val numberOfPlayers = intent.getSerializableExtra("NumberOfPlayers") as Int
        val courseInfoBtn = findViewById<ImageButton>(R.id.courseInfoBtn)

    //Getting Names of Each Player From New Round
        bundle?.let {
            bundle.apply {
                nameOfCourse = bundle.getString("NameOfCourse") as String
                userBestScore = intent.getStringExtra("UserBestScore")
                userName = intent.getStringExtra("UserName")

                if (numberOfPlayers == 2) {
                    nameOfPlayer2 = bundle.getString("NameOfPlayerTwo") as String
                } else if (numberOfPlayers == 3) {
                    nameOfPlayer2 = bundle.getString("NameOfPlayerTwo") as String
                    nameOfPlayer3 = bundle.getString("NameOfPlayerThree") as String
                } else if (numberOfPlayers == 4) {
                    nameOfPlayer2 = bundle.getString("NameOfPlayerTwo") as String
                    nameOfPlayer3 = bundle.getString("NameOfPlayerThree") as String
                    nameOfPlayer4 = bundle.getString("NameOfPlayerFour") as String
                }
            }
        }

    //Getting course info from firebase for course info button
        var course: Courses? = null
        database = Firebase.database.reference
        database.child("Courses").child(nameOfCourse!!).get().addOnSuccessListener {
            course = it.getValue(Courses::class.java)!!
            coursePar = course?.coursePar
        }.addOnFailureListener{
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }

    //Course Info Dialog Box Button
        courseInfoBtn.setOnClickListener {
            showCourseInfoDialog(course?.courseName!!, course?.courseAddress!!, course?.yearOpened!!, course?.coursePar!!,
                course?.numOfHoles!!, course?.drivingRange!!, course?.rentalCarts!!, course?.rentalClubs!!, course?.proOnsite!!)
        }



    //Initializing the Scorecard based on info from New Round
        if (numberOfPlayers == 1) {
            setPlayer2invisible()
            setPlayer3invisible()
            setPlayer4invisible()
            player1headertv.text = userName
        }
        else if (numberOfPlayers == 2) {
            setPlayer3invisible()
            setPlayer4invisible()
            player1headertv.text = userName
            player2headertv.text = nameOfPlayer2
        }
        else if (numberOfPlayers == 3) {
            setPlayer4invisible()
            player1headertv.text = userName
            player2headertv.text = nameOfPlayer2
            player3headertv.text = nameOfPlayer3
        }
        else if (numberOfPlayers == 4) {
            player1headertv.text = userName
            player2headertv.text = nameOfPlayer2
            player3headertv.text = nameOfPlayer3
            player4headertv.text = nameOfPlayer4
        }

    //Getting Par For Each Hole From User
        h1par.setOnClickListener {
            if (x == 0) {
                showEnterParDialog(h1par, 1)
            } else if (x == 1) {
                showEnterParDialog(h1par, 10)
            }
        }
        h2par.setOnClickListener {
            if (x == 0) {
                showEnterParDialog(h2par, 2)
            } else if (x == 1) {
                showEnterParDialog(h2par, 11)
            }
        }
        h3par.setOnClickListener {
            if (x == 0) {
                showEnterParDialog(h3par, 3)
            } else if (x == 1) {
                showEnterParDialog(h3par, 12)
            }
        }
        h4par.setOnClickListener {
            if (x == 0) {
                showEnterParDialog(h4par, 4)
            } else if (x == 1) {
                showEnterParDialog(h4par, 13)
            }
        }
        h5par.setOnClickListener {
            if (x == 0) {
                showEnterParDialog(h5par, 5)
            } else if (x == 1) {
                showEnterParDialog(h5par, 14)
            }
        }
        h6par.setOnClickListener {
            if (x == 0) {
                showEnterParDialog(h6par, 6)
            } else if (x == 1) {
                showEnterParDialog(h6par, 15)
            }
        }
        h7par.setOnClickListener {
            if (x == 0) {
                showEnterParDialog(h7par, 7)
            } else if (x == 1) {
                showEnterParDialog(h7par, 16)
            }
        }
        h8par.setOnClickListener {
            if (x == 0) {
                showEnterParDialog(h8par, 8)
            } else if (x == 1) {
                showEnterParDialog(h8par, 17)
            }
        }
        h9par.setOnClickListener {
            if (x == 0) {
                showEnterParDialog(h9par, 9)
            } else if (x == 1) {
                showEnterParDialog(h9par, 18)
            }
        }

    //Getting Score For Player 1 from User

        p1h1.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p1h1, 1, p1score)
            }
            else if (x == 1)
            {
                showEnterScoreDialog(p1h1, 10, p1score)
            }
        }
        p1h2.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p1h2, 2, p1score)
            }
            else if (x == 1)
            {
                showEnterScoreDialog(p1h2, 11, p1score)
            }
        }
        p1h3.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p1h3, 3, p1score)
            }
            else if (x == 1)
            {
                showEnterScoreDialog(p1h3, 12, p1score)
            }
        }
        p1h4.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p1h4, 4, p1score)
            }
            else if (x == 1)
            {
                showEnterScoreDialog(p1h4, 13, p1score)
            }
        }
        p1h5.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p1h5, 5, p1score)
            }
            else if (x == 1)
            {
                showEnterScoreDialog(p1h5, 14, p1score)
            }
        }
        p1h6.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p1h6, 6, p1score)
            }
            else if (x == 1)
            {
                showEnterScoreDialog(p1h6, 15, p1score)
            }
        }
        p1h7.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p1h7, 7, p1score)
            }
            else if (x == 1)
            {
                showEnterScoreDialog(p1h7, 16, p1score)
            }
        }
        p1h8.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p1h8, 8, p1score)
            }
            else if (x == 1)
            {
                showEnterScoreDialog(p1h8, 17, p1score)
            }
        }
        p1h9.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p1h9, 9, p1score)
            }
            else if (x == 1)
            {
                showEnterScoreDialog(p1h9, 18, p1score)
            }
        }

    //Getting Score For Player 2 from User

        p2h1.setOnClickListener {
             if (x == 0)
             {
                 showEnterScoreDialog(p2h1, 1, p2score)
             }
             else if(x == 1)
             {
                 showEnterScoreDialog(p2h1, 10, p2score)
             }
        }
        p2h2.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p2h2, 2, p2score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p2h2, 11, p2score)
            }
        }
        p2h3.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p2h3, 3, p2score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p2h3, 12, p2score)
            }
        }
        p2h4.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p2h4, 4, p2score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p2h4, 13, p2score)
            }
        }
        p2h5.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p2h5, 5, p2score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p2h5, 14, p2score)
            }
        }
        p2h6.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p2h6, 6, p2score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p2h6, 15, p2score)
            }
        }
        p2h7.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p2h7, 7, p2score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p2h7, 16, p2score)
            }
        }
        p2h8.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p2h8, 8, p2score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p2h8, 17, p2score)
            }
        }
        p2h9.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p2h9, 9, p2score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p2h9, 18, p2score)
            }
        }

    //Getting Score For Player 3 from User

        p3h1.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p3h1, 1, p3score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p3h1, 10, p3score)
            }
        }
        p3h2.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p3h2, 2, p3score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p3h2, 11, p3score)
            }
        }
        p3h3.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p3h3, 3, p3score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p3h3, 12, p3score)
            }
        }
        p3h4.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p3h4, 4, p3score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p3h4, 13, p3score)
            }
        }
        p3h5.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p3h5, 5, p3score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p3h5, 14, p3score)
            }
        }
        p3h6.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p3h6, 6, p3score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p3h6, 15, p3score)
            }
        }
        p3h7.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p3h7, 7, p3score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p3h7, 16, p3score)
            }
        }
        p3h8.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p3h8, 8, p3score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p3h8, 17, p3score)
            }
        }
        p3h9.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p3h9, 9, p3score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p3h9, 18, p3score)
            }
        }

    //Getting Score For Player 4 from User

        p4h1.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p4h1, 1, p4score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p4h1, 10, p4score)
            }
        }
        p4h2.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p4h2, 2, p4score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p4h2, 11, p4score)
            }
        }
        p4h3.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p4h3, 3, p4score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p4h3, 12, p4score)
            }
        }
        p4h4.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p4h4, 4, p4score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p4h4, 13, p4score)
            }
        }
        p4h5.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p4h5, 5, p4score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p4h5, 14, p4score)
            }
        }
        p4h6.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p4h6, 6, p4score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p4h6, 15, p4score)
            }
        }
        p4h7.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p4h7, 7, p4score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p4h7, 16, p4score)
            }
        }
        p4h8.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p4h8, 8, p4score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p4h8, 17, p4score)
            }
        }
        p4h9.setOnClickListener {
            if (x == 0)
            {
                showEnterScoreDialog(p4h9, 9, p4score)
            }
            else if(x == 1)
            {
                showEnterScoreDialog(p4h9, 18, p4score)
            }
        }

    //Swaps Scorecard from Front to Back Nine or Vice Versa
        changeNineBtn.setOnClickListener {
            if (x == 0) {
                h1.setText("10")
                h2.setText("11")
                h3.setText("12")
                h4.setText("13")
                h5.setText("14")
                h6.setText("15")
                h7.setText("16")
                h8.setText("17")
                h9.setText("18")
                changeNineBtn.setText("Previous Nine")
                clearall()
                x = 1

                if (parMap.containsKey(10)) {
                    h1par.text = parMap[10].toString()
                }
                if (parMap.containsKey(11)) {
                    h2par.text = parMap[11].toString()
                }
                if (parMap.containsKey(12)) {
                    h3par.text = parMap[12].toString()
                }
                if (parMap.containsKey(13)) {
                    h4par.text = parMap[13].toString()
                }
                if (parMap.containsKey(14)) {
                    h5par.text = parMap[14].toString()
                }
                if (parMap.containsKey(15)) {
                    h6par.text = parMap[15].toString()
                }
                if (parMap.containsKey(16)) {
                    h7par.text = parMap[16].toString()
                }
                if (parMap.containsKey(17)) {
                    h8par.text = parMap[17].toString()
                }
                if (parMap.containsKey(18)) {
                    h9par.text = parMap[18].toString()
                }

                if(p1score.containsKey(10))
                {
                    p1h1.text = p1score[10].toString()
                }
                if(p1score.containsKey(11))
                {
                    p1h2.text = p1score[11].toString()
                }
                if(p1score.containsKey(12))
                {
                    p1h3.text = p1score[12].toString()
                }
                if(p1score.containsKey(13))
                {
                    p1h4.text = p1score[13].toString()
                }
                if(p1score.containsKey(14))
                {
                    p1h5.text = p1score[14].toString()
                }
                if(p1score.containsKey(15))
                {
                    p1h6.text = p1score[15].toString()
                }
                if(p1score.containsKey(16))
                {
                    p1h7.text = p1score[16].toString()
                }
                if(p1score.containsKey(17))
                {
                    p1h8.text = p1score[17].toString()
                }
                if(p1score.containsKey(18))
                {
                    p1h9.text = p1score[18].toString()
                }

                if(p2score.containsKey(10))
                {
                    p2h1.text = p2score[10].toString()
                }
                if(p2score.containsKey(11))
                {
                    p2h2.text = p2score[11].toString()
                }
                if(p2score.containsKey(12))
                {
                    p2h3.text = p2score[12].toString()
                }
                if(p2score.containsKey(13))
                {
                    p2h4.text = p2score[13].toString()
                }
                if(p2score.containsKey(14))
                {
                    p2h5.text = p2score[14].toString()
                }
                if(p2score.containsKey(15))
                {
                    p2h6.text = p2score[15].toString()
                }
                if(p2score.containsKey(16))
                {
                    p2h7.text = p2score[16].toString()
                }
                if(p2score.containsKey(17))
                {
                    p2h8.text = p2score[17].toString()
                }
                if(p2score.containsKey(18))
                {
                    p2h9.text = p2score[18].toString()
                }


                if(p3score.containsKey(10))
                {
                    p3h1.text = p3score[10].toString()
                }
                if(p3score.containsKey(11))
                {
                    p3h2.text = p3score[11].toString()
                }
                if(p3score.containsKey(12))
                {
                    p3h3.text = p3score[12].toString()
                }
                if(p3score.containsKey(13))
                {
                    p3h4.text = p3score[13].toString()
                }
                if(p3score.containsKey(14))
                {
                    p3h5.text = p3score[14].toString()
                }
                if(p3score.containsKey(15))
                {
                    p3h6.text = p3score[15].toString()
                }
                if(p3score.containsKey(16))
                {
                    p3h7.text = p3score[16].toString()
                }
                if(p3score.containsKey(17))
                {
                    p3h8.text = p3score[17].toString()
                }
                if(p3score.containsKey(18))
                {
                    p3h9.text = p3score[18].toString()
                }

                if(p4score.containsKey(10))
                {
                    p4h1.text = p4score[10].toString()
                }
                if(p4score.containsKey(11))
                {
                    p4h2.text = p4score[11].toString()
                }
                if(p4score.containsKey(12))
                {
                    p4h3.text = p4score[12].toString()
                }
                if(p4score.containsKey(13))
                {
                    p4h4.text = p4score[13].toString()
                }
                if(p4score.containsKey(14))
                {
                    p4h5.text = p4score[14].toString()
                }
                if(p4score.containsKey(15))
                {
                    p4h6.text = p4score[15].toString()
                }
                if(p4score.containsKey(16))
                {
                    p4h7.text = p4score[16].toString()
                }
                if(p4score.containsKey(17))
                {
                    p4h8.text = p4score[17].toString()
                }
                if(p4score.containsKey(18))
                {
                    p4h9.text = p4score[18].toString()
                }

            } else if (x == 1) {
                h1.setText("1")
                h2.setText("2")
                h3.setText("3")
                h4.setText("4")
                h5.setText("5")
                h6.setText("6")
                h7.setText("7")
                h8.setText("8")
                h9.setText("9")
                changeNineBtn.setText("Next Nine")
                clearall()
                x = 0

                if (parMap.containsKey(1)) {
                    h1par.text = parMap[1].toString()
                }
                if (parMap.containsKey(2)) {
                    h2par.text = parMap[2].toString()
                }
                if (parMap.containsKey(3)) {
                    h3par.text = parMap[3].toString()
                }
                if (parMap.containsKey(4)) {
                    h4par.text = parMap[4].toString()
                }
                if (parMap.containsKey(5)) {
                    h5par.text = parMap[5].toString()
                }
                if (parMap.containsKey(6)) {
                    h6par.text = parMap[6].toString()
                }
                if (parMap.containsKey(7)) {
                    h7par.text = parMap[7].toString()
                }
                if (parMap.containsKey(8)) {
                    h8par.text = parMap[8].toString()
                }
                if (parMap.containsKey(9)) {
                    h9par.text = parMap[9].toString()
                }


                if(p1score.containsKey(1))
                {
                    p1h1.text = p1score[1].toString()
                }
                if(p1score.containsKey(2))
                {
                    p1h2.text = p1score[2].toString()
                }
                if(p1score.containsKey(3))
                {
                    p1h3.text = p1score[3].toString()
                }
                if(p1score.containsKey(4))
                {
                    p1h4.text = p1score[4].toString()
                }
                if(p1score.containsKey(5))
                {
                    p1h5.text = p1score[5].toString()
                }
                if(p1score.containsKey(6))
                {
                    p1h6.text = p1score[6].toString()
                }
                if(p1score.containsKey(7))
                {
                    p1h7.text = p1score[7].toString()
                }
                if(p1score.containsKey(8))
                {
                    p1h8.text = p1score[8].toString()
                }
                if(p1score.containsKey(9))
                {
                    p1h9.text = p1score[9].toString()
                }

                if(p2score.containsKey(1))
                {
                    p2h1.text = p2score[1].toString()
                }
                if(p2score.containsKey(2))
                {
                    p2h2.text = p2score[2].toString()
                }
                if(p2score.containsKey(3))
                {
                    p2h3.text = p2score[3].toString()
                }
                if(p2score.containsKey(4))
                {
                    p2h4.text = p2score[4].toString()
                }
                if(p2score.containsKey(5))
                {
                    p2h5.text = p2score[5].toString()
                }
                if(p2score.containsKey(6))
                {
                    p2h6.text = p2score[6].toString()
                }
                if(p2score.containsKey(7))
                {
                    p2h7.text = p2score[7].toString()
                }
                if(p2score.containsKey(8))
                {
                    p2h8.text = p2score[8].toString()
                }
                if(p2score.containsKey(9))
                {
                    p2h9.text = p2score[9].toString()
                }

                if(p3score.containsKey(1))
                {
                    p3h1.text = p3score[1].toString()
                }
                if(p3score.containsKey(2))
                {
                    p3h2.text = p3score[2].toString()
                }
                if(p3score.containsKey(3))
                {
                    p3h3.text = p3score[3].toString()
                }
                if(p3score.containsKey(4))
                {
                    p3h4.text = p3score[4].toString()
                }
                if(p3score.containsKey(5))
                {
                    p3h5.text = p3score[5].toString()
                }
                if(p3score.containsKey(6))
                {
                    p3h6.text = p3score[6].toString()
                }
                if(p3score.containsKey(7))
                {
                    p3h7.text = p3score[7].toString()
                }
                if(p3score.containsKey(8))
                {
                    p3h8.text = p3score[8].toString()
                }
                if(p3score.containsKey(9))
                {
                    p3h9.text = p3score[9].toString()
                }

                if(p4score.containsKey(1))
                {
                    p4h1.text = p4score[1].toString()
                }
                if(p4score.containsKey(2))
                {
                    p4h2.text = p4score[2].toString()
                }
                if(p4score.containsKey(3))
                {
                    p4h3.text = p4score[3].toString()
                }
                if(p4score.containsKey(4))
                {
                    p4h4.text = p4score[4].toString()
                }
                if(p4score.containsKey(5))
                {
                    p4h5.text = p4score[5].toString()
                }
                if(p4score.containsKey(6))
                {
                    p4h6.text = p4score[6].toString()
                }
                if(p4score.containsKey(7))
                {
                    p4h7.text = p4score[7].toString()
                }
                if(p4score.containsKey(8))
                {
                    p4h8.text = p4score[8].toString()
                }
                if(p4score.containsKey(9))
                {
                    p4h9.text = p4score[9].toString()
                }
            }
        }



    //Ends the Round
        finishroundbtn.setOnClickListener {
            var plyr1Score = 0
            var plyr2Score = 0
            var plyr3Score = 0
            var plyr4Score = 0

            for ((Int, String) in p1score) {
                plyr1Score += String.toInt()
            }
            for ((Int, String) in p2score) {
                plyr2Score += String.toInt()
            }
            for ((Int, String) in p3score) {
                plyr3Score += String.toInt()
            }
            for ((Int, String) in p4score) {
                plyr4Score += String.toInt()
            }

            val intent = Intent(this, RoundFinished::class.java)
            intent.putExtra("NameOfCourse", nameOfCourse)
            intent.putExtra("ParOfCourse", coursePar)
            intent.putExtra("UserBestScore", userBestScore)
            intent.putExtra("UserName", userName)


            if(numberOfPlayers == 1)
            {
                intent.putExtra("NumberOfPlayers", numberOfPlayers)
                intent.putExtra("PlayerOneScore", plyr1Score)
            }
            else if (numberOfPlayers == 2)
            {
                intent.putExtra("NumberOfPlayers", numberOfPlayers)
                intent.putExtra("NameOfPlayerTwo", nameOfPlayer2)
                intent.putExtra("PlayerOneScore", plyr1Score)
                intent.putExtra("PlayerTwoScore", plyr2Score)
            }
            else if (numberOfPlayers == 3)
            {
                intent.putExtra("NumberOfPlayers", numberOfPlayers)
                intent.putExtra("NameOfPlayerTwo", nameOfPlayer2)
                intent.putExtra("NameOfPlayerThree", nameOfPlayer3)
                intent.putExtra("PlayerOneScore", plyr1Score)
                intent.putExtra("PlayerTwoScore", plyr2Score)
                intent.putExtra("PlayerThreeScore", plyr3Score)
            }
            else if (numberOfPlayers == 4)
            {
                intent.putExtra("NumberOfPlayers", numberOfPlayers)
                intent.putExtra("NameOfPlayerTwo", nameOfPlayer2)
                intent.putExtra("NameOfPlayerThree", nameOfPlayer3)
                intent.putExtra("NameOfPlayerFour", nameOfPlayer4)
                intent.putExtra("PlayerOneScore", plyr1Score)
                intent.putExtra("PlayerTwoScore", plyr2Score)
                intent.putExtra("PlayerThreeScore", plyr3Score)
                intent.putExtra("PlayerFourScore", plyr4Score)
            }

            startActivity(intent)
        }

    }

    private fun showCourseInfoDialog(name:String, address:String, yrOpen:String, par:String, numHoles:String, range:String, carts:String, clubs:String, pro:String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.activity_course_info_dialog)

        val courseInfoHeader = dialog.findViewById<TextView>(R.id.courseInfoHeader)
        val courseInfoAddress = dialog.findViewById<TextView>(R.id.courseInfoAddress)
        val courseInfoCoursePar = dialog.findViewById<TextView>(R.id.courseInfoCoursePar)
        val courseInfoNumberOfHoles = dialog.findViewById<TextView>(R.id.courseInfoNumberOfHoles)
        val courseInfoYearOpened = dialog.findViewById<TextView>(R.id.courseInfoYearOpened)
        val courseInfoDrivingRange = dialog.findViewById<TextView>(R.id.courseInfoDrivingRange)
        val courseInfoRentalCarts = dialog.findViewById<TextView>(R.id.courseInfoRentalCarts)
        val courseInfoRentalClubs = dialog.findViewById<TextView>(R.id.courseInfoRentalClubs)
        val courseInfoProOnSite = dialog.findViewById<TextView>(R.id.courseInfoProOnSite)
        val closeCourseInfoDialog = dialog.findViewById<Button>(R.id.closeCourseInfoDialog)

        courseInfoHeader.text = name
        courseInfoAddress.text = address
        courseInfoCoursePar.text = "Course Par: $par"
        courseInfoNumberOfHoles.text = "Number of Holes: $numHoles"
        courseInfoYearOpened.text = "Opened In $yrOpen"
        courseInfoDrivingRange.text = "Driving Range: $range"
        courseInfoRentalCarts.text = "Rental Carts: $carts"
        courseInfoRentalClubs.text = "Rental Clubs: $clubs"
        courseInfoProOnSite.text = "Pro On Site: $pro"

        closeCourseInfoDialog.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showEnterScoreDialog(scoreTextView: TextView, numberHole: Int, scoreMap: HashMap<Int, String>) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.activity_add_score_dialog)

        val scoreToAdd = dialog.findViewById<EditText>(R.id.scoreToAddET)
        val addscorebtn = dialog.findViewById<Button>(R.id.addscoredialogbutton)
        val dialogHeader = dialog.findViewById<TextView>(R.id.enterScoreDialogTV)
        var newHeaderText: String? = null

        if(scoreMap == p1score)
        {
            newHeaderText = "Hole $numberHole: Number of Strokes for $userName"
        }
        else if (scoreMap == p2score)
        {
            newHeaderText = "Hole $numberHole: Number of Strokes for $nameOfPlayer2"
        }
        else if (scoreMap == p3score)
        {
            newHeaderText = "Hole $numberHole: Number of Strokes for $nameOfPlayer3"
        }
        else if (scoreMap == p4score)
        {
            newHeaderText = "Hole $numberHole: Number of Strokes for $nameOfPlayer4"
        }
        dialogHeader.text = newHeaderText

        addscorebtn.setOnClickListener {
            scoreTextView.text = scoreToAdd.text.toString()
            scoreMap[numberHole] = scoreToAdd.text.toString()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun clearall() {
        h1par.text = getString(R.string._1)
        h2par.text = getString(R.string._1)
        h3par.text = getString(R.string._1)
        h4par.text = getString(R.string._1)
        h5par.text = getString(R.string._1)
        h6par.text = getString(R.string._1)
        h7par.text = getString(R.string._1)
        h8par.text = getString(R.string._1)
        h9par.text = getString(R.string._1)
        p1h1.text = getString(R.string._1)
        p1h2.text = getString(R.string._1)
        p1h3.text = getString(R.string._1)
        p1h4.text = getString(R.string._1)
        p1h5.text = getString(R.string._1)
        p1h6.text = getString(R.string._1)
        p1h7.text = getString(R.string._1)
        p1h8.text = getString(R.string._1)
        p1h9.text = getString(R.string._1)
        p2h1.text = getString(R.string._1)
        p2h2.text = getString(R.string._1)
        p2h3.text = getString(R.string._1)
        p2h4.text = getString(R.string._1)
        p2h5.text = getString(R.string._1)
        p2h6.text = getString(R.string._1)
        p2h7.text = getString(R.string._1)
        p2h8.text = getString(R.string._1)
        p2h9.text = getString(R.string._1)
        p3h1.text = getString(R.string._1)
        p3h2.text = getString(R.string._1)
        p3h3.text = getString(R.string._1)
        p3h4.text = getString(R.string._1)
        p3h5.text = getString(R.string._1)
        p3h6.text = getString(R.string._1)
        p3h7.text = getString(R.string._1)
        p3h8.text = getString(R.string._1)
        p3h9.text = getString(R.string._1)
        p4h1.text = getString(R.string._1)
        p4h2.text = getString(R.string._1)
        p4h3.text = getString(R.string._1)
        p4h4.text = getString(R.string._1)
        p4h5.text = getString(R.string._1)
        p4h6.text = getString(R.string._1)
        p4h7.text = getString(R.string._1)
        p4h8.text = getString(R.string._1)
        p4h9.text = getString(R.string._1)
    }

    private fun showEnterParDialog(parTextView: TextView, numberHole: Int) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.activity_enter_par_dialog)

        val partoadd = dialog.findViewById<EditText>(R.id.parToAddET)
        val addParBtn = dialog.findViewById<Button>(R.id.addpardialogbutton)

        addParBtn.setOnClickListener {
            parTextView.text = partoadd.text.toString()
            parMap[numberHole] = partoadd.text.toString()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun setPlayer2invisible() {
        player2headertv.visibility = GONE
        p2h1.visibility = GONE
        p2h2.visibility = GONE
        p2h3.visibility = GONE
        p2h4.visibility = GONE
        p2h5.visibility = GONE
        p2h6.visibility = GONE
        p2h7.visibility = GONE
        p2h8.visibility = GONE
        p2h9.visibility = GONE
    }

    private fun setPlayer3invisible() {
        player3headertv.visibility = GONE
        p3h1.visibility = GONE
        p3h2.visibility = GONE
        p3h3.visibility = GONE
        p3h4.visibility = GONE
        p3h5.visibility = GONE
        p3h6.visibility = GONE
        p3h7.visibility = GONE
        p3h8.visibility = GONE
        p3h9.visibility = GONE
    }

    private fun setPlayer4invisible() {
        player4headertv.visibility = GONE
        p4h1.visibility = GONE
        p4h2.visibility = GONE
        p4h3.visibility = GONE
        p4h4.visibility = GONE
        p4h5.visibility = GONE
        p4h6.visibility = GONE
        p4h7.visibility = GONE
        p4h8.visibility = GONE
        p4h9.visibility = GONE
    }
}