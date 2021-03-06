package com.anirudh.tripmonitor.onboarding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.anirudh.tripmonitor.onboarding.interfaces.ClickListeners
import com.anirudh.tripmonitor.onboarding.ui.IntroFragment

class IntroAdapter internal constructor(
    private val clickListeners:ClickListeners,
    private val titleList: ArrayList<String>,
    private val descList: ArrayList<String>,
    private val lottieList: ArrayList<String>,
    supportFragmentManager: FragmentManager
) :
    FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return IntroFragment.newInstance(
            clickListeners,
            titleList[position],
            descList[position],
            lottieList[position],
            titleList.size,
            position
        )
    }

    override fun getCount(): Int {
        return titleList.size
    }
}