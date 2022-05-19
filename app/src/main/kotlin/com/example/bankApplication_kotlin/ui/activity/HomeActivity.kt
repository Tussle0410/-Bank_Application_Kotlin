package com.example.bankApplication_kotlin.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.api.model.HomeNaviMenu
import com.example.bankApplication_kotlin.databinding.HomePageBinding
import com.example.bankApplication_kotlin.ui.fragment.HomeFinancialFragment
import com.example.bankApplication_kotlin.ui.fragment.HomeFragment
import com.example.bankApplication_kotlin.ui.fragment.HomeMyAssetFragment
import com.example.bankApplication_kotlin.ui.fragment.HomeMyBankFragment
import com.example.bankApplication_kotlin.viewModel.HomeViewModel
import org.jetbrains.anko.toast
import java.lang.IllegalArgumentException

class HomeActivity : AppCompatActivity() {
    private val viewModel : HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    private lateinit var binding : HomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.home_page)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.currentFragment.observe(this){
            changeFragment(it!!)
        }
    }
    private fun changeFragment(menu_tag : HomeNaviMenu){
        val transaction = supportFragmentManager.beginTransaction()
        var targetFragment = supportFragmentManager.findFragmentByTag(menu_tag.tag)
        if(targetFragment == null){
            targetFragment = when(menu_tag){
                HomeNaviMenu.HomeFragment -> HomeFragment.getInstance()
                HomeNaviMenu.FinancialFragment ->HomeFinancialFragment.getInstance()
                HomeNaviMenu.MyBankFragment -> HomeMyBankFragment.getInstance()
                HomeNaviMenu.MyAssetFragment -> HomeMyAssetFragment.getInstance()
                else -> throw IllegalArgumentException("not found menu_tag")
            }
            transaction.add(R.id.home_container,targetFragment,menu_tag.tag)
        }
        transaction.show(targetFragment)
        HomeNaviMenu.values()
            .filterNot { it == menu_tag }
            .forEach { menu ->
                supportFragmentManager.findFragmentByTag(menu.tag)?.let {
                    transaction.hide(it)
                }

            }
        transaction.commitAllowingStateLoss()
    }
}