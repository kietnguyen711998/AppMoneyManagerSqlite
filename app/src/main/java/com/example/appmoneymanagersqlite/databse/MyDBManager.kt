package com.example.appmoneymanagersqlite.databse

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class MyDBManager(private val context: Context) {
    private var dbHelper: DataBaseHelper? = null
    private var database: SQLiteDatabase? = null

    @Throws(SQLException::class)
    fun open(): MyDBManager {
        dbHelper = DataBaseHelper(context)
        database = dbHelper?.writableDatabase
        return this
    }

    fun close() {
        dbHelper!!.close()
    }

    fun insert(name: String?, kilo: String?, price: String?, address: String?) {
        val contentValue = ContentValues()
        contentValue.put(DataBaseHelper.NAME, name)
        contentValue.put(DataBaseHelper.KILO, kilo)
        contentValue.put(DataBaseHelper.PRICE, price)
        contentValue.put(DataBaseHelper.ADDRESS, address)
        database?.insert(DataBaseHelper.TABLE_NAME, null, contentValue)
        Log.d("nnn", "nnn")
    }

    fun fetch(): Cursor? {
        val columns =
            arrayOf(
                DataBaseHelper.ID,
                DataBaseHelper.NAME,
                DataBaseHelper.KILO,
                DataBaseHelper.PRICE,
                DataBaseHelper.ADDRESS
            )
        val cursor =
            database?.query(DataBaseHelper.TABLE_NAME, columns, null, null, null, null, null)
        cursor?.moveToFirst()
        return cursor
        Log.d("mmm", "mmm")
    }

    fun update(_id: Long, name: String?, kilo: String?, price: String?, address: String?): Int {
        val contentValues = ContentValues()
        contentValues.put(DataBaseHelper.NAME, name)
        contentValues.put(DataBaseHelper.KILO, kilo)
        contentValues.put(DataBaseHelper.PRICE, price)
        contentValues.put(DataBaseHelper.ADDRESS, address)
        return database!!.update(
            DataBaseHelper.TABLE_NAME,
            contentValues,
            DataBaseHelper.ID + " = " + _id,
            null
        )
    }

    fun delete(_id: Long) {
        database!!.delete(DataBaseHelper.TABLE_NAME, DataBaseHelper.ID + "=" + _id, null)
    }

}
