package com.anirudh.tripmonitor.onboarding.adapter

import android.content.Context
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.anirudh.tripmonitor.R
import kotlin.math.abs

class IntroPageTransformer(val context: Context) : ViewPager.PageTransformer {
    override fun transformPage(
        page: View,
        position: Float
    ) {
        val pageWidth = page.width
        val pageWidthTimesPosition = pageWidth * position
        val absPosition = abs(position)

        val bgLayout = page.findViewById<View>(R.id.bgLinearLayout)
        val prevTextView = page.findViewById<View>(R.id.prevTextView)
        val nextTextView = page.findViewById<View>(R.id.nextTextView)
        val skipTextView = page.findViewById<View>(R.id.skipTextView)
        val lottieAnimationView = page.findViewById<View>(R.id.lottieAnimationView)
        val titleTextView = page.findViewById<View>(R.id.titleTextView)
        val descTextView = page.findViewById<View>(R.id.descTextView)

        bgLayout.translationX = -pageWidthTimesPosition
        prevTextView.translationX = -pageWidthTimesPosition
        nextTextView.translationX = -pageWidthTimesPosition
        skipTextView.translationX = -pageWidthTimesPosition

        if (position < 0) {
            lottieAnimationView.translationX = -(absPosition*1000)
            titleTextView.translationX = -(absPosition*1200)
            descTextView.translationX = -(absPosition*600)
        } else {
            lottieAnimationView.translationX = (absPosition*1000)
            titleTextView.translationX = (absPosition*1200)
            descTextView.translationX = (absPosition*600)
        }

    }
}