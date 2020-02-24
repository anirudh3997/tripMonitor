package com.anirudh.tripmonitor.login.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.anirudh.tripmonitor.R
import com.anirudh.tripmonitor.R.layout.login_fragment
import com.anirudh.tripmonitor.commons.MyBounceInterpolator
import kotlinx.android.synthetic.main.login_fragment.*


class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var leftBg: ImageView
    private lateinit var rightBg: ImageView
    private lateinit var loginButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        setupVariables()
        animateBgOpen()
        clickListeners()
        // TODO: Use the ViewModel
    }

    private fun clickListeners() {
        loginButton.setOnClickListener {
            val myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce)
            val interpolator = MyBounceInterpolator(0.2, 15.0)
            myAnim.interpolator = interpolator
            it.startAnimation(myAnim)
        }
    }

    private fun setupVariables() {
        leftBg = bg_left
        rightBg = bg_right
        loginButton = login_button
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

    private fun animateBgClose() {
        val animateLeftBg: ObjectAnimator = ObjectAnimator.ofFloat(leftBg, "x", 100f)
        val animateRightBg: ObjectAnimator = ObjectAnimator.ofFloat(rightBg, "x", 0f)
        animateLeftBg.duration = 2000
        animateRightBg.duration = 2000
        val animateSet = AnimatorSet()
        animateSet.playTogether(animateLeftBg, animateRightBg)
        animateSet.start()

    }

}
