package com.example.bankApplication_kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.api.model.ProductionModel
import kotlinx.android.synthetic.main.financial_item.view.*

class FinancialAdapter(var data : List<ProductionModel>) : RecyclerView.Adapter<FinancialAdapter.FinancialViewHolder>() {
    inner class FinancialViewHolder(parent : ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.financial_item,parent,false)){
        val title : TextView = itemView.financial_title
        val description : TextView = itemView.financial_description
        val interestRate : TextView = itemView.financial_rate
        val perMonth : TextView = itemView.financial_perMonth
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinancialViewHolder
        =FinancialViewHolder(parent)


    override fun onBindViewHolder(holder: FinancialViewHolder, position: Int) {
        data[position].let { item ->
            with(holder){
                title.text = item.productionName
                description.text = item.description
                interestRate.text = item.interestRate
                perMonth.text = item.interestCycle
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}