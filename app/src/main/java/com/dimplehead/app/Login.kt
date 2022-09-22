package com.dimplehead.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val createAccountbutton = findViewById<Button>(R.id.createAccountbutton)

        createAccountbutton.setOnClickListener {
            val intent = Intent(this, CreateAccount::class.java)
            startActivity(intent)
        }

        signinButton.setOnClickListener {
            signInUser()
        }
    }

    private fun signInUser()
    {
        auth.signInWithEmailAndPassword(emailAddressEditText.text.trim().toString(), passwordEditText.text.trim().toString())
            .addOnCompleteListener {
                if (it.isSuccessful)
                {
                    val intent = Intent(this, UserHome::class.java)
                    startActivity(intent)
                }
                else
                {
                    Toast.makeText(this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()

        val user = auth.currentUser

        if(user != null)
        {
            val intent = Intent(this, UserHome::class.java)
            startActivity(intent)
        }
    }
}