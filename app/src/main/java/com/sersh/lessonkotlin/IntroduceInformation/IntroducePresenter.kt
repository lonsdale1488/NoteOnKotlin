package com.sersh.lessonkotlin.IntroduceInformation

import com.sersh.lessonkotlin.MainActivity

class IntroducePresenter (var introduseIView: IntroduceIView) {

val model by lazy { IntroduceModel(this) }


fun inPut (deals:String, category:String)
    {
        model.DEALS = deals
    }
    fun initAll ()
    {
        introduseIView.init()
    }


    fun getDEALS ():String
    {
      return  model.DEALS
    }
    fun outCATEGORYS (): String
    {
        return  model.CATEGORYS
    }
    fun outData (): String
    {
        return  model.DATES
    }
}