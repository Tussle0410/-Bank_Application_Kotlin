package com.example.bankApplication_kotlin.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.databinding.HomeFinancialFragmentPageBinding
import com.example.bankApplication_kotlin.ui.adapter.viewPagerAdapter
import com.example.bankApplication_kotlin.viewModel.HomeViewModel
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

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
        viewModel.getFinancial()
        viewModel.financialProduction.observe(requireActivity(), Observer {

        })
        bannerSetting(binding.financialFinancialBanner,viewModel.financialBanner,binding.financialFinancialIndicator)
    }
    private fun bannerSetting(pager : ViewPager2, banner : ArrayList<String>, indicator: DotsIndicator){
        pager.offscreenPageLimit
        pager.adapter = viewPagerAdapter(banner,requireContext())
        indicator.attachTo(pager)
    }
    companion object{
        fun getInstance() = HomeFinancialFragment()
    }
}