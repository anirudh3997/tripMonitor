package com.anirudh.tripmonitor.commons

interface SmsListener {
    fun messageReceived(messageText: String)
}
