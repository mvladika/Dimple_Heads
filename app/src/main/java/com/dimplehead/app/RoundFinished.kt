package com.dimplehead.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseApiNotAvailableException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RoundFinished : AppCompatActivity() {

    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_round_finished)

        var plyr1score: Int? = null
        var plyr2score: Int? = null
        var plyr3score: Int? = null
        var plyr4score: Int? = null
        var nameOfPlayer2: String? = null
        var nameOfPlayer3: String? = null
        var nameOfPlayer4: String? = null
        var parString: String? = null
        var courseString: String? = null
        var userBestScore: String? = null
        var userName: String? = null
        val bundle: Bundle? = intent.extras
        val numberOfPlayers = intent.getSerializableExtra("NumberOfPlayers") as Int
        val secondPlaceRL = findViewById<RelativeLayout>(R.id.entireSecondPlaceRL)
        val thirdPlaceRL = findViewById<RelativeLayout>(R.id.entireThirdPlaceRL)
        val fourthPlaceRL = findViewById<RelativeLayout>(R.id.entireFourthPlaceRL)
        val firstplacescore = findViewById<TextView>(R.id.firstplacescore)
        val secondplacescore = findViewById<TextView>(R.id.secondplacescore)
        val thirdplacescore = findViewById<TextView>(R.id.thirdplacescore)
        val fourthplacescore = findViewById<TextView>(R.id.fourthplacescore)
        val firstplaceplayer = findViewById<TextView>(R.id.firstplaceplaver)
        val secondplaceplayer = findViewById<TextView>(R.id.secondplaceplaver)
        val thirdplaceplayer = findViewById<TextView>(R.id.thirdplaceplaver)
        val fourthplaceplayer = findViewById<TextView>(R.id.fourthplaceplaver)
        val returnhomebtn = findViewById<Button>(R.id.returnhomebtn)
        val nameOfCoursetv = findViewById<TextView>(R.id.roundFinishedGolfCourse)
        val roundfinishedpar = findViewById<TextView>(R.id.roundfinishedpar)

        bundle?.let {
            bundle.apply {
                courseString = bundle.getString("NameOfCourse")
                parString = bundle.getString("ParOfCourse")
                userBestScore = intent.getStringExtra("UserBestScore")
                userName = intent.getStringExtra("UserName")


                if (numberOfPlayers == 1) {
                    plyr1score = bundle.getInt("PlayerOneScore")
                    secondPlaceRL.visibility = INVISIBLE
                    thirdPlaceRL.visibility = INVISIBLE
                    fourthPlaceRL.visibility = INVISIBLE
                } else if (numberOfPlayers == 2) {
                    nameOfPlayer2 = bundle.getString("NameOfPlayerTwo") as String
                    plyr1score = bundle.getInt("PlayerOneScore")
                    plyr2score = bundle.getInt("PlayerTwoScore")
                    thirdPlaceRL.visibility = INVISIBLE
                    fourthPlaceRL.visibility = INVISIBLE
                } else if (numberOfPlayers == 3) {
                    nameOfPlayer2 = bundle.getString("NameOfPlayerTwo") as String
                    nameOfPlayer3 = bundle.getString("NameOfPlayerThree") as String
                    plyr1score = bundle.getInt("PlayerOneScore")
                    plyr2score = bundle.getInt("PlayerTwoScore")
                    plyr3score = bundle.getInt("PlayerThreeScore")
                    fourthPlaceRL.visibility = INVISIBLE
                } else if (numberOfPlayers == 4) {
                    nameOfPlayer2 = bundle.getString("NameOfPlayerTwo") as String
                    nameOfPlayer3 = bundle.getString("NameOfPlayerThree") as String
                    nameOfPlayer4 = bundle.getString("NameOfPlayerFour") as String
                    plyr1score = bundle.getInt("PlayerOneScore")
                    plyr2score = bundle.getInt("PlayerTwoScore")
                    plyr3score = bundle.getInt("PlayerThreeScore")
                    plyr4score = bundle.getInt("PlayerFourScore")
                }
            }
        }

        roundfinishedpar.text = "Par at $courseString is $parString"
        nameOfCoursetv.text = courseString

        if (numberOfPlayers == 1) {
            firstplaceplayer.text = userName
            firstplacescore.text = plyr1score.toString()
        } else if (numberOfPlayers == 2) {
            if (plyr1score!! <= plyr2score!!) {
                firstplacescore.text = plyr1score.toString()
                firstplaceplayer.text = userName
                secondplacescore.text = plyr2score.toString()
                secondplaceplayer.text = nameOfPlayer2
            } else {
                firstplacescore.text = plyr2score.toString()
                firstplaceplayer.text = nameOfPlayer2
                secondplacescore.text = plyr1score.toString()
                secondplaceplayer.text = userName
            }
        } else if (numberOfPlayers == 3) {
            if (plyr1score!! <= plyr2score!! && plyr1score!! <= plyr3score!!) {
                firstplacescore.text = plyr1score.toString()
                firstplaceplayer.text = userName

                if (plyr2score!! <= plyr3score!!) {
                    secondplacescore.text = plyr2score.toString()
                    secondplaceplayer.text = nameOfPlayer2
                    thirdplacescore.text = plyr3score.toString()
                    thirdplaceplayer.text = nameOfPlayer3
                } else {
                    secondplacescore.text = plyr3score.toString()
                    secondplaceplayer.text = nameOfPlayer3
                    thirdplacescore.text = plyr2score.toString()
                    thirdplaceplayer.text = nameOfPlayer2
                }
            } else if (plyr2score!! <= plyr1score!! && plyr2score!! <= plyr3score!!) {
                firstplacescore.text = plyr2score.toString()
                firstplaceplayer.text = nameOfPlayer2
                if (plyr1score!! <= plyr3score!!) {
                    secondplacescore.text = plyr1score.toString()
                    secondplaceplayer.text = userName
                    thirdplacescore.text = plyr3score.toString()
                    thirdplaceplayer.text = nameOfPlayer3
                } else {
                    secondplacescore.text = plyr3score.toString()
                    secondplaceplayer.text = nameOfPlayer3
                    thirdplacescore.text = plyr1score.toString()
                    thirdplaceplayer.text = userName
                }
            } else if (plyr3score!! <= plyr1score!! && plyr3score!! <= plyr2score!!) {
                firstplacescore.text = plyr3score.toString()
                firstplaceplayer.text = nameOfPlayer3
                if (plyr2score!! <= plyr1score!!) {
                    secondplacescore.text = plyr2score.toString()
                    secondplaceplayer.text = nameOfPlayer2

                    thirdplacescore.text = plyr1score.toString()
                    thirdplaceplayer.text = userName
                } else {
                    secondplacescore.text = plyr1score.toString()
                    secondplaceplayer.text = userName

                    thirdplacescore.text = plyr2score.toString()
                    thirdplaceplayer.text = nameOfPlayer2
                }
            }
        } else if (numberOfPlayers == 4) {
            if (plyr1score!! <= plyr2score!! && plyr1score!! <= plyr3score!! && plyr1score!! <= plyr4score!!) {
                firstplacescore.text = plyr1score.toString()
                firstplaceplayer.text = userName

                if (plyr2score!! <= plyr3score!! && plyr2score!! <= plyr4score!!) {
                    secondplacescore.text = plyr2score.toString()
                    secondplaceplayer.text = nameOfPlayer2
                    if (plyr3score!! <= plyr4score!!) {
                        thirdplacescore.text = plyr3score.toString()
                        thirdplaceplayer.text = nameOfPlayer3
                        fourthplacescore.text = plyr4score.toString()
                        fourthplaceplayer.text = nameOfPlayer4
                    } else {
                        thirdplacescore.text = plyr4score.toString()
                        thirdplaceplayer.text = nameOfPlayer4
                        fourthplacescore.text = plyr3score.toString()
                        fourthplaceplayer.text = nameOfPlayer3
                    }
                } else if (plyr3score!! <= plyr2score!! && plyr3score!! <= plyr4score!!) {
                    secondplacescore.text = plyr3score.toString()
                    secondplaceplayer.text = nameOfPlayer3
                    if (plyr2score!! <= plyr4score!!) {
                        thirdplacescore.text = plyr2score.toString()
                        thirdplaceplayer.text = nameOfPlayer2
                        fourthplacescore.text = plyr4score.toString()
                        fourthplaceplayer.text = nameOfPlayer4
                    } else {
                        thirdplacescore.text = plyr4score.toString()
                        thirdplaceplayer.text = nameOfPlayer4
                        fourthplacescore.text = plyr2score.toString()
                        fourthplaceplayer.text = nameOfPlayer2
                    }
                } else if (plyr4score!! <= plyr2score!! && plyr4score!! <= plyr3score!!) {
                    secondplacescore.text = plyr4score.toString()
                    secondplaceplayer.text = nameOfPlayer4
                    if (plyr2score!! <= plyr3score!!) {
                        thirdplacescore.text = plyr2score.toString()
                        thirdplaceplayer.text = nameOfPlayer2
                        fourthplacescore.text = plyr3score.toString()
                        fourthplaceplayer.text = nameOfPlayer3
                    } else {
                        thirdplacescore.text = plyr3score.toString()
                        thirdplaceplayer.text = nameOfPlayer3
                        fourthplacescore.text = plyr2score.toString()
                        fourthplaceplayer.text = nameOfPlayer2
                    }
                }
            } else if (plyr2score!! <= plyr1score!! && plyr2score!! <= plyr3score!! && plyr2score!! <= plyr4score!!) {
                firstplacescore.text = plyr2score.toString()
                firstplaceplayer.text = nameOfPlayer2
                if (plyr1score!! <= plyr3score!! && plyr1score!! <= plyr4score!!) {
                    secondplacescore.text = plyr1score.toString()
                    secondplaceplayer.text = userName

                    if (plyr3score!! <= plyr4score!!) {
                        thirdplacescore.text = plyr3score.toString()
                        thirdplaceplayer.text = nameOfPlayer3
                        fourthplacescore.text = plyr4score.toString()
                        fourthplaceplayer.text = nameOfPlayer4
                    } else {
                        thirdplacescore.text = plyr4score.toString()
                        thirdplaceplayer.text = nameOfPlayer4
                        fourthplacescore.text = plyr3score.toString()
                        fourthplaceplayer.text = nameOfPlayer3
                    }
                } else if (plyr3score!! <= plyr1score!! && plyr3score!! <= plyr4score!!) {
                    secondplacescore.text = plyr3score.toString()
                    secondplaceplayer.text = nameOfPlayer3
                    if (plyr1score!! <= plyr4score!!) {
                        thirdplacescore.text = plyr1score.toString()
                        thirdplaceplayer.text = userName
                        fourthplacescore.text = plyr4score.toString()
                        fourthplaceplayer.text = nameOfPlayer4
                    } else {
                        thirdplacescore.text = plyr4score.toString()
                        thirdplaceplayer.text = nameOfPlayer4
                        fourthplacescore.text = plyr1score.toString()
                        fourthplaceplayer.text = userName
                    }
                } else if (plyr4score!! <= plyr1score!! && plyr4score!! <= plyr3score!!) {
                    secondplacescore.text = plyr4score.toString()
                    secondplaceplayer.text = nameOfPlayer4
                    if (plyr1score!! <= plyr3score!!) {
                        thirdplacescore.text = plyr1score.toString()
                        thirdplaceplayer.text = userName
                        fourthplacescore.text = plyr3score.toString()
                        fourthplaceplayer.text = nameOfPlayer3
                    } else {
                        thirdplacescore.text = plyr3score.toString()
                        thirdplaceplayer.text = nameOfPlayer3
                        fourthplacescore.text = plyr1score.toString()
                        fourthplaceplayer.text = userName
                    }
                }
            } else if (plyr3score!! <= plyr1score!! && plyr3score!! <= plyr2score!! && plyr3score!! <= plyr4score!!) {
                firstplacescore.text = plyr3score.toString()
                firstplaceplayer.text = nameOfPlayer3
                if (plyr2score!! <= plyr1score!! && plyr2score!! <= plyr4score!!) {
                    secondplacescore.text = plyr2score.toString()
                    secondplaceplayer.text = nameOfPlayer2
                    if (plyr1score!! <= plyr4score!!) {
                        thirdplacescore.text = plyr1score.toString()
                        thirdplaceplayer.text = userName
                        fourthplacescore.text = plyr4score.toString()
                        fourthplaceplayer.text = nameOfPlayer4
                    } else {
                        thirdplacescore.text = plyr4score.toString()
                        thirdplaceplayer.text = nameOfPlayer4
                        fourthplacescore.text = plyr1score.toString()
                        fourthplaceplayer.text = userName
                    }
                } else if (plyr1score!! <= plyr2score!! && plyr1score!! <= plyr4score!!) {
                    secondplacescore.text = plyr1score.toString()
                    secondplaceplayer.text = userName
                    if (plyr2score!! <= plyr4score!!) {
                        thirdplacescore.text = plyr2score.toString()
                        thirdplaceplayer.text = nameOfPlayer2
                        fourthplacescore.text = plyr4score.toString()
                        fourthplaceplayer.text = nameOfPlayer4
                    } else {
                        thirdplacescore.text = plyr4score.toString()
                        thirdplaceplayer.text = nameOfPlayer4
                        fourthplacescore.text = plyr2score.toString()
                        fourthplaceplayer.text = nameOfPlayer2
                    }
                } else if (plyr4score!! <= plyr1score!! && plyr4score!! <= plyr2score!!) {
                    secondplacescore.text = plyr4score.toString()
                    secondplaceplayer.text = nameOfPlayer4
                    if (plyr2score!! <= plyr1score!!) {
                        thirdplacescore.text = plyr2score.toString()
                        thirdplaceplayer.text = nameOfPlayer2
                        fourthplacescore.text = plyr1score.toString()
                        fourthplaceplayer.text = userName
                    } else {
                        thirdplacescore.text = plyr1score.toString()
                        thirdplaceplayer.text = userName
                        fourthplacescore.text = plyr2score.toString()
                        fourthplaceplayer.text = nameOfPlayer2
                    }
                }
            } else if (plyr4score!! <= plyr1score!! && plyr4score!! <= plyr2score!! && plyr4score!! <= plyr3score!!) {
                firstplacescore.text = plyr4score.toString()
                firstplaceplayer.text = nameOfPlayer4
                if (plyr2score!! <= plyr3score!! && plyr2score!! <= plyr1score!!) {
                    secondplacescore.text = plyr2score.toString()
                    secondplaceplayer.text = nameOfPlayer2
                    if (plyr3score!! <= plyr1score!!) {
                        thirdplacescore.text = plyr3score.toString()
                        thirdplaceplayer.text = nameOfPlayer3
                        fourthplacescore.text = plyr1score.toString()
                        fourthplaceplayer.text = userName
                    } else {
                        thirdplacescore.text = plyr1score.toString()
                        thirdplaceplayer.text = userName
                        fourthplacescore.text = plyr3score.toString()
                        fourthplaceplayer.text = nameOfPlayer3
                    }
                } else if (plyr1score!! <= plyr3score!! && plyr1score!! <= plyr2score!!) {
                    secondplacescore.text = plyr1score.toString()
                    secondplaceplayer.text = userName
                    if (plyr3score!! <= plyr2score!!) {
                        thirdplacescore.text = plyr3score.toString()
                        thirdplaceplayer.text = nameOfPlayer3
                        fourthplacescore.text = plyr2score.toString()
                        fourthplaceplayer.text = nameOfPlayer2
                    } else {
                        thirdplacescore.text = plyr2score.toString()
                        thirdplaceplayer.text = nameOfPlayer2
                        fourthplacescore.text = plyr3score.toString()
                        fourthplaceplayer.text = nameOfPlayer3
                    }
                } else if (plyr3score!! <= plyr1score!! && plyr3score!! <= plyr2score!!) {
                    secondplacescore.text = plyr3score.toString()
                    secondplaceplayer.text = nameOfPlayer3
                    if (plyr2score!! <= plyr1score!!) {
                        thirdplacescore.text = plyr2score.toString()
                        thirdplaceplayer.text = nameOfPlayer2
                        fourthplacescore.text = plyr1score.toString()
                        fourthplaceplayer.text = userName
                    } else {
                        thirdplacescore.text = plyr1score.toString()
                        thirdplaceplayer.text = userName
                        fourthplaceplayer.text = nameOfPlayer2
                        fourthplacescore.text = plyr2score.toString()
                    }
                }
            }
        }

        returnhomebtn.setOnClickListener {
            val intent = Intent(this, UserHome::class.java)

            val database = Firebase.database.reference.child("Users")

            database.child(FirebaseAuth.getInstance().uid.toString()).child("lastCoursePlayed").setValue(courseString)
            database.child(FirebaseAuth.getInstance().uid.toString()).child("finalScoreOfLastRound").setValue(plyr1score.toString())


            if(userBestScore!! > plyr1score.toString())
            {
                database.child(FirebaseAuth.getInstance().uid.toString()).child("userBestScore").setValue("$plyr1score @ $courseString")
            }

            startActivity(intent)
        }
    }
}
//if(database.child(FirebaseAuth.getInstance().uid.toString()).child("userBestScore").get().result.value.toString() == "No Current Data")
//{
//    database.child(FirebaseAuth.getInstance().uid.toString()).child("userBestScore").setValue(plyr1score.toString())
//}
//else if(database.child(FirebaseAuth.getInstance().uid.toString()).child("userBestScore").get().result.value.toString() > plyr1score.toString())
//{
//    database.child(FirebaseAuth.getInstance().uid.toString()).child("userBestScore").setValue(plyr1score.toString())
//}