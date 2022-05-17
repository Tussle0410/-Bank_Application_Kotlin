package com.example.bankApplication_kotlin.ui

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.databinding.FoundPwPageBinding
import com.example.bankApplication_kotlin.event.EventObserver
import com.example.bankApplication_kotlin.viewModel.FoundPWViewModel
import org.jetbrains.anko.startActivity

class FoundPWActivity : AppCompatActivity() {
    private val viewModel : FoundPWViewModel by lazy {
        ViewModelProvider(this).get(FoundPWViewModel::class.java)
    }
    private lateinit var binding: FoundPwPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.found_pw_page)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        //Back Button Click Observer
        viewModel.backEvent.observe(this,EventObserver{
            finish()
        })
        //Found ID Button Click Observer
        viewModel.foundIDEvent.observe(this, EventObserver{
            startActivity<FoundIDActivity>()
        })
        //Found Button Click Observer
        viewModel.foundPWEvent.observe(this,EventObserver{
            val dialog = AlertDialog.Builder(this)
                .setTitle("방가방가 테스트")
                .setMessage("\n" + viewModel.pw + "\n" + "확인을 눌러주세용")
                .setPositiveButton("확인",DialogInterface.OnClickListener { dialogInterface, i ->
                    finish()
                })
            .show()
        })
    }
}