package com.anirudh.tripmonitor.commons

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.util.Log
import com.anirudh.tripmonitor.login.ui.OtpFragment
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class SmsReceiver : BroadcastReceiver() {

    private val mListener: SmsListener? = OtpFragment()

    override fun onReceive(context: Context?, intent: Intent?) {
        val data = intent?.extras
        if (data!!.get("pdus") != null) {
            val pdus = data.get("pdus") as Array<*>

            for (i in pdus.indices) {
                val smsMessage = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                val sender = smsMessage.displayOriginatingAddress
                val messageBody = smsMessage.messageBody
                Log.e("---->", "$sender     $messageBody")
                if (messageBody.toLowerCase(Locale.getDefault()).contains(
                        "verification code",
                        ignoreCase = true
                    )
                ) {
                    try {
                        Log.e("---->", "messagebody is $messageBody")
                        mListener!!.messageReceived(parseCode(messageBody))
                    } catch (e: NullPointerException) {
                    }
                }
            }
        }
    }

    private fun parseCode(msg: String): String {
        Log.e("---->", "msg is $msg")
        var otp = ""
        try {
            Log.e("---->", "msg is $msg")
            val pattern: Pattern = Pattern.compile("(\\b\\d{6}\\b)")
            val matcher: Matcher = pattern.matcher(msg)
            if (matcher.find()) {
                Log.e("---->", "mather is found")
                otp = matcher.group(0)!!
            }
            Log.e("---->", "otp is $otp")
        } catch (e: Exception) {
        }
        return otp
    }
}