package com.app.landlord.modules.home.dashBoard.maintenance.adapter
import com.app.landlord.modules.home.dashBoard.maintenance.model.Data
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.app.landlord.R


class MaintenanceAdapter() : RecyclerView.Adapter<MaintenanceAdapter.ChatViewHolder>()  {

    lateinit var ctx: Context
    val ROW_NUMBER = 8
    lateinit var list: ArrayList<Data>


    constructor(ctx: Context, list: ArrayList<Data>) : this() {
        this.ctx = ctx
        this.list = list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_maintenance , parent, false)

        return ChatViewHolder(v)
    }


    override fun onBindViewHolder(h: ChatViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 2
    }

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}