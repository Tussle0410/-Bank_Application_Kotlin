package com.example.bankApplication_kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.api.model.HistoryModel
import kotlinx.android.synthetic.main.remittance_history_item.view.*

class RemittanceHistoryAdapter(var data : LiveData<List<HistoryModel>>) : RecyclerView.Adapter<RemittanceHistoryAdapter.HistoryViewHolder>() {

    inner class HistoryViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.remittance_history_item,parent,false)
    )
    override fun onBindViewHolder(holder: RemittanceHistoryAdapter.HistoryViewHolder, position: Int) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemittanceHistoryAdapter.HistoryViewHolder
        = HistoryViewHolder(parent)

    override fun getItemCount(): Int {
        return data.value!!.size
    }
}