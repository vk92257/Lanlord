package com.app.landlord.modules.home.dashBoard.home.fragments.notices.selectTenants.adaptaer
import com.app.landlord.modules.home.dashBoard.home.fragments.messages.chat.model.Data
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout

import androidx.recyclerview.widget.RecyclerView
import com.app.landlord.R
import com.app.landlord.databinding.RvAddTenantsBinding
import com.app.landlord.databinding.RvChatMessagesBinding
import kotlinx.android.synthetic.main.rv_messages.view.*


class TenantsAdapter() : RecyclerView.Adapter<TenantsAdapter.ChatViewHolder>()  {

    lateinit var ctx: Context
    val ROW_NUMBER = 8
    lateinit var list: ArrayList<Data>


    constructor(ctx: Context, list: ArrayList<Data>) : this() {
        this.ctx = ctx
        this.list = list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {

        val binding = RvAddTenantsBinding.inflate(LayoutInflater.from(parent.context),parent,false)

//        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_chat_messages , parent, false)

        return ChatViewHolder(binding)
    }


    override fun onBindViewHolder(h: ChatViewHolder, position: Int) {
//                    h.itemView.root.setOnClickListener{
//
//                    }
                }

    override fun getItemCount(): Int {
        return 4
    }

    class ChatViewHolder(itemView: RvAddTenantsBinding) : RecyclerView.ViewHolder(itemView.root)
}