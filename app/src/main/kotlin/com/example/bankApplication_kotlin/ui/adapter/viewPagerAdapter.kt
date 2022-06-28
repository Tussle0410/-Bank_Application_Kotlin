package com.example.bankApplication_kotlin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.databinding.BannerAdapterItemBinding

class viewPagerAdapter(private val bannerList : ArrayList<String>, private val context : Context) :
    RecyclerView.Adapter<viewPagerAdapter.PageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewPagerAdapter.PageViewHolder {
        val binding :BannerAdapterItemBinding = BannerAdapterItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: viewPagerAdapter.PageViewHolder, position: Int) {
        holder.bindSliderImage(bannerList[position])
    }

    override fun getItemCount(): Int {
        return bannerList.size
    }

    inner class PageViewHolder(private val binding: BannerAdapterItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindSliderImage(url : String){
            Glide.with(context).load(url).into(binding.bannerItem)
        }
    }
}