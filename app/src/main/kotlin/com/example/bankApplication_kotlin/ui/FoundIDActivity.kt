package com.example.bankApplication_kotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.databinding.FoundIdPageBinding
import com.example.bankApplication_kotlin.viewModel.FoundIDViewModel

class FoundIDActivity : AppCompatActivity() {
    private val viewModel : FoundIDViewModel by lazy {
        ViewModelProvider(this).get(FoundIDViewModel::class.java)
    }
    private lateinit var binding : FoundIdPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.found_id_page)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}