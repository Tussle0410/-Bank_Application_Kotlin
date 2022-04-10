package com.example.bankApplication_kotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.databinding.RegisterInfoPageBinding
import com.example.bankApplication_kotlin.event.EventObserver
import com.example.bankApplication_kotlin.viewModel.RegisterInfoViewModel

class RegisterInfoActivity : AppCompatActivity() {
    private val viewModel : RegisterInfoViewModel by lazy {
        ViewModelProvider(this).get(RegisterInfoViewModel::class.java)
    }
    private lateinit var binding:RegisterInfoPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.register_info_page)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.backEvent.observe(this,EventObserver{
            finish()
        })
    }
}