package com.anirudh.tripmonitor.login.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.TransitionInflater
import com.anirudh.tripmonitor.R
import com.anirudh.tripmonitor.commons.MyBounceInterpolator
import com.anirudh.tripmonitor.commons.OtpTextWatcher
import kotlinx.android.synthetic.main.otp_fragment.*

class OtpFragment : Fragment() {

    private lateinit var leftBg: ImageView
    private lateinit var rightBg: ImageView
    private lateinit var loginButton: TextView

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
        loginButton = login_otp_button

        et1.addTextChangedListener(OtpTextWatcher(et1, null, et2))
        et2.addTextChangedListener(OtpTextWatcher(et2, et1, et3))
        et3.addTextChangedListener(OtpTextWatcher(et3, et2, et4))
        et4.addTextChangedListener(OtpTextWatcher(et4, et3, et5))
        et5.addTextChangedListener(OtpTextWatcher(et5, et4, et6))
        et6.addTextChangedListener(OtpTextWatcher(et6, et5, null))
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
        loginButton.setOnClickListener {
            Log.e(TAG, "clickListeners: ${et1.text}  ${et2.text}  ${et3.text}  ${et4.text}")

            val myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce)
            val interpolator = MyBounceInterpolator(0.2, 15.0)
            myAnim.interpolator = interpolator
            it.startAnimation(myAnim)
        }
    }
}
