package com.sersh.lessonkotlin.List

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.support.v4.widget.SimpleCursorAdapter
import android.util.Log
import com.sersh.lessonkotlin.BD


class ListPresenter (var listIView: ListIView) {

  val listModel by lazy { ListModel(this) }


    fun enterInform (deals:String, type:String, time:String )
    {
        listModel.deals = deals
        listModel.type = type
        listModel.time = time
    }



    var LOG_TAG: String = "ListModel"
    var bd: BD? = null
    lateinit var scAdapter: SimpleCursorAdapter
    val from = arrayOf(BD.KEY_DEALS, BD.KEY_CATEGORY, BD.KEY_DATE)
    val to = intArrayOf(
        com.sersh.lessonkotlin.R.id.tvText1
        //,
//        com.sersh.lessonkotlin.R.id.tvText2,
//        com.sersh.lessonkotlin.R.id.tvText3
    )












}