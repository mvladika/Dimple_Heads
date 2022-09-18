package com.dimplehead.app

import android.app.Dialog
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_active_round.*
import kotlinx.android.synthetic.main.activity_new_round.*

class ActiveRound : AppCompatActivity() {

    var x = 0;
    var parMap = HashMap<Int, String> ()


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

            h1par.setOnClickListener{
                if(x == 0)
                {
                    showCustomDialog(h1par, 1)
                }
                else if (x == 1)
                {
                    showCustomDialog(h1par, 10)
                }
            }
            h2par.setOnClickListener{
                if(x == 0)
                {
                    showCustomDialog(h2par, 2)
                }
                else if (x == 1)
                {
                    showCustomDialog(h2par, 11)
                }
            }
            h3par.setOnClickListener{
                if(x == 0)
                {
                    showCustomDialog(h3par, 3)
                }
                else if (x == 1)
                {
                    showCustomDialog(h3par, 12)
                }
            }
            h4par.setOnClickListener{
                if(x == 0)
                {
                    showCustomDialog(h4par, 4)
                }
                else if (x == 1)
                {
                    showCustomDialog(h4par, 13)
                }
            }
            h5par.setOnClickListener{
                if(x == 0)
                {
                    showCustomDialog(h5par, 5)
                }
                else if (x == 1)
                {
                    showCustomDialog(h5par, 14)
                }
            }
            h6par.setOnClickListener{
                if(x == 0)
                {
                    showCustomDialog(h6par, 6)
                }
                else if (x == 1)
                {
                    showCustomDialog(h6par, 15)
                }
            }
            h7par.setOnClickListener{
                if(x == 0)
                {
                    showCustomDialog(h7par, 7)
                }
                else if (x == 1)
                {
                    showCustomDialog(h7par, 16)
                }
            }
            h8par.setOnClickListener{
                if(x == 0)
                {
                    showCustomDialog(h8par, 8)
                }
                else if (x == 1)
                {
                    showCustomDialog(h8par, 17)
                }
            }
            h9par.setOnClickListener{
                if(x == 0)
                {
                    showCustomDialog(h9par, 9)
                }
                else if (x == 1)
                {
                    showCustomDialog(h9par, 18)
                }
            }

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
                    clearallPars()
                    x = 1

                    if(parMap.containsKey(10))
                    {
                        h1par.text = parMap[10].toString()
                    }
                    if(parMap.containsKey(11))
                    {
                        h2par.text = parMap[11].toString()
                    }
                    if(parMap.containsKey(12))
                    {
                        h3par.text = parMap[12].toString()
                    }
                    if(parMap.containsKey(13))
                    {
                        h4par.text = parMap[13].toString()
                    }
                    if(parMap.containsKey(14))
                    {
                        h5par.text = parMap[14].toString()
                    }
                    if(parMap.containsKey(15))
                    {
                        h6par.text = parMap[15].toString()
                    }
                    if(parMap.containsKey(16))
                    {
                        h7par.text = parMap[16].toString()
                    }
                    if(parMap.containsKey(17))
                    {
                        h8par.text = parMap[17].toString()
                    }
                    if(parMap.containsKey(18))
                    {
                        h9par.text = parMap[18].toString()
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
                    clearallPars()
                    x = 0

                    if(parMap.containsKey(1))
                    {
                        h1par.text = parMap[1].toString()
                    }
                    if(parMap.containsKey(2))
                    {
                        h2par.text = parMap[2].toString()
                    }
                    if(parMap.containsKey(3))
                    {
                        h3par.text = parMap[3].toString()
                    }
                    if(parMap.containsKey(4))
                    {
                        h4par.text = parMap[4].toString()
                    }
                    if(parMap.containsKey(5))
                    {
                        h5par.text = parMap[5].toString()
                    }
                    if(parMap.containsKey(6))
                    {
                        h6par.text = parMap[6].toString()
                    }
                    if(parMap.containsKey(7))
                    {
                        h7par.text = parMap[7].toString()
                    }
                    if(parMap.containsKey(8))
                    {
                        h8par.text = parMap[8].toString()
                    }
                    if(parMap.containsKey(9))
                    {
                        h9par.text = parMap[9].toString()
                    }
                }
            }
        }
    }

    private fun clearallPars() {
        h1par.text = getString(R.string._1)
        h2par.text = getString(R.string._1)
        h3par.text = getString(R.string._1)
        h4par.text = getString(R.string._1)
        h5par.text = getString(R.string._1)
        h6par.text = getString(R.string._1)
        h7par.text = getString(R.string._1)
        h8par.text = getString(R.string._1)
        h9par.text = getString(R.string._1)
    }

    private fun showCustomDialog(parTextView: TextView, numberHole: Int) {
        val dialog = Dialog(this)

        var x = 0

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