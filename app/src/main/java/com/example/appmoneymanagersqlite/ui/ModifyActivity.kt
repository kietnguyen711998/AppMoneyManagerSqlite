package com.example.appmoneymanagersqlite.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.appmoneymanagersqlite.R
import com.example.appmoneymanagersqlite.databse.MyDBManager
import kotlinx.android.synthetic.main.activity_modify.*

class ModifyActivity : AppCompatActivity() {

    private var uid: Long? = null
    private var dbManager: MyDBManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify)
        dbManager = MyDBManager(this)
        dbManager?.open()
        val intent = intent
        val id = intent.getStringExtra("id")
        val name = intent.getStringExtra("name")
        val kilo = intent.getStringExtra("kilo")
        val price = intent.getStringExtra("price")
        val address = intent.getStringExtra("address")

        txtUpdateName?.setText(name)
        txtUpdateKilo?.setText(kilo)
        txtUpdatePrice?.setText(price)
        txtUpdateAddress?.setText(address)

        uid = id.toLong()
        Log.d("_recValue", "$id $name $kilo $price $address")
    }

    fun onClickUpdateTask(view: View?) {
        val name = txtUpdateName?.text.toString()
        val kilo = txtUpdateKilo?.text.toString()
        val price = txtUpdatePrice?.text.toString()
        val address = txtUpdateAddress?.text.toString()

        dbManager?.update(uid!!, name, kilo, price, address)
        val intent = Intent(this, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    fun onClickDeleteTask(view: View?) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("Do you wanted to Delete?")
        alertDialogBuilder.setPositiveButton(
            "yes"
        ) { arg0, arg1 ->
            dbManager?.delete(uid!!)
            val intent = Intent(
                applicationContext,
                MainActivity::class.java
            ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
        alertDialogBuilder.setNegativeButton("No") { dialog, which -> finish() }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}
