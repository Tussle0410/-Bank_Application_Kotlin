package com.example.bankApplication_kotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.databinding.RegisterPwPageBinding
import com.example.bankApplication_kotlin.event.EventObserver
import com.example.bankApplication_kotlin.viewModel.RegisterPwViewModel
import org.jetbrains.anko.startActivity

class RegisterPwActivity : AppCompatActivity() {
    private val viewModel : RegisterPwViewModel by lazy{
        ViewModelProvider(this).get(RegisterPwViewModel::class.java)
    }
    private lateinit var binding : RegisterPwPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.register_pw_page)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.backEvent.observe(this,EventObserver{
            finish()
        })
        viewModel.registerEvent.observe(this,EventObserver{
            finishAffinity()
            startActivity<LoginActivity>()
        })
    }
}