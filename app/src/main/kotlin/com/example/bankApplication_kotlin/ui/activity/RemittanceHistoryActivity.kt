package com.example.bankApplication_kotlin.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.databinding.RemittanceHistoryPageBinding
import com.example.bankApplication_kotlin.event.EventObserver
import com.example.bankApplication_kotlin.ui.adapter.RemittanceHistoryAdapter
import com.example.bankApplication_kotlin.viewModel.RemittanceHistoryViewModel

class RemittanceHistoryActivity : AppCompatActivity() {
    private val viewModel : RemittanceHistoryViewModel by lazy {
        ViewModelProvider(this).get(RemittanceHistoryViewModel::class.java)
    }
    private lateinit var adapter : RemittanceHistoryAdapter
    private lateinit var binding : RemittanceHistoryPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.remittance_history_page)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.backEvent.observe(this,EventObserver{
            finish()
        })
        viewModel.setHistory()
        viewModel.history.observe(this, Observer {
            adapter = RemittanceHistoryAdapter(it)
            binding.remittanceHistoryRecycleView.layoutManager = LinearLayoutManager(this)
            binding.remittanceHistoryRecycleView.adapter = adapter
        })
    }
}