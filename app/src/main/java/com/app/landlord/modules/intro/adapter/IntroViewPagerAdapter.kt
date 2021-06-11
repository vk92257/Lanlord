package com.app.landlord.modules.intro.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.app.landlord.R
import com.lonoshapp.models.OnBordingModel


class IntroViewPagerAdapter(val mList: ArrayList<OnBordingModel>) : PagerAdapter() {
    override fun getCount(): Int {
        return mList.size
    }

    override fun isViewFromObject(
        view: View,
        `object`: Any
    ): Boolean {
        return view === `object`
    }

    override fun destroyItem(
        view: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        view.removeView(`object` as View)
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
      val  inflater = view.getContext()
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val viewItem = inflater.inflate(R.layout.intro_item_layout, view, false)
        viewItem.findViewById<TextView>(R.id.txtOnBordingTitle).text = mList[position].title
        viewItem.findViewById<TextView>(R.id.txtOnBordingSubTitle).text = mList[position].subTitle
        viewItem.findViewById<ImageView>(R.id.ivBording).setImageResource(mList[position].image)
        view.addView(viewItem)
        return viewItem
    }
}