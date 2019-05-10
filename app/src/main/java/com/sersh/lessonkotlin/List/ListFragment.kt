package com.sersh.lessonkotlin.List

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.support.v4.widget.SimpleCursorAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sersh.lessonkotlin.BD
import com.sersh.lessonkotlin.BD.Companion.KEY_CATEGORY
import com.sersh.lessonkotlin.BD.Companion.KEY_DATE
import com.sersh.lessonkotlin.BD.Companion.KEY_DEALS
import com.sersh.lessonkotlin.MainActivity
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor>, ListIView {


    val presenter by lazy { ListPresenter(this) }


    var LOG_TAG: String = "ListFragment"
    lateinit var root: View


    var bd: BD? = null
    lateinit var scAdapter: SimpleCursorAdapter
    val from = arrayOf(KEY_DEALS, KEY_CATEGORY, KEY_DATE)
    val to = intArrayOf(
        com.sersh.lessonkotlin.R.id.tvText1
//        ,
//        com.sersh.lessonkotlin.R.id.tvText2,
//        com.sersh.lessonkotlin.R.id.tvText3
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = BD(activity as MainActivity)
        Log.d(LOG_TAG, "onCreateView")
        root = inflater.inflate(com.sersh.lessonkotlin.R.layout.fragment_list, container, false)
        return root
    }

    override fun onResume() {
        bd?.ADDBD(presenter.listModel.deals, presenter.listModel.type, presenter.listModel.type)
        adapterinit()
        initLisener()
        getLoaderManager().initLoader<Cursor>(0, null, this)
        super.onResume()
    }

    private fun initLisener() {
        delete.setOnClickListener {
            val toast = Toast.makeText(getActivity(), "delete", Toast.LENGTH_SHORT).show()
            Log.d(LOG_TAG, "delete")
            bd?.deleteBD()
            adapterinit()
        }
        add?.setOnClickListener {


            (activity as MainActivity).showDialog()
        }
        sort?.setOnClickListener {

        }


    }


    fun adapterinit() {

        scAdapter = SimpleCursorAdapter(activity, com.sersh.lessonkotlin.R.layout.item, null, from, to, 0)
        listXT.setAdapter(scAdapter)
        Log.d(LOG_TAG, "adapterinit() ")
    }

    override fun onCreateLoader(id: Int, bndl: Bundle?): Loader<Cursor> {
        Log.d(LOG_TAG, "onCreateLoader ")
        return MyCursorLoader(activity!!, bd!!)
    }

    override fun onLoadFinished(loader: Loader<Cursor>, cursor: Cursor) {
        scAdapter.swapCursor(cursor)
        Log.d(LOG_TAG, "onLoadFinished")
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        scAdapter.swapCursor(null)
        Log.d(LOG_TAG, "onLoaderReset")
    }

    internal class MyCursorLoader(context: Context, val db: BD) : CursorLoader(context) {

        override fun loadInBackground(): Cursor? {

            val cursor = db.getAllData()
            return cursor
        }

    }

    fun addBD(deals: String, type: String, time: String) {
        presenter.enterInform(deals, type, time)
    }


}



