package com.example.bankApplication_kotlin.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.databinding.HomeFragementPageBinding
import com.example.bankApplication_kotlin.event.EventObserver
import com.example.bankApplication_kotlin.viewModel.HomeViewModel

class HomeFragment : Fragment(){
    private val viewModel : HomeViewModel by lazy {
        ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    }
    private lateinit var binding : HomeFragementPageBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragement_page,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()
        viewModel.getBannerInfo()
        viewModel.getBannerEvent.observe(requireActivity(), EventObserver{
            Log.d("Banner Info",viewModel.eventBanner.toString())
        })
    }
    companion object{
        fun getInstance() = HomeFragment()

    }

}