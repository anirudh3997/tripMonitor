package com.anirudh.tripmonitor.onboarding.adapter

import android.view.View
import androidx.viewpager.widget.ViewPager
import com.anirudh.tripmonitor.R

class IntroPageTransformer : ViewPager.PageTransformer {
    override fun transformPage(
        page: View,
        position: Float
    ) {
        val pagePosition = page.tag as Int
        val pageWidth = page.width
        val pageWidthTimesPosition = pageWidth * position
        val absPosition = Math.abs(position)
        if (position <= -1.0f || position >= 1.0f) {

        } else if (position == 0.0f) {

        } else {

            val title = page.findViewById<View>(
                R.id.title
            )
            title.alpha = 1.0f - absPosition
            val description = page.findViewById<View>(
                R.id.description
            )
            description.translationY = -pageWidthTimesPosition / 2f
            description.alpha = 1.0f - absPosition
            val computer = page.findViewById<View>(
                R.id.computer
            )
            if (pagePosition == 0 && computer != null) {
                computer.alpha = 1.0f - absPosition
                computer.translationX = -pageWidthTimesPosition * 1.5f
            }
            if (position < 0) {

            } else {

            }
        }
    }
}