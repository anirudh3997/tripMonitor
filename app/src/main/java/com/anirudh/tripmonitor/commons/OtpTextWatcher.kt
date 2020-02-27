package com.anirudh.tripmonitor.commons

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText


class OtpTextWatcher() : TextWatcher {
    private lateinit var current: EditText
    private var prev: EditText? = null
    private var next: EditText? = null

    constructor(current: EditText, prev: EditText?, next: EditText?) : this() {
        this.current = current
        this.prev = prev
        this.next = next
    }

    override fun afterTextChanged(editable: Editable) {
        val text = editable.toString()
        if (text.length == 1) {
            next?.requestFocus()
        } else if (text.isEmpty()) {
            prev?.requestFocus()
        }
    }

    override fun beforeTextChanged(
        arg0: CharSequence?,
        arg1: Int,
        arg2: Int,
        arg3: Int
    ) {
        // TODO Auto-generated method stub
    }

    override fun onTextChanged(
        arg0: CharSequence?,
        arg1: Int,
        arg2: Int,
        arg3: Int
    ) {
        // TODO Auto-generated method stub
    }
}