package com.example.exhibitappcrop2cash.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.exhibitappcrop2cash.R
import com.example.exhibitappcrop2cash.databinding.FragmentSplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment() {

    private lateinit var _binding: FragmentSplashScreenBinding
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSplashScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** this is to initialise animation **/
        val ttb = AnimationUtils.loadAnimation(requireContext(), R.anim.exhibit_animation)
        val ssb = AnimationUtils.loadAnimation(requireContext(), R.anim.db_animation)

        binding.tvExhibit.startAnimation(ttb)
        binding.tvDb.startAnimation(ssb)

        /** delay the splash screen for 3 secs before navigating to the next fragment **/
        val splashScreenTimeout = 3000
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.exhibitFragment)
        }, splashScreenTimeout.toLong())
    }

}