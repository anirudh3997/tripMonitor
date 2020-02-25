package com.anirudh.tripmonitor.onboarding.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anirudh.tripmonitor.R
import kotlinx.android.synthetic.main.intro_fragment_layout_1.view.*


class IntroFragment : Fragment() {
    private var title: String = ""
    private var desc: String = ""
    private var lottie: String = ""
    private var mPage = 0

    val TAG = "IntroFragment"
    private val TITLETEXT = "title"
    private val DESCTEXT = "desc"
    private val LOTTIETEXT = "lottie"
    private val PAGE = "page"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!arguments!!.containsKey(PAGE)) throw RuntimeException(
            "Fragment must contain a \"$PAGE\" argument!"
        )
        mPage = arguments!!.getInt(PAGE)
        title = arguments!!.getString(TITLETEXT).toString()
        desc = arguments!!.getString(DESCTEXT).toString()
        lottie = arguments!!.getString(LOTTIETEXT).toString()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutResId: Int = R.layout.intro_fragment_layout_1
        val view =
            activity!!.layoutInflater.inflate(layoutResId, container, false)
        view.tag = mPage
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.titleTextView.text = title
        view.descTextView.text = desc
        view.lottieAnimationView.setAnimation(lottie)
    }

    companion object {
        private const val PAGE = "page"
        private val TITLETEXT = "title"
        private val DESCTEXT = "desc"
        private val LOTTIETEXT = "lottie"

        fun newInstance(title: String, desc: String, lottie: String, page: Int): IntroFragment {
            val frag = IntroFragment()
            val b = Bundle()
            b.putString(TITLETEXT, title)
            b.putString(DESCTEXT, desc)
            b.putString(LOTTIETEXT, lottie)
            b.putInt(PAGE, page)
            frag.arguments = b
            return frag
        }
    }


}