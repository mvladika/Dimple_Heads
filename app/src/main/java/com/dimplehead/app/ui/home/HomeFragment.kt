package com.dimplehead.app.ui.home

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dimplehead.app.Courses
import com.dimplehead.app.Login
import com.dimplehead.app.NewRound
import com.dimplehead.app.Users
import com.dimplehead.app.databinding.FragmentHomeBinding
import com.dimplehead.app.ui.slideshow.SlideshowFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var database: DatabaseReference

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var userBestScore: String? = null
    var userName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        var user: Users? = null
        database = Firebase.database.reference
        database.child("Users").child(FirebaseAuth.getInstance().uid.toString()).get().addOnSuccessListener {
            user = it.getValue(Users::class.java)!!
            userBestScore = user?.userBestScore
            userName = user?.userName
            homefragusername.text = userName
            homefraglastcourse.text = user?.lastCoursePlayed
            homefragfinalscoreoflastgame.text = user?.finalScoreOfLastRound
            homefragbestscore.text = userBestScore
        }.addOnFailureListener{
            Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
        }

        binding.playnewroundBtn.setOnClickListener {
            val intent = Intent(activity, NewRound::class.java)
            startActivity(intent)
        }

        binding.signoutbutton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(activity, Login::class.java)
            startActivity(intent)
        }

        binding.mystatisticsBtn.setOnClickListener {
            val nextFrag = SlideshowFragment()
            this.parentFragment?.let { it1 ->
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(it1.id, nextFrag)
                    .addToBackStack(null)
                    .commit()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}