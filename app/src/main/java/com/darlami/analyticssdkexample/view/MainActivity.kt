package com.darlami.analyticssdkexample.view

import android.app.Application
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.darlami.analyticsdk.AndroidAnalyticsSDK
import com.darlami.analyticsdk.model.EventType
import com.darlami.analyticssdkexample.R
import com.darlami.analyticssdkexample.databinding.ActivityMainBinding
import com.darlami.analyticssdkexample.view_model.MainViewModel
import com.google.android.material.navigation.NavigationBarView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener(this)

        lifecycleScope.launch {
            viewModel.activeFragment.collectLatest {
                loadFragment(it)
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigation_home -> {
                viewModel.setActiveFragment(ShopFragment())
                true
            }

            R.id.navigation_dashboard -> {
                viewModel.setActiveFragment(AnalyticsFragment())
                true
            }

            else -> false
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.navHostFl.id, fragment)
            .commit()
    }

}