package com.sersh.lessonkotlin.IntroduceInformation

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.sersh.lessonkotlin.MainActivity
import android.app.Activity
import android.util.Log


class IntroduceFragment : Fragment(), IntroduceIView {
    override fun getComtx(): Context {

        return activity!!
         }

    val presenter by lazy { IntroducePresenter(this) }
    private lateinit var deal: EditText
    private lateinit var type: EditText
    private lateinit var date: EditText
    private lateinit var root: View
    private lateinit var ok: Button
    private lateinit var cencel: Button
    var LOGTAG = "IntroduceFragment"



    internal lateinit var callback: OnHeadlineSelectedListener

//    fun setOnHeadlineSelectedListener(callback: OnHeadlineSelectedListener) {
//        this.callback = callback
//    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(com.sersh.lessonkotlin.R.layout.fragment_dialog, container, false)
        presenter.initAll()
        return root
    }

    override fun init() {

        deal = root.findViewById(com.sersh.lessonkotlin.R.id.deals)
        type = root.findViewById(com.sersh.lessonkotlin.R.id.type)
        date = root.findViewById(com.sersh.lessonkotlin.R.id.date)
        ok = root.findViewById(com.sersh.lessonkotlin.R.id.ok)
        cencel = root.findViewById(com.sersh.lessonkotlin.R.id.cencel)
        initLisener()
    }

    fun initLisener() {
        ok.setOnClickListener {
            Log.d(LOGTAG,"initLisener()" )
            presenter.inPut(deal.text.toString(),type.text.toString() )



            sendMessenge ()


        }
        cencel.setOnClickListener {
            (activity as MainActivity).showListFragment()
        }
    }



    fun sendMessenge ()
    {
        callback.setInf(presenter.getDEALS(), presenter.outCATEGORYS(), presenter.outData())
        Log.d(LOGTAG,"sendMessenge " )
    }

    interface OnHeadlineSelectedListener {
        fun setInf( deal: String, type: String, date: String )

    }

    override fun onAttach(activity: Activity?) {

        super.onAttach(activity)
        if (activity is OnHeadlineSelectedListener) {
            callback = activity as OnHeadlineSelectedListener
        } else
            throw ClassCastException()

    }
}
