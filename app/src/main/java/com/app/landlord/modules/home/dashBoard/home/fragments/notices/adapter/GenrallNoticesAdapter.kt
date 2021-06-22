package com.app.landlord.modules.home.dashBoard.home.fragments.notices.adapter
import com.app.landlord.modules.home.dashBoard.home.fragments.notices.model.Data
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.app.landlord.databinding.RvNoticesBinding
import com.app.landlord.modules.home.dashBoard.home.fragments.notices.noticeDetail.NoticeDetailActivity
import com.app.landlord.modules.home.dashBoard.home.fragments.notices.selectTenants.listener.OnclickListener
import com.app.landlord.utils.Constants


class GenrallNoticesAdapter() : RecyclerView.Adapter<GenrallNoticesAdapter.ChatViewHolder>()  {

    lateinit var ctx: Context
    val ROW_NUMBER = 8
    lateinit var list: ArrayList<Data>
    lateinit var  onclickListener: OnclickListener

    constructor(ctx: Context, list: ArrayList<Data>) : this() {
        this.ctx = ctx
        this.list = list
    }

    fun addOnClickListener(listener: OnclickListener){
        onclickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {

        val binding = RvNoticesBinding.inflate(LayoutInflater.from(parent.context),parent,false)

//        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_chat_messages , parent, false)

        return ChatViewHolder(binding)
    }


    override fun onBindViewHolder(h: ChatViewHolder, position: Int) {
                    h.itemView.setOnClickListener {
                        ctx.startActivity(Intent(ctx,NoticeDetailActivity::class.java).putExtra(
                            Constants.TYPE,
                            Constants.GENERAL_NOTICE))
                    }
                }

    override fun getItemCount(): Int {
        return 1
    }

    class ChatViewHolder(itemView: RvNoticesBinding) : RecyclerView.ViewHolder(itemView.root)
}