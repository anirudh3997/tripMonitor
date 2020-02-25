package com.anirudh.tripmonitor.onboarding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewpager.widget.ViewPager

class IntoViewModel : ViewModel() {
    private val titleListLiveData = MutableLiveData<ArrayList<String>>()
    private val descListLiveData = MutableLiveData<ArrayList<String>>()
    private val lottieListLiveData = MutableLiveData<ArrayList<String>>()
    private lateinit var viewPager:ViewPager
    private val descList = ArrayList<String>()
    private val lottieList = ArrayList<String>()
    private val titleList = ArrayList<String>()

    init {
        setDescList()
        setLottieList()
        setTitleList()
    }

    private fun setDescList() {
        descList.add("This is Description 1")
        descList.add("This is Description 2")
        descList.add("This is Description 3")
        descList.add("This is Description 4")
        descList.add("This is Description 5")
        descList.add("This is Description 6")
        descListLiveData.value = descList
    }

    private fun setLottieList() {
        lottieList.add("review.json")
        lottieList.add("review2.json")
        lottieList.add("review.json")
        lottieList.add("review2.json")
        lottieList.add("review.json")
        lottieList.add("review2.json")
        lottieListLiveData.value = lottieList

    }

    private fun setTitleList() {
        titleList.add("Text 1")
        titleList.add("Text 2")
        titleList.add("Text 3")
        titleList.add("Text 4")
        titleList.add("Text 5")
        titleList.add("Text 6")
        titleListLiveData.value = titleList
    }

    fun getTitleListLiveData(): LiveData<ArrayList<String>> {
        return titleListLiveData
    }

    fun getDescListLiveData(): LiveData<ArrayList<String>> {
        return descListLiveData
    }

    fun getLottieListLiveData(): LiveData<ArrayList<String>> {
        return lottieListLiveData
    }

    fun getViewPager(): ViewPager {
        return viewPager
    }

    fun setViewPager(viewPager: ViewPager) {
        this.viewPager = viewPager
    }



}