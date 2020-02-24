package com.anirudh.tripmonitor.onboarding.adapter

import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.anirudh.tripmonitor.onboarding.ui.IntroFragment

class IntroAdapter internal constructor(fm: FragmentManager?) :
    FragmentPagerAdapter(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> IntroFragment.newInstance(
                Color.parseColor("#03A9F4"),
                position
            ) // blue
            else -> IntroFragment.newInstance(
                Color.parseColor("#4CAF50"),
                position
            ) // green
        }
    }

    override fun getCount(): Int {
        return 2
    }
}