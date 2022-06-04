package com.example.bankApplication_kotlin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bankApplication_kotlin.R

class viewPagerAdapter(private val bannerList : ArrayList<String>, private val context : Context) :
    RecyclerView.Adapter<viewPagerAdapter.PageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewPagerAdapter.PageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.banner_adapter_item,parent,false)
        return PageViewHolder(view)
    }

    override fun onBindViewHolder(holder: viewPagerAdapter.PageViewHolder, position: Int) {
        holder.bindSliderImage(bannerList[position])
    }

    override fun getItemCount(): Int {
        return bannerList.size
    }

    inner class PageViewHolder(parent: View) : RecyclerView.ViewHolder(parent){
        private val img : ImageView = itemView.findViewById(R.id.banner_item)
        fun bindSliderImage(url : String){
            Glide.with(context).load(url).into(img)
        }
    }
}