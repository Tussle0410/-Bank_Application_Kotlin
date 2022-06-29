package com.example.bankApplication_kotlin.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.databinding.HomeMyassetFragementPageBinding
import com.example.bankApplication_kotlin.event.EventObserver
import com.example.bankApplication_kotlin.viewModel.HomeViewModel
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class HomeMyAssetFragment : Fragment(){
    private val viewModel : HomeViewModel by lazy {
        ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    }
    private lateinit var binding : HomeMyassetFragementPageBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_myasset_fragement_page,container,false)
        binding.viewModel = viewModel
        binding.plus = "#008080"
        binding.minus = "#FF0000"
        binding.lifecycleOwner = requireActivity()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMyAssetInfo()
        viewModel.getAssetEvent.observe(requireActivity(),EventObserver{
            pieChartSetting()
        })
    }
    private fun pieChartSetting(){
        binding.pieChart.clear()
        val color = listOf(ContextCompat.getColor(requireContext(),R.color.myAsset_pieChart_color1),
                ContextCompat.getColor(requireContext(),R.color.myAsset_pieChart_color2),
                ContextCompat.getColor(requireContext(),R.color.myAsset_pieChart_color3),
                ContextCompat.getColor(requireContext(),R.color.myAsset_pieChart_color4))
        val pieDataSet : PieDataSet = PieDataSet(pieChartSetData(),"").apply {
            this.colors = color
            this.sliceSpace = 3f
        }
        val pieData = PieData(pieDataSet).apply {
            this.setValueTextSize(20F)
            this.setValueTextColor(Color.BLACK)
        }
        val description = Description().apply {
            this.text = "자산현황"
            this.textSize = 15F
        }
        binding.pieChart.apply {
            this.setExtraOffsets(5F,10F,5F,5F)
            this.description = description
            this.dragDecelerationFrictionCoef = 0.95F
            this.transparentCircleRadius = 61F
            this.setEntryLabelTextSize(20F)
            this.isDrawHoleEnabled = false             //중앙 원 안보이게
            this.setHoleColor(Color.WHITE)             // 중앙 원 색 변경
            this.setDrawEntryLabels(true)          //라벨 보이도록
            this.setUsePercentValues(true)          //퍼센트 허용
            this.setEntryLabelColor(Color.BLACK)
            this.data = pieData
            this.invalidate()
        }

    }
    private fun pieChartSetData() : ArrayList<PieEntry>{
        val list = ArrayList<PieEntry>().apply {
            for (value in viewModel.myAsset.keys){
                when(value){
                    "deposit" -> this.add(PieEntry(viewModel.myAsset[value]!!.toFloat(),"입출금"))
                    "loan" -> this.add(PieEntry(viewModel.myAsset[value]!!.toFloat(),"대출"))
                    "savings" -> this.add(PieEntry(viewModel.myAsset[value]!!.toFloat(),"예적금"))
                    "fund" -> this.add(PieEntry(viewModel.myAsset[value]!!.toFloat(),"펀드"))
                }
            }
        }
        return list
    }
    companion object{
        fun getInstance() = HomeMyAssetFragment()
    }
}