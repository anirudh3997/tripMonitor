package com.anirudh.tripmonitor.onboarding.ui

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.anirudh.tripmonitor.R
import com.anirudh.tripmonitor.commons.Constants.IS_WALK_THROUGH_SHOWN
import com.anirudh.tripmonitor.commons.SharedPref
import com.anirudh.tripmonitor.login.LoginActivity
import com.anirudh.tripmonitor.onboarding.adapter.IntroAdapter
import com.anirudh.tripmonitor.onboarding.adapter.IntroPageTransformer
import com.anirudh.tripmonitor.onboarding.interfaces.ClickListeners
import com.anirudh.tripmonitor.onboarding.viewmodel.IntoViewModel
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import kotlinx.android.synthetic.main.intro_layout.*


class IntroActivity : AppCompatActivity() {

    private lateinit var mIntoViewModel: IntoViewModel
    private var dotsIndicator: DotsIndicator? = null
    private lateinit var mViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro_layout)
        init()
    }

    private fun init() {
        initViewModel()
        SharedPref().saveSharedPrefBool(this, IS_WALK_THROUGH_SHOWN, true)
        setUpViewPager()
        setUpStatusBar()
    }

    private fun setUpStatusBar() {
        val window: Window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
    }

    private fun setUpViewPager() {
        mViewPager = viewpager
        dotsIndicator = dots_indicator
        mViewPager.adapter = IntroAdapter(
            clickListeners,
            mIntoViewModel.getTitleListLiveData().value!!,
            mIntoViewModel.getDescListLiveData().value!!,
            mIntoViewModel.getLottieListLiveData().value!!,
            supportFragmentManager
        )
        dotsIndicator?.setViewPager(mViewPager)
        // Set a PageTransformer
        mViewPager.setPageTransformer(false, IntroPageTransformer(this))
        mIntoViewModel.setViewPager(mViewPager)
    }

    private fun initViewModel() {
        mIntoViewModel = ViewModelProvider(this).get(IntoViewModel::class.java)
    }

    private var clickListeners: ClickListeners = object : ClickListeners {
        override fun prevClicked() {
            if (mViewPager.currentItem > 0) {
                mViewPager.currentItem = mViewPager.currentItem - 1
            }
        }

        override fun nextClicked() {
            if (mViewPager.currentItem < mViewPager.adapter?.count!! - 1) {
                mViewPager.currentItem = mViewPager.currentItem + 1
            }
        }

        override fun skipClicked() {
            val intent = Intent(this@IntroActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            //Not Used
        }

        override fun describeContents(): Int {
            return 0
        }
    }
}