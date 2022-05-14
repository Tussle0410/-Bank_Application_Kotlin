package com.example.bankApplication_kotlin.ui

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.databinding.RegisterInfoPageBinding
import com.example.bankApplication_kotlin.event.EventObserver
import com.example.bankApplication_kotlin.viewModel.RegisterInfoViewModel
import org.jetbrains.anko.startActivity
import java.util.*

class RegisterInfoActivity : AppCompatActivity() {
    private val viewModel : RegisterInfoViewModel by lazy {
        ViewModelProvider(this).get(RegisterInfoViewModel::class.java)
    }
    private lateinit var binding : RegisterInfoPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.register_info_page)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.backEvent.observe(this,EventObserver{
            finish()
        })
        viewModel.birthEvent.observe(this,EventObserver{
            val c = Calendar.getInstance()
            val dialog = DatePickerDialog(this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
            DatePickerDialog.OnDateSetListener { _, y, m, d ->
                viewModel.year.value = y.toString()
                if(m+1<10)
                    viewModel.month.value = "0" + (m+1).toString()
                else
                    viewModel.month.value = (m+1).toString()

                viewModel.day.value = d.toString()
            },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH))
            dialog.window!!.setBackgroundDrawableResource(R.color.backgroundTransparency)
            dialog.show()
        })
        viewModel.nextEvent.observe(this,EventObserver{
            startActivity<RegisterPwActivity>()
        })
    }
}