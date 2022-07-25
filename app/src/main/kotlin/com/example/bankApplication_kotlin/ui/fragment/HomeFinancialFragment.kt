package com.example.bankApplication_kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.databinding.HomeFinancialFragmentPageBinding
import com.example.bankApplication_kotlin.event.EventObserver
import com.example.bankApplication_kotlin.ui.adapter.FinancialAdapter
import com.example.bankApplication_kotlin.ui.adapter.viewPagerAdapter
import com.example.bankApplication_kotlin.viewModel.HomeViewModel
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class HomeFinancialFragment : Fragment(){
    private val viewModel : HomeViewModel by lazy {
        ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    }
    private lateinit var adapter : FinancialAdapter
    private lateinit var binding : HomeFinancialFragmentPageBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.home_financial_fragment_page,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.financialRecycleView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getFinancial()
        viewModel.getFinancialEvent.observe(requireActivity(),EventObserver{
            setSavings()
        })
        viewModel.financialSavingsEvent.observe(requireActivity(),EventObserver{
            setSavings()
        })
        viewModel.financialLoanEvent.observe(requireActivity(),EventObserver{
            setLoan()
        })
        viewModel.financialFundEvent.observe(requireActivity(),EventObserver{
            setFund()
        })
        bannerSetting(binding.financialFinancialBanner,viewModel.financialBanner,binding.financialFinancialIndicator)
    }
    private fun bannerSetting(pager : ViewPager2, banner : ArrayList<String>, indicator: DotsIndicator){
        pager.offscreenPageLimit
        pager.adapter = viewPagerAdapter(banner,requireContext())
        indicator.attachTo(pager)
    }
    private fun setSavings(){
        adapter = FinancialAdapter(viewModel.savingsProduction.value!!)
        binding.financialRecycleView.adapter = adapter
        binding.financialSavingsButton.setTextColor(resources.getColor(R.color.main_color))
        binding.financialFundButton.setTextColor(resources.getColor(R.color.gray))
        binding.financialLoanButton.setTextColor(resources.getColor(R.color.gray))
    }
    private fun setLoan(){
        adapter = FinancialAdapter(viewModel.loanProduction.value!!)
        binding.financialRecycleView.adapter = adapter
        binding.financialSavingsButton.setTextColor(resources.getColor(R.color.gray))
        binding.financialFundButton.setTextColor(resources.getColor(R.color.gray))
        binding.financialLoanButton.setTextColor(resources.getColor(R.color.main_color))
    }
    private fun setFund(){
        adapter = FinancialAdapter(viewModel.fundProduction.value!!)
        binding.financialRecycleView.adapter = adapter
        binding.financialSavingsButton.setTextColor(resources.getColor(R.color.gray))
        binding.financialFundButton.setTextColor(resources.getColor(R.color.main_color))
        binding.financialLoanButton.setTextColor(resources.getColor(R.color.gray))
    }
    companion object{
        fun getInstance() = HomeFinancialFragment()
    }
}