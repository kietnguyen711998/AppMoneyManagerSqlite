package com.example.appmoneymanagersqlite.databse

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context?) :
    SQLiteOpenHelper(
        context,
        DATABASE_NAME,
        null,
        DATABASE_Version
    ) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "myDatabase" // Database Name
        const val TABLE_NAME = "myTable" // Table Name
        const val DATABASE_Version = 1 // Database Version
        const val ID = "_id"
        const val NAME = "name"
        const val KILO = "kilo"
        const val PRICE = "price"
        const val ADDRESS = "address"


        private const val CREATE_TABLE =
            ("create table " + TABLE_NAME + "(" + ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NAME + " TEXT, "
                    + KILO + " TEXT, "
                    + PRICE + " TEXT, "
                    + ADDRESS + " TEXT);")
    }
}
