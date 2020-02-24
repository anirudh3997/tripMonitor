package com.anirudh.tripmonitor.onboarding.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.anirudh.tripmonitor.R
import com.anirudh.tripmonitor.onboarding.IntroPageTransformer
import com.anirudh.tripmonitor.onboarding.adapter.IntroAdapter

class IntroActivity: AppCompatActivity() {

    private var mViewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro_layout)
        mViewPager = findViewById(R.id.viewpager)
        // Set an Adapter on the ViewPager
        mViewPager!!.adapter = IntroAdapter(supportFragmentManager)
        // Set a PageTransformer
        mViewPager!!.setPageTransformer(false, IntroPageTransformer())
    }

}