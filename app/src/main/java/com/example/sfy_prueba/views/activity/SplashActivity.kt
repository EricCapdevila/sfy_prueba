package com.example.sfy_prueba.views.activity

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.sfy_prueba.R

class SplashActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        Handler(Looper.getMainLooper()).postDelayed( Runnable {
            showNextActivity()
            finish()
        }, SPLASH_DELAY.toLong())
    }

    private fun showNextActivity(){
        getPreferences(Context.MODE_PRIVATE).let {
            if(it.contains(getString(R.string.shared_preference_onboarding_done))) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                it.edit().putString(getString(R.string.shared_preference_onboarding_done), "").apply()
                startActivity(Intent(this, OnboardingActivity::class.java))
            }
        }
    }


    companion object {
        private const val SPLASH_DELAY = 1000
        private const val TAG = "MainActivity"
    }

   }

