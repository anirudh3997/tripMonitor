package com.anirudh.tripmonitor.onboarding.interfaces

import android.os.Parcelable

interface ClickListeners : Parcelable {
    fun prevClicked()
    fun nextClicked()
    fun skipClicked()
}