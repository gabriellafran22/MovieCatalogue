package com.example.moviecatalogue.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewPropertyAnimator
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.MainActivity
import com.example.moviecatalogue.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val alphaFirst = 0f
    private val alphaSecond = 1f
    private val splashScreenDuration: Long = 500
    private var propertyAnim: ViewPropertyAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        binding.iconMovie.alpha = alphaFirst


        propertyAnim =
            binding.iconMovie.animate().setDuration(splashScreenDuration).alpha(alphaSecond)
                .withEndAction {
                    Intent(this, MainActivity::class.java).apply {
                        startActivity(this)
                        finish()
                    }
                }
    }

    override fun onDestroy() {
        propertyAnim?.cancel()
        super.onDestroy()
    }
}