package com.anirudh.tripmonitor.onboarding.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.anirudh.tripmonitor.R
import com.anirudh.tripmonitor.onboarding.adapter.IntroAdapter
import com.anirudh.tripmonitor.onboarding.adapter.IntroPageTransformer
import com.anirudh.tripmonitor.onboarding.viewmodel.IntoViewModel
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import kotlinx.android.synthetic.main.intro_fragment_layout_1.*
import kotlinx.android.synthetic.main.intro_layout.*

class IntroActivity : AppCompatActivity() {

    private lateinit var mIntoViewModel: IntoViewModel
    private var dotsIndicator: DotsIndicator? = null
    private var mViewPager: ViewPager? = null
    private val titleList = ArrayList<String>()
    private val descList = ArrayList<String>()
    private val lottieList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro_layout)
        initViewModel()
        mViewPager = viewpager
        dotsIndicator = dots_indicator
        mViewPager!!.adapter = IntroAdapter(
            mIntoViewModel.getTitleListLiveData().value!!,
            mIntoViewModel.getDescListLiveData().value!!,
            mIntoViewModel.getLottieListLiveData().value!!,
            supportFragmentManager
        )
        dotsIndicator?.setViewPager(mViewPager!!)
        // Set a PageTransformer
        mViewPager!!.setPageTransformer(false, IntroPageTransformer(this))
        clickListeners()
    }

    private fun clickListeners() {
        nextTextView.setOnClickListener {
            mViewPager?.currentItem = mViewPager!!.currentItem + 1
        }
    }

    private fun initViewModel() {
        mIntoViewModel = ViewModelProvider(this).get(IntoViewModel::class.java)

    }

}