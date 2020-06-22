package com.example.appmoneymanagersqlite.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.appmoneymanagersqlite.R
import com.example.appmoneymanagersqlite.databse.MyDBManager
import kotlinx.android.synthetic.main.activity_add_task.*

class AddTaskActivity : AppCompatActivity() {

    private var dbManager: MyDBManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        dbManager = MyDBManager(this)
        dbManager?.open()
//        Log.d("nnn", dbManager.)
    }

    fun onClickAddTask(view: View?) {
        val name = txtAddName?.text.toString()
        val kilo = txtAddKilo?.text.toString()
        val price = txtAddPrice?.text.toString()
        val address = txtAddAddress?.text.toString()

        if (name.length == 0 || kilo.length == 0 || price.length == 0 || address.length == 0) {
            return
        }
        Log.d("nnn", name + kilo + price + address)

        dbManager?.insert(name, kilo, price, address)
        val intent = Intent(this@AddTaskActivity, MainActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
