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
import com.example.bankApplication_kotlin.databinding.HomeFinancialFragmentPageBinding
import com.example.bankApplication_kotlin.viewModel.HomeViewModel

class HomeFinancialFragment : Fragment(){
    private val viewModel : HomeViewModel by lazy {
        ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    }
    private lateinit var binding : HomeFinancialFragmentPageBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.home_financial_fragment_page,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Event", viewModel.eventBanner.toString())
        Log.d("Financial", viewModel.financialBanner.toString())
        Log.d("Name",viewModel.userName.value!!)
        Log.d("Money",viewModel.homeCurMoney.value!!)
    }
    companion object{
        fun getInstance(financialBanner : ArrayList<String>) = HomeFinancialFragment().apply {
            arguments = Bundle().apply {
                putStringArrayList("Banner",financialBanner)
            }
        }
    }
}