package com.example.bankapplication_kotlin.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.bankapplication_kotlin.R
import com.example.bankapplication_kotlin.login.LoginActivity
import org.jetbrains.anko.startActivity


class SplashActivity : AppCompatActivity() {
    companion object{
        private const val splash_Time_OUT : Long = 3000
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_page)
        Handler().postDelayed({
            startActivity<LoginActivity>()
            finish()
        }, splash_Time_OUT)
    }
}