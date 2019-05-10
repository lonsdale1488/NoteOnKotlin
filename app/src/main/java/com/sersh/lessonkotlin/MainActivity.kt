package com.sersh.lessonkotlin


import android.annotation.SuppressLint
import android.app.AlertDialog

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.sersh.lessonkotlin.IntroduceInformation.IntroduceFragment
import com.sersh.lessonkotlin.List.ListFragment
import com.sersh.lessonkotlin.R.*


class MainActivity : AppCompatActivity()
   , IntroduceFragment.OnHeadlineSelectedListener
{



    @SuppressLint("ResourceType")
    override fun setInf(deal: String, type: String, date: String) {

    //    showListFragment ()
        val listFragmen = supportFragmentManager.findFragmentById(R.layout.fragment_list) as ListFragment?



        if(listFragmen!= null && listFragmen.isInLayout())
        {
            listFragmen.addBD(deal,type, date )
        }
        else
        {
           val newFragment = ListFragment ()
            newFragment.addBD(deal,type, date )
            replasefragment(newFragment)
            Toast.makeText(this, "Error Sending Message", Toast.LENGTH_SHORT).show();
        }

    }



    var LOG_TAG:String = "MainActivity"
//    var deals = ""
//    var type = ""
//    var time = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.main)
        showListFragment ()
    }





    fun replasefragment (fragment: Fragment){
        val fragmentMeneger = getSupportFragmentManager()
        fragmentMeneger.beginTransaction()
            .replace(id.frame, fragment)
            .addToBackStack(null)
            .commit()
    }
    fun addfragment (fragment: Fragment){
        val fragmentMeneger = getSupportFragmentManager()
        fragmentMeneger.beginTransaction()
            .add(id.frame, fragment)
            .addToBackStack(null)
            .commit()
    }


    fun showListFragment ()
    { Log.d(LOG_TAG, "show list Fragment")
        replasefragment(ListFragment())
    }

//    fun showListFragment ( DEALS:String, CATEGORYS:String, DATA:String )
//
//    {
//
//        replasefragment(ListFragment())
//    }

    fun showDialog ()
    {
        replasefragment(IntroduceFragment())
    }

    override fun onPause() {
        Log.d(LOG_TAG, "onPause() work")
//        bd.closeDB()
        super.onPause()
    }


  override  fun onBackPressed()
    {
        val fm = supportFragmentManager
        if (fm.backStackEntryCount > 1) {
            fm.popBackStackImmediate()
        } else {
            val bulder = AlertDialog.Builder(this)
            bulder.setTitle("Do you want exit?").setPositiveButton("ok", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, id: Int) {
                    finish()
                }
            }
            ).setNegativeButton("cencel", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, id: Int) {
                }
            }
            ).show()
            Log.d(LOG_TAG, " onBackPressed()")
        }

    }


//    fun initAdapter ()
//    {
//        bd.openDB()
//
//
//        // формируем столбцы сопоставления
//
//        val from = arrayOf("name", "time", "expirationTime")
//        val to = intArrayOf(R.id.tvText1, R.id.tvText2, R.id.tvText3)
//
//        // создаем адаптер и настраиваем список
//        scAdapter = SimpleCursorAdapter(this, R.layout.item, null, from, to, 0)
//        lvData = findViewById(R.id.listXT) as ListView
//        lvData.adapter = scAdapter
//        // создаем лоадер для чтения данных
//        supportLoaderManager.initLoader(0, null, this)
//    }












//    override fun onCreateLoader(id: Int, bndl: Bundle?): Loader<Cursor> {
//        return MyCursorLoader(this, bd)
//    }
//
//    override fun onLoadFinished(loader: Loader<Cursor>, cursor: Cursor) {
//        scAdapter.swapCursor(cursor)
//    }
//
//    override fun onLoaderReset(loader: Loader<Cursor>) {}
//
//    internal class MyCursorLoader(context: Context, var bd: BD) : CursorLoader(context) {
//
//        override fun loadInBackground(): Cursor? {
////            try {
//            //                TimeUnit.SECONDS.sleep(3);
//            //            } catch (InterruptedException e) {
//            //                e.printStackTrace();
//            //            }
//            return bd.getAllData()
//        }
//
//    }
}
