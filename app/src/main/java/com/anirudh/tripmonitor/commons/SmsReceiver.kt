package com.anirudh.tripmonitor.commons

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.util.Log
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class SmsReceiver : BroadcastReceiver() {

    private val mListener: SmsListener? = null

    override fun onReceive(context: Context?, intent: Intent?) {
//        val bundle = intent!!.extras
//        val messages: Array<SmsMessage>?
//        messages = parseSmsMessage(bundle)
//
//        if (messages != null && messages.isNotEmpty()) {
//            val sender = messages[0].originatingAddress
//            val contents = messages[0].messageBody.toString()
//            val receivedDate = Date(messages[0].timestampMillis)
//        }

        val data = intent?.extras
        if (data!!.get("pdus") != null) {
            val pdus = data.get("pdus") as Array<*>

            for (i in pdus.indices) {
                val smsMessage = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                val sender = smsMessage.displayOriginatingAddress
                val messageBody = smsMessage.messageBody
                Log.e("---->", "$sender     $messageBody")
                if (messageBody.toLowerCase(Locale.ROOT).contains(
                        "verification code",
                        ignoreCase = true
                    )
                ) {
                    try {
                        mListener!!.messageReceived(parseCode(messageBody))
                    } catch (e: NullPointerException) {
                    }
                }
            }
        }
    }

    private fun parseCode(msg: String): String {
        var otp = ""
        try {
            val pattern: Pattern = Pattern.compile("(\\d{6})")
            val matcher: Matcher = pattern.matcher(msg)
            if (matcher.find()) {
                otp = matcher.group(0)!!
            }
            Log.e("---->", "otp is $otp")
        } catch (e: Exception) {
        }
        return otp
    }
}