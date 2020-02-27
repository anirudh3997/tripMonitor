package com.anirudh.tripmonitor.commons

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import com.anirudh.tripmonitor.R


class OtpTextWatcher(
    private var currentIndex: Int,
    private var editTexts: List<EditText>,
    private var otpText: OtpText
) :
    TextWatcher {
    private var isFirst = false
    private var isLast = false
    private var newTypedString = ""

    init {
        if (currentIndex == 0)
            this.isFirst = true
        else if (currentIndex == editTexts.size - 1)
            this.isLast = true
    }

    override fun afterTextChanged(editable: Editable) {

        var text: String = newTypedString

        if (text.length > 1) {
            text = text[0].toString()
        }
        editTexts[currentIndex].removeTextChangedListener(this)
        editTexts[currentIndex].setText(text)
        editTexts[currentIndex].setSelection(text.length)
        editTexts[currentIndex].addTextChangedListener(this)

        if (text.length == 1) {
            moveToNext()
        } else if (text.isEmpty()) {
            moveToPrevious()
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
        s: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) {
        newTypedString = s?.subSequence(start, start + count).toString().trim()
    }

    private fun moveToNext() {
        if (!isLast) {
            for (editText in editTexts){
                editText.removeFocus()
            }
            editTexts[currentIndex + 1].requestFocus()
            editTexts[currentIndex + 1].focus()
        }
        if (isAllEditTextsFilled() && isLast) { // isLast is optional
            editTexts[currentIndex].clearFocus()
            editTexts[currentIndex].removeFocus()
            otpText.hideKeyBoard()
        }
    }

    private fun moveToPrevious() {
        if (!isFirst) {
            for (editText in editTexts){
                editText.removeFocus()
            }
            editTexts[currentIndex - 1].requestFocus()
            editTexts[currentIndex - 1].focus()
        }
    }

    private fun isAllEditTextsFilled(): Boolean {
        for (editText in editTexts) {
            if (editText.text.toString().trim { it <= ' ' }.isEmpty()) {
                return false
            }
        }
        return true
    }

    private fun EditText.focus() {
        setBackgroundResource(R.drawable.otp_et_focused)
    }

    private fun EditText.removeFocus() {
        setBackgroundResource(R.drawable.otp_et)
    }
}


class OtpOnKeyListener(private var currentIndex: Int, private var editTexts: List<EditText>) :
    View.OnKeyListener {

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
            if (editTexts[currentIndex].text.toString().isEmpty() && currentIndex != 0) {
                for (editText in editTexts){
                    editText.removeFocus()
                }
                editTexts[currentIndex - 1].requestFocus()
                editTexts[currentIndex - 1].focused()
            } else {
                editTexts[currentIndex].text.clear()
                editTexts[currentIndex].requestFocus()
                for (editText in editTexts){
                    editText.removeFocus()
                }
                editTexts[currentIndex].focused()

            }
        }
        return false
    }

    private fun EditText.focused() {
        setBackgroundResource(R.drawable.otp_et_focused)
    }

    private fun EditText.removeFocus() {
        setBackgroundResource(R.drawable.otp_et)
    }
}