package com.example.bankApplication_kotlin.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.databinding.HomeFragementPageBinding
import com.example.bankApplication_kotlin.event.EventObserver
import com.example.bankApplication_kotlin.ui.adapter.viewPagerAdapter
import com.example.bankApplication_kotlin.viewModel.HomeViewModel
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class HomeFragment : Fragment(){
    private val viewModel : HomeViewModel by lazy {
        ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    }
    private lateinit var binding : HomeFragementPageBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragement_page,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBannerInfo()
        binding.viewModel!!.getBannerEvent.observe(requireActivity(),EventObserver{
            bannerSetting(binding.homeEventBanner,binding.viewModel!!.eventBanner,binding.homeEventIndicator)
            bannerSetting(binding.homeFinancialBanner,binding.viewModel!!.financialBanner,binding.homeFinancialIndicator)
        })
    }
    private fun bannerSetting(pager : ViewPager2, banner : ArrayList<String>, dotsIndicator: DotsIndicator ){
        pager.offscreenPageLimit
        pager.adapter = viewPagerAdapter(banner,requireContext())
        dotsIndicator.attachTo(pager)
    }
    companion object{
        fun getInstance() = HomeFragment()
    }

}