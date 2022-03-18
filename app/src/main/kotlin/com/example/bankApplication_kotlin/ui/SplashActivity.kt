package com.example.bankApplication_kotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.viewModel.SplashViewModel
import org.jetbrains.anko.startActivity


class SplashActivity : AppCompatActivity() {
    private val viewModel : SplashViewModel by lazy {
        ViewModelProvider(this).get(SplashViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_page)
        viewModel.isSplashing.observe(this, Observer {
            if (it){
                startActivity<LoginActivity>()
                finish()
            }
        })
    }
}