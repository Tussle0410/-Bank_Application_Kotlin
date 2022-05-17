package com.example.bankApplication_kotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.databinding.LoginPageBinding
import com.example.bankApplication_kotlin.event.EventObserver
import com.example.bankApplication_kotlin.viewModel.LoginViewModel
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {
    private val viewModel : LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }
    private lateinit var binding : LoginPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.login_page)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        //Register Button Click Observer
        viewModel.register.observe(this,EventObserver{
            startActivity<RegisterIDActivity>()
        })
        //Found Button Click Observer
        viewModel.found.observe(this, EventObserver{
            startActivity<FoundPWActivity>()
        })
    }
}