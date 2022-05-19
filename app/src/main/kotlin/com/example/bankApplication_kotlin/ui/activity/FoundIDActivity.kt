package com.example.bankApplication_kotlin.ui.activity

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.databinding.FoundIdPageBinding
import com.example.bankApplication_kotlin.event.EventObserver
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
        //back Button Click Observer
        viewModel.backEvent.observe(this,EventObserver{
            finish()
        })
        //found Button Click Observer
        viewModel.foundEvent.observe(this,EventObserver{
            val dialog = AlertDialog.Builder(this)
                .setTitle("해당 정보의 아이디 목록입니다.")
                .setItems(viewModel.id.toArray(arrayOfNulls<String>(viewModel.id.size)),null)
                .setPositiveButton("확인",DialogInterface.OnClickListener { _, _ ->
                    finish()
                }).show()
        })
    }
}