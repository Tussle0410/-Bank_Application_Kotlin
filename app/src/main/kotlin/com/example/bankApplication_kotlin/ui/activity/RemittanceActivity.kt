package com.example.bankApplication_kotlin.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.databinding.RemittancePageBinding
import com.example.bankApplication_kotlin.ui.fragment.RemittanceReceiverFragment
import com.example.bankApplication_kotlin.viewModel.RemittanceViewModel


class RemittanceActivity : AppCompatActivity() {
    private val viewModel : RemittanceViewModel by lazy {
        ViewModelProvider(this).get(RemittanceViewModel::class.java)
    }
    private lateinit var binding : RemittancePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.remittance_page)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initFrag()
    }
    //Fragment Init Method
    fun initFrag(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.remittance_frame,RemittanceReceiverFragment())
        transaction.commit()
    }
}