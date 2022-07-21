package com.example.bankApplication_kotlin.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.api.model.HistoryModel
import com.example.bankApplication_kotlin.sharedPreference.PreferenceApplication
import kotlinx.android.synthetic.main.remittance_history_item.view.*

class RemittanceHistoryAdapter(var data : List<HistoryModel>) : RecyclerView.Adapter<RemittanceHistoryAdapter.HistoryViewHolder>() {

    private val userName = PreferenceApplication.prefs.userGetString("Name","")
    private val minusColor = Color.RED
    inner class HistoryViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.remittance_history_item,parent,false)
    ){
        var date: TextView = itemView.remittance_history_date
        var name: TextView = itemView.remittance_history_name
        var amount: TextView = itemView.remittance_history_amount
    }
    override fun onBindViewHolder(holder: RemittanceHistoryAdapter.HistoryViewHolder, position: Int) {
        data[position].let { item ->
            with(holder){
                date.text = item.transactionDate
                if(item.sendName == userName){
                    name.text = item.receiveName
                    date.setTextColor(minusColor)
                    name.setTextColor(minusColor)
                    amount.setTextColor(minusColor)
                }else
                    name.text = item.sendName
                amount.text = item.money
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemittanceHistoryAdapter.HistoryViewHolder
        =HistoryViewHolder(parent)

    override fun getItemCount(): Int {
        return data.size
    }
}