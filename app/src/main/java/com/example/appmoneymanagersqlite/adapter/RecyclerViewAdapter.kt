package com.example.appmoneymanagersqlite.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appmoneymanagersqlite.R
import com.example.appmoneymanagersqlite.model.DailyConsumption
import com.example.appmoneymanagersqlite.ui.MainActivity
import kotlinx.android.synthetic.main.item_list.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private var listClasses: List<DailyConsumption>? = null
    private var mContext: Context? = null
    private var clickListener: ClickListener? = null

    constructor(context: Context?, listClasses: List<DailyConsumption>?) {
        this.listClasses = listClasses
        mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val list = listClasses!![position]
        holder.name.text = list.mName
        holder.kilo.text = list.mKilo
        holder.price.text = list.mPrice
        holder.address.text = list.mAddressMarket
    }

    fun setClickListener(clickListener: MainActivity) {
        this.clickListener = clickListener
    }

    override fun getItemCount(): Int {
        return listClasses!!.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val name = itemView.txtName
        val kilo = itemView.txtKilo
        val price = itemView.txtPrice
        val address = itemView.txtAddress
        override fun onClick(v: View) {
//            Toast.makeText(mContext, "Clicked", Toast.LENGTH_SHORT).show();
            if (clickListener != null) {
                clickListener?.itemClicked(v, position)
            }
        }
    }

    interface ClickListener {
        fun itemClicked(view: View?, position: Int)
    }
}