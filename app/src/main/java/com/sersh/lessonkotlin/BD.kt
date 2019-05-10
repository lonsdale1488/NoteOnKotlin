package com.sersh.lessonkotlin

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class BD (ctx: Context) {
    init {
        mCtx = ctx
    }
  companion object {
      var KEY_DEALS = "deals"
      var KEY_CATEGORY = "category"
      var KEY_DATE = "dating"
      var table ="table1"
      private var NAMEBASE = "BDSQL"

     lateinit var  mCtx: Context
  }
    var LOGTAG = "DB"

    private val DB_VERSION = 1

    private lateinit var BD: SQLiteDatabase
    private lateinit var hDB: HelpDB


    fun openDB() {
        hDB = HelpDB(mCtx,NAMEBASE,null, DB_VERSION)
            BD = hDB.getWritableDatabase()
    }
    fun closeDB() {

         hDB.close()
    }

    fun deleteBD()
    {openDB()
        BD.delete(table, null, null )
        closeDB()
    }
    fun ADDBD ( dealsADD: String ,categoryADD: String, dateADD: String  )
    {  openDB()
        val contend = ContentValues ()
        contend.put(KEY_DEALS,dealsADD )
        contend.put(KEY_CATEGORY,categoryADD)
        contend.put(KEY_DATE,dateADD)
        val rowID = BD.insert(table, null, contend )
        Log.d(LOGTAG, "ADDBD -  $dealsADD, $categoryADD, $dateADD " )
      //  BD.update(table, contend , null, null )
        closeDB()
    }
    fun getAllData(): Cursor
    {
        openDB()
        Log.d(LOGTAG, "getAllData " )
        return BD.query(table, null, null, null, null, null,null )

    }
    class HelpDB(
        context: Context, name: String, factory: SQLiteDatabase.CursorFactory?,
        version: Int
    ) : SQLiteOpenHelper(context, name, factory, version) {
      //  private var KEY_DEALS = "deals"
      //  private var KEY_CATEGORY = "category"
       // private var KEY_DATE = "date"
        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL(
                "create table $table  (" +
                        "_id integer primary key autoincrement," +
                        "$KEY_DEALS text," +
                        "$KEY_CATEGORY text," +
                        "$KEY_DATE text" + ");")

            val cV = ContentValues()

            for (i in 1..4) {
                cV.put("id", "0")
                cV.put(KEY_DEALS, "sometext ")
                cV.put(KEY_CATEGORY, "sometext ")
                cV.put(KEY_DATE, "sometext ")
            }
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        }
    }
}