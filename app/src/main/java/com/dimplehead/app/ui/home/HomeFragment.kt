package com.dimplehead.app.ui.home

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dimplehead.app.NewRound
import com.dimplehead.app.databinding.FragmentHomeBinding
import com.dimplehead.app.ui.slideshow.SlideshowFragment
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.playnewroundBtn.setOnClickListener {
            val intent = Intent(activity, NewRound::class.java)
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