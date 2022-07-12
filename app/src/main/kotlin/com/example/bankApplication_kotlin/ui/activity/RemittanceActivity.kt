package com.example.bankApplication_kotlin.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.databinding.RemittancePageBinding
import com.example.bankApplication_kotlin.event.EventObserver
import com.example.bankApplication_kotlin.ui.fragment.*
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
        binding.viewModel!!.addressEvent.observe(this,EventObserver{
            fragIndex = 1
            fragChange(1)
        })
        binding.viewModel!!.amountEvent.observe(this, EventObserver{
            fragIndex = 2
            fragChange(2)
        })
        binding.viewModel!!.pwEvent.observe(this,EventObserver{
            fragIndex = 4
            binding.remittanceNextButton.text = "완료"
            binding.remittanceTitle.text = "송금 완료"
            binding.remittanceBack.visibility = View.INVISIBLE
            fragChange(4)
        })
        binding.viewModel!!.nextEvent.observe(this,EventObserver{
            when (fragIndex) {
                0 -> viewModel.addressCheck()
                1 -> viewModel.amountCheck()
                2->{
                    binding.remittanceNextButton.setBackgroundColor(resources.getColor(R.color.main_color))
                    fragIndex = 3
                    fragChange(3)
                }
                3 -> viewModel.remittance()
                else -> finish()
            }
        })
    }
    //Fragment Init Method
    private fun initFrag(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.remittance_frame,RemittanceReceiverFragment())
        transaction.commit()
    }
    //Fragment Change Method
    private fun fragChange(check : Int){
        val transaction = supportFragmentManager.beginTransaction()
        when(check){
            1 -> transaction.replace(R.id.remittance_frame,RemittanceAmountFragment())
            2 -> transaction.replace(R.id.remittance_frame,RemittanceCheckFragment())
            3 -> transaction.replace(R.id.remittance_frame,RemittancePwCheckFragment())
            4 -> transaction.replace(R.id.remittance_frame,RemittanceCompleteFragment())
        }
        transaction.commit()
    }
}