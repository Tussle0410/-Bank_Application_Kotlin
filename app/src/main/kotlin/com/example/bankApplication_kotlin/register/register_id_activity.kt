package com.example.bankApplication_kotlin.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.databinding.RegisterIdPageBinding
import com.example.bankApplication_kotlin.viewModel.Register_ID_ViewModel

class register_id_activity : AppCompatActivity() {
    private val viewModel:Register_ID_ViewModel by lazy{
        ViewModelProvider(this).get(Register_ID_ViewModel::class.java)
    }
    private lateinit var binding:RegisterIdPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.register_id_page)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}