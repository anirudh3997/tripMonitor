package com.anirudh.tripmonitor.login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.anirudh.tripmonitor.R
import com.anirudh.tripmonitor.commons.MyBounceInterpolator
import com.anirudh.tripmonitor.login.ui.LoginFragment
import com.anirudh.tripmonitor.login.ui.OtpFragment
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.login_fragment.*
import java.util.concurrent.TimeUnit


class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private var TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()
        }
        mAuth = FirebaseAuth.getInstance()

    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (null == currentUser) {

            callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    // This callback will be invoked in two situations:
                    // 1 - Instant verification. In some cases the phone number can be instantly
                    //     verified without needing to send or enter a verification code.
                    // 2 - Auto-retrieval. On some devices Google Play services can automatically
                    //     detect the incoming verification SMS and perform verification without
                    //     user action.
                    Log.e(TAG, "onVerificationCompleted:$credential")
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Log.e(TAG, "onVerificationFailed", e)
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    // The SMS verification code has been sent to the provided phone number, we
                    // now need to ask the user to enter the code and then construct a credential
                    // by combining the code with a verification ID.
//                    val otpFragment = OtpFragment()
//                    otpFragment.sharedElementEnterTransition = OtpTransition()
//                    otpFragment.enterTransition = Fade()
//                    otpFragment.exitTransition = Fade()
//                    otpFragment.sharedElementReturnTransition = OtpTransition()

                    Log.e(TAG, "onCodeSent:$verificationId")
                    val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                    ft.setCustomAnimations(
                        R.anim.slide_in_left,
                        R.anim.slide_out_right,
                        R.anim.slide_in_left,
                        R.anim.slide_out_right
                    )
                    ft.add(R.id.container, OtpFragment.newInstance())
                        .addToBackStack(null)
                        .commit()
                    // Save verification ID and resending token so we can use them later
                    // storedVerificationId = verificationId
                    // resendToken = token
                }
            }
            login_button.setOnClickListener {
                val myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce)
                val interpolator = MyBounceInterpolator(0.2, 15.0)
                myAnim.interpolator = interpolator
                it.startAnimation(myAnim)
                loginGetOtp()
            }
        }

    }

    private fun loginGetOtp() {
        if (login_phone_number.text.toString().isNotEmpty() &&
            login_phone_number.text.toString().length == 10
        ) {
            val phoneNumber = "+91" + login_phone_number.text.toString()
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber, // Phone number to verify
                120, // Timeout duration
                TimeUnit.SECONDS, // Unit of timeout
                this, // Activity (for callback binding)
                callbacks
            ) // OnVerificationStateChangedCallbacks
        } else {
            Toast.makeText(
                this,
                "Please enter a valid phone number",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}