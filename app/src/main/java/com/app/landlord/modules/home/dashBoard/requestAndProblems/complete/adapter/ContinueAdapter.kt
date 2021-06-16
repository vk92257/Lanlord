package com.app.landlord.modules.home.dashBoard.requestAndProblems.complete.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.app.landlord.modules.home.dashBoard.requestAndProblems.complete.model.Data
import androidx.recyclerview.widget.RecyclerView
import com.app.landlord.R
import com.app.landlord.modules.request.Requests


class ContinueAdapter() : RecyclerView.Adapter<ContinueAdapter.ChatViewHolder>()  {

    lateinit var ctx: Context
    val ROW_NUMBER = 8
    lateinit var list: ArrayList<Data>


    constructor(ctx: Context, list: ArrayList<Data>) : this() {
        this.ctx = ctx
        this.list = list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_continue, parent, false)

        return ChatViewHolder(v)
    }


    override fun onBindViewHolder(h: ChatViewHolder, position: Int) {
        h.root.setOnClickListener{
            ctx.startActivity(Intent(ctx, Requests::class.java))
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val root : ConstraintLayout = itemView.findViewById(R.id.root)
    }
}