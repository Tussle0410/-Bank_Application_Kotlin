package com.example.bankApplication_kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.databinding.RemittanceReceiverFragmentBinding
import com.example.bankApplication_kotlin.event.EventObserver
import com.example.bankApplication_kotlin.viewModel.RemittanceViewModel

class RemittanceReceiverFragment : Fragment() {
    private val viewModel : RemittanceViewModel by lazy {
        ViewModelProvider(requireActivity()).get(RemittanceViewModel::class.java)
    }
    private lateinit var binding : RemittanceReceiverFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.remittance_receiver_fragment,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frameInit(true)
        binding.viewModel!!.addressReceiver.observe(requireActivity(),EventObserver{
            if(it){
                frameInit(true)
                binding.remittanceAddressButton.setTextColor(resources.getColor(R.color.main_color))
                binding.remittanceEmailButton.setTextColor(resources.getColor(R.color.gray))
            }
        })
        binding.viewModel!!.emailReceiver.observe(requireActivity(),EventObserver{
            if(it){
                frameInit(false)
                binding.remittanceEmailButton.setTextColor(resources.getColor(R.color.main_color))
                binding.remittanceAddressButton.setTextColor(resources.getColor(R.color.gray))
            }
        })
    }
    private fun frameInit(check : Boolean){
        val transaction = childFragmentManager.beginTransaction()
        if (check)
            transaction.replace(R.id.remittance_receiver_frame,RemittanceAddressReceiverFragment())
        else
            transaction.replace(R.id.remittance_receiver_frame,RemittanceEmailReceiverFragment())

        transaction.commit()
    }
}