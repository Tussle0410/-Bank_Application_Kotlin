package com.example.bankapplication_kotlin.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bankapplication_kotlin.R
import com.example.bankapplication_kotlin.databinding.LoginPageBinding
import com.example.bankapplication_kotlin.viewmodel.LoginViewModel

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



    }
}