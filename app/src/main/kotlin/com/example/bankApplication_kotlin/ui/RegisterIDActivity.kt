package com.example.bankApplication_kotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.databinding.RegisterIdPageBinding
import com.example.bankApplication_kotlin.event.EventObserver
import com.example.bankApplication_kotlin.viewModel.RegisterIDViewModel
import org.jetbrains.anko.startActivity

class RegisterIDActivity : AppCompatActivity() {
    private val viewModel: RegisterIDViewModel by lazy{
        ViewModelProvider(this).get(RegisterIDViewModel::class.java)
    }
    private lateinit var binding:RegisterIdPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.register_id_page)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        //Back Button Click Observer
        viewModel.backEvent.observe(this, EventObserver{
            finish()
        })
        //Next Button Click Observer
        viewModel.nextEvent.observe(this,EventObserver{
            startActivity<RegisterEmailActivity>()
        })
    }
}