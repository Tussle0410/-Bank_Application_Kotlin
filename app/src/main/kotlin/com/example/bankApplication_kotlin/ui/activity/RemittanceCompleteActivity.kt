package com.example.bankApplication_kotlin.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.databinding.RemittanceCompletePageBinding

class RemittanceCompleteActivity : AppCompatActivity() {
    private lateinit var binding : RemittanceCompletePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.remittance_complete_page)
        binding.lifecycleOwner = this
    }
}