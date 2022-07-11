package com.example.bankApplication_kotlin.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.databinding.RemittancePageBinding
import com.example.bankApplication_kotlin.event.EventObserver
import com.example.bankApplication_kotlin.ui.fragment.RemittanceAmountFragment
import com.example.bankApplication_kotlin.ui.fragment.RemittanceCheckFragment
import com.example.bankApplication_kotlin.ui.fragment.RemittancePwCheckFragment
import com.example.bankApplication_kotlin.ui.fragment.RemittanceReceiverFragment
import com.example.bankApplication_kotlin.viewModel.RemittanceViewModel
import org.jetbrains.anko.startActivity


class RemittanceActivity : AppCompatActivity() {
    private var fragIndex = 0
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
        binding.viewModel!!.backEvent.observe(this,EventObserver{
            finish()
        })
        binding.viewModel!!.nextEvent.observe(this,EventObserver{
            val transaction = supportFragmentManager.beginTransaction()
            when (fragIndex) {
                0 -> {
                    if(viewModel.addressCheck()){
                        transaction.replace(R.id.remittance_frame,RemittanceAmountFragment())
                        fragIndex = 1
                    }
                }
                1 -> {
                    if(viewModel.amountCheck()){
                        transaction.replace(R.id.remittance_frame, RemittanceCheckFragment())
                        fragIndex = 2
                    }
                }
                2->{
                    transaction.replace(R.id.remittance_frame,RemittancePwCheckFragment())
                    binding.remittanceNextButton.setBackgroundColor(resources.getColor(R.color.main_color))
                    fragIndex = 3
                }
                else -> {
                    startActivity<RemittanceCompleteActivity>()
                    finish()
                }
            }
            transaction.commit()
        })
    }
    //Fragment Init Method
    private fun initFrag(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.remittance_frame,RemittanceReceiverFragment())
        transaction.commit()
    }
}