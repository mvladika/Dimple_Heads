package com.dimplehead.app

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_active_round.*
import kotlinx.android.synthetic.main.activity_new_round.*

class ActiveRound : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_round)

        var nameOfPlayer2: String? = null
        var nameOfPlayer3: String? = null
        var nameOfPlayer4: String? = null
        val bundle: Bundle? = intent.extras
        val numberOfPlayers = intent.getSerializableExtra("NumberOfPlayers") as Int

        bundle?.let {

            bundle.apply {

                if(numberOfPlayers == 2)
                {
                    nameOfPlayer2 = bundle.getString("NameOfPlayerTwo") as String
                }
                else if(numberOfPlayers == 3)
                {
                    nameOfPlayer2 = bundle.getString("NameOfPlayerTwo") as String
                    nameOfPlayer3 = bundle.getString("NameOfPlayerThree") as String
                }
                else if (numberOfPlayers == 4)
                {
                    nameOfPlayer2 = bundle.getString("NameOfPlayerTwo") as String
                    nameOfPlayer3 = bundle.getString("NameOfPlayerThree") as String
                    nameOfPlayer4 = bundle.getString("NameOfPlayerFour") as String
                }


            }


            if (numberOfPlayers == 1) {
                setPlayer2invisible()
                setPlayer3invisible()
                setPlayer4invisible()
                player1headertv.text = getString(R.string.matt)
            } else if (numberOfPlayers == 2) {
                setPlayer3invisible()
                setPlayer4invisible()
                player1headertv.text = getString(R.string.matt)
                player2headertv.text = nameOfPlayer2
            } else if (numberOfPlayers == 3) {
                setPlayer4invisible()
                player1headertv.text = getString(R.string.matt)
                player2headertv.text = nameOfPlayer2
                player3headertv.text = nameOfPlayer3
            } else if (numberOfPlayers == 4) {
                player1headertv.text = getString(R.string.matt)
                player2headertv.text = nameOfPlayer2
                player3headertv.text = nameOfPlayer3
                player4headertv.text = nameOfPlayer4
            }

            var x = 0;

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
                    x = 1
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
                    x = 0
                }
            }
        }
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