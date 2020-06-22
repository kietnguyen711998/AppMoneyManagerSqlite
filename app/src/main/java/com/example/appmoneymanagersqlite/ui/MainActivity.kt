package com.example.appmoneymanagersqlite.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmoneymanagersqlite.R
import com.example.appmoneymanagersqlite.adapter.RecyclerViewAdapter
import com.example.appmoneymanagersqlite.databse.DataBaseHelper
import com.example.appmoneymanagersqlite.databse.MyDBManager
import com.example.appmoneymanagersqlite.model.DailyConsumption
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_list.*
import java.util.*

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.ClickListener {

    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var mAdapter: RecyclerViewAdapter? = null
    private var dbManager: MyDBManager? = null
    private var dailyConsumption: ArrayList<DailyConsumption>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewTasks?.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(this)
        recyclerViewTasks?.setLayoutManager(mLayoutManager)
        dailyConsumption = ArrayList()
        val fabButton: FloatingActionButton = this.findViewById(R.id.fab)
        fabButton.setOnClickListener { // Create a new intent to start an AddTaskActivity
            val addTaskIntent = Intent(this, AddTaskActivity::class.java)
            startActivity(addTaskIntent)
        }
        Init()
    }

    private fun Init() {
        dbManager = MyDBManager(this)
        recyclerViewTasks?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewTasks?.adapter = mAdapter
        dbManager?.open()
        val cursor = dbManager?.fetch()
        while (cursor?.moveToNext()!!) {
            val id = cursor.getString(cursor.getColumnIndex(DataBaseHelper.ID))
            val name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.NAME))
            val kilo = cursor.getString(cursor.getColumnIndex(DataBaseHelper.KILO))
            val price = cursor.getString(cursor.getColumnIndex(DataBaseHelper.PRICE))
            val address = cursor.getString(cursor.getColumnIndex(DataBaseHelper.ADDRESS))
            val list = DailyConsumption(name, kilo, price, address, id)
            dailyConsumption?.add(list)
        }


        mAdapter = RecyclerViewAdapter(this, dailyConsumption)
        mAdapter?.setClickListener(this)
        recyclerViewTasks?.adapter = mAdapter
        mAdapter?.notifyDataSetChanged()
    }

    override fun itemClicked(view: View?, position: Int) {
        val intent = Intent(this, ModifyActivity::class.java)
        //        String id = String.valueOf(view.getId());
        val name = txtName.text.toString()
        val kilo = txtKilo.text.toString()
        val address = txtAddress.text.toString()
        val price = txtPrice.text.toString()
        val id = id_text.text.toString()
        //        Log.d("_passValue",id+" "+title+" "+desc);
        intent.putExtra("name", name)
        intent.putExtra("kilo", kilo)
        intent.putExtra("price", price)
        intent.putExtra("address", address)
        intent.putExtra("id", id)

        startActivity(intent)
    }
}
