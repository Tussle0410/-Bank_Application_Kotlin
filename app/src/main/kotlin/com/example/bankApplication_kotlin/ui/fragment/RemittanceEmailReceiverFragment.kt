package com.example.bankApplication_kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.databinding.RemittanceReceiverEmailFrameBinding
import com.example.bankApplication_kotlin.viewModel.RemittanceViewModel

class RemittanceEmailReceiverFragment : Fragment() {
    private val viewModel : RemittanceViewModel by lazy {
        ViewModelProvider(requireActivity()).get(RemittanceViewModel::class.java)
    }
    private lateinit var binding : RemittanceReceiverEmailFrameBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.remittance_receiver_email_frame,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}