package com.anirudh.tripmonitor.onboarding.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anirudh.tripmonitor.R
import com.anirudh.tripmonitor.commons.Constants.CLICK_LISTENERS
import com.anirudh.tripmonitor.commons.Constants.DESC_TEXT
import com.anirudh.tripmonitor.commons.Constants.LOTTIE_TEXT
import com.anirudh.tripmonitor.commons.Constants.PAGE
import com.anirudh.tripmonitor.commons.Constants.SIZE
import com.anirudh.tripmonitor.commons.Constants.TITLE_TEXT
import com.anirudh.tripmonitor.onboarding.interfaces.ClickListeners
import kotlinx.android.synthetic.main.intro_fragment_layout.*
import kotlinx.android.synthetic.main.intro_fragment_layout.view.*


class IntroFragment : Fragment() {
    private var title: String = ""
    private var desc: String = ""
    private var lottie: String = ""
    private lateinit var clickListeners: ClickListeners
    private var mPage = 0
    private var size = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPage = arguments!!.getInt(PAGE)
        size = arguments!!.getInt(SIZE)
        title = arguments!!.getString(TITLE_TEXT).toString()
        desc = arguments!!.getString(DESC_TEXT).toString()
        lottie = arguments!!.getString(LOTTIE_TEXT).toString()
        clickListeners = arguments!!.getParcelable(CLICK_LISTENERS)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutResId: Int = R.layout.intro_fragment_layout
        val view =
            activity!!.layoutInflater.inflate(layoutResId, container, false)
        view.tag = mPage
        return view
    }

    private fun clickHandlers() {
        prevTextView.setOnClickListener {
            clickListeners.prevClicked()
        }
        nextTextView.setOnClickListener {
            clickListeners.nextClicked()
        }
        skipTextView.setOnClickListener {
            clickListeners.skipClicked()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.titleTextView.text = title
        view.descTextView.text = desc
        view.lottieAnimationView.setAnimation(lottie)
        clickHandlers()
        hideNavButtons()
    }

    private fun hideNavButtons() {
        if (mPage == 0) {
            prevTextView.visibility = INVISIBLE
            nextTextView.visibility = VISIBLE
        } else if (mPage == size - 1) {
            nextTextView.visibility = INVISIBLE
            prevTextView.visibility = VISIBLE
        }
    }

    companion object {
        fun newInstance(
            clickListeners: ClickListeners,
            title: String,
            desc: String,
            lottie: String,
            size: Int,
            page: Int
        ): IntroFragment {
            val frag = IntroFragment()
            val b = Bundle()
            b.putParcelable(CLICK_LISTENERS, clickListeners)
            b.putString(TITLE_TEXT, title)
            b.putString(DESC_TEXT, desc)
            b.putString(LOTTIE_TEXT, lottie)
            b.putInt(PAGE, page)
            b.putInt(SIZE, size)
            frag.arguments = b
            return frag
        }
    }


}