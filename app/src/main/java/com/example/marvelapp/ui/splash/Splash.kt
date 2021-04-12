package com.example.marvelapp.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.marvelapp.R

class Splash : Fragment(R.layout.splash_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Handler().postDelayed({
            Navigation.findNavController(view).navigate(R.id.action_splash_to_mainFragment)
        }, 3000)
        super.onViewCreated(view, savedInstanceState)
    }
}