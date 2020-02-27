package com.anirudh.tripmonitor.login.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.anirudh.tripmonitor.R
import com.anirudh.tripmonitor.commons.MyBounceInterpolator
import com.anirudh.tripmonitor.commons.OtpOnKeyListener
import com.anirudh.tripmonitor.commons.OtpText
import com.anirudh.tripmonitor.commons.OtpTextWatcher
import kotlinx.android.synthetic.main.otp_fragment.*


class OtpFragment : Fragment() {

    private lateinit var leftBg: ImageView
    private lateinit var rightBg: ImageView
    private lateinit var loginOtpButton: TextView

    private val TAG = OtpFragment::class.java.simpleName

    companion object {
        fun newInstance() = OtpFragment()
    }

    private lateinit var viewModel: OtpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.otp_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OtpViewModel::class.java)
        setupVariables()
        animateBgOpen()
        clickListeners()
    }

    private fun setupVariables() {
        leftBg = bg_left
        rightBg = bg_right
        loginOtpButton = login_otp_button
        et1.requestFocus()
        showKeyboard()
        textChangeListeners()
    }

    private fun textChangeListeners() {
        val editTexts: ArrayList<EditText> = ArrayList()
        editTexts.add(et1)
        editTexts.add(et2)
        editTexts.add(et3)
        editTexts.add(et4)
        editTexts.add(et5)
        editTexts.add(et6)

        et1.addTextChangedListener(OtpTextWatcher(0, editTexts, otpText))
        et2.addTextChangedListener(OtpTextWatcher(1, editTexts, otpText))
        et3.addTextChangedListener(OtpTextWatcher(2, editTexts, otpText))
        et4.addTextChangedListener(OtpTextWatcher(3, editTexts, otpText))
        et5.addTextChangedListener(OtpTextWatcher(4, editTexts, otpText))
        et6.addTextChangedListener(OtpTextWatcher(5, editTexts, otpText))

        et1.setOnKeyListener(OtpOnKeyListener(0, editTexts))
        et2.setOnKeyListener(OtpOnKeyListener(1, editTexts))
        et3.setOnKeyListener(OtpOnKeyListener(2, editTexts))
        et4.setOnKeyListener(OtpOnKeyListener(3, editTexts))
        et5.setOnKeyListener(OtpOnKeyListener(4, editTexts))
        et6.setOnKeyListener(OtpOnKeyListener(5, editTexts))
    }

    private fun animateBgOpen() {
        val animateLeftBg: ObjectAnimator = ObjectAnimator.ofFloat(leftBg, "x", -150f)
        val animateRightBg: ObjectAnimator = ObjectAnimator.ofFloat(rightBg, "x", 150f)
        animateLeftBg.duration = 1500
        animateRightBg.duration = 1500
        val animateSet = AnimatorSet()

        animateSet.playTogether(animateLeftBg, animateRightBg)
        animateSet.start()
    }


    private fun clickListeners() {
        loginOtpButton.setOnClickListener {
            Log.e(TAG, "clickListeners: ${et1.text}  ${et2.text}  ${et3.text}  ${et4.text}")

            val myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce)
            val interpolator = MyBounceInterpolator(0.2, 15.0)
            myAnim.interpolator = interpolator
            it.startAnimation(myAnim)
        }
    }

    private var otpText = object : OtpText {
        override fun hideKeyBoard() {
            val inputMethodManager =
                context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(et6.windowToken, 0)
        }
    }

    fun showKeyboard() {
        val inputMethodManager =
            context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            0
        )
    }
}
