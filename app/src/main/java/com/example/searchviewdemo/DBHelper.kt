package com.example.searchviewdemo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.SQLException

private val dbname = "myDB"
private val dbTable = "Demo"
private val version =1

class DBHelper(context: Context?, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, dbname,factory,version){
    override fun onCreate(p0: SQLiteDatabase?) {
        try {
            p0!!.execSQL("CREATE TABLE " + dbTable + "(roll_no TEXT, name TEXT)")
        }catch(e: SQLException){
            e.printStackTrace()
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE "+ dbTable)
    }

    fun get(): ArrayList<Student>? {
        lateinit var list: ArrayList<Student>
        val db = this.readableDatabase
        list = ArrayList()
        var cursor = db.rawQuery("SELECT * FROM "+ dbTable, null)
        if(cursor.moveToFirst()) {
            do {
                val roll_no: String = cursor.getString(0)
                val name: String = cursor.getString(1)
                list.add(Student(roll_no,name))
            } while (cursor.moveToNext())
        }
        return list
    }

    fun insert(roll_no:String,name:String){
        val db = this.writableDatabase
        val values = ContentValues()

        values.put("roll_no",roll_no)
        values.put("name",name)
        db.insert(dbTable,null,values)

    }

}