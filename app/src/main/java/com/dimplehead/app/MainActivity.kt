package com.dimplehead.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dimplehead.app.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        val intent = Intent(this, Login::class.java)
        startActivity(intent)

        binding.btnSubmitCourse.setOnClickListener {


            val courseName = binding.etCourseName
            val courseAddress = binding.etCourseAddress
            val numOfHoles = binding.etNumOfHoles
            val coursePar = binding.etCoursePar
            val yearOpened = binding.etYearOpened
            val proOnsite = binding.etProOnsite
            val drivingRange = binding.etDrivingRange
            val rentalClubs = binding.etRentalClubs
            val rentalCarts = binding.etRentalCarts

            val courseNameTXT = courseName.text.toString()
            val courseAddressTXT = courseAddress.text.toString()
            val numOfHolesTXT = numOfHoles.text.toString()
            val courseParTXT = coursePar.text.toString()
            val yearOpenedTXT = yearOpened.text.toString()
            val proOnsiteTXT = proOnsite.text.toString()
            val drivingRangeTXT = drivingRange.text.toString()
            val rentalClubsTXT = rentalClubs.text.toString()
            val rentalCartsTXT = rentalCarts.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Courses")
            val course = Courses(courseNameTXT, courseAddressTXT, numOfHolesTXT, courseParTXT, yearOpenedTXT, proOnsiteTXT, drivingRangeTXT, rentalClubsTXT, rentalCartsTXT )

            database.child(courseNameTXT).setValue(course).addOnSuccessListener {

                courseName.text.clear()
                courseAddress.text.clear()
                numOfHoles.text.clear()
                coursePar.text.clear()
                yearOpened.text.clear()
                proOnsite.text.clear()
                drivingRange.text.clear()
                rentalClubs.text.clear()
                rentalCarts.text.clear()

                Toast.makeText(this, "Successfully Stored", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {

                Toast.makeText(this, "Failed to Store", Toast.LENGTH_SHORT).show()
            }
        }
    }
}