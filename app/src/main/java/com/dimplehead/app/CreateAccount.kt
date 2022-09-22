package com.dimplehead.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dimplehead.app.databinding.ActivityCreateAccountBinding
import com.dimplehead.app.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccount : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivityCreateAccountBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.signinButton.setOnClickListener {
            if(inputIsNotEmpty())
            {


                registerUser()
            }
            else
            {
                Toast.makeText(this, "All Input Required", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun inputIsNotEmpty(): Boolean {
        if (nameEditText.text.isNotEmpty() && dobEditText.text.isNotEmpty() && emailEditText.text.isNotEmpty()
            && passwordEditText.text.isNotEmpty() && yearsGolfingEditText.text.isNotEmpty())
        {
            return true;
        }
        return false;
    }

    private fun registerUser()
    {
        auth.createUserWithEmailAndPassword(emailEditText.text.trim().toString(), passwordEditText.text.trim().toString())
            .addOnCompleteListener {
            if(it.isSuccessful)
            {
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_LONG).show()

                database = FirebaseDatabase.getInstance().getReference("Users")
                val uid: String = auth.uid.toString()

                val user = Users(nameEditText.text.trim().toString(), dobEditText.text.trim().toString(),
                    emailEditText.text.trim().toString(), yearsGolfingEditText.text.trim().toString(), uid)

                database.child(uid).setValue(user)

                val intent = Intent(this, UserHome::class.java)
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this, "Registration Unsuccessful", Toast.LENGTH_LONG).show()
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