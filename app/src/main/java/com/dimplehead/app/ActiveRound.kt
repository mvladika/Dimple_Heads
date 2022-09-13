package com.dimplehead.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ActiveRound : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_round)

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
            if(x == 0)
            {
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
            }
            else if (x == 1)
            {
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