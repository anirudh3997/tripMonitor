package com.anirudh.tripmonitor.login.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.anirudh.tripmonitor.R
import kotlinx.android.synthetic.main.login_fragment.*


class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var leftBg: ImageView
    private lateinit var rightBg: ImageView
    private lateinit var loginButton: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        setupVariables()
        animateBgOpen()
        editTextListener()
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

    private fun editTextListener() {
        login_phone_number.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 10) {
                    hideKeyBoard()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    private fun hideKeyBoard() {
        val inputMethodManager =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(login_phone_number.windowToken, 0)
    }

}
