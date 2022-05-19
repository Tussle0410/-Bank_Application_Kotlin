package com.example.bankApplication_kotlin.viewModel

import android.app.Application
import android.view.MenuItem
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankApplication_kotlin.R
import com.example.bankApplication_kotlin.api.model.HomeNaviMenu
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val _currentFragment = MutableLiveData(HomeNaviMenu.HomeFragment)

    val currentFragment : LiveData<HomeNaviMenu>
        get() = _currentFragment

    val onItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val fragment = getFragment(item.itemId)
            changeCurrentFragment(fragment)
            true
        }
    private fun getFragment(menu_id: Int) : HomeNaviMenu
        = when(menu_id){
            R.id.nav_home -> HomeNaviMenu.HomeFragment
            R.id.nav_financial_products -> HomeNaviMenu.FinancialFragment
            R.id.nav_myBank -> HomeNaviMenu.MyBankFragment
            R.id.nav_myAsset -> HomeNaviMenu.MyAssetFragment
            else -> throw IllegalArgumentException("not found menu item id")
        }
    private fun changeCurrentFragment(fragment : HomeNaviMenu){
        if (_currentFragment.value == fragment)
            return
        else
            _currentFragment.value = fragment
    }
}