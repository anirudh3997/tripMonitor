package com.anirudh.tripmonitor

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.anirudh.tripmonitor.commons.Constants.IS_WALK_THROUGH_SHOWN
import com.anirudh.tripmonitor.commons.SharedPref
import com.anirudh.tripmonitor.onboarding.ui.IntroActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)
        OpenNextActivity()
    }

    private fun OpenNextActivity() {
        Handler().postDelayed({
            if (SharedPref().getSharedPrefBool(this, IS_WALK_THROUGH_SHOWN)) {
                val intent = Intent(applicationContext, IntroActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(applicationContext, IntroActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000)
    }
}
