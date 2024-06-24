package com.darlami.analyticssdkexample.view_model

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.darlami.analyticssdkexample.view.ShopFragment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.Flow

class MainViewModel : ViewModel() {

    private var _activeFragment: MutableStateFlow<Fragment> = MutableStateFlow(ShopFragment())
    val activeFragment: StateFlow<Fragment> = _activeFragment

    fun setActiveFragment(fragment: Fragment) {
        _activeFragment.value = fragment
    }
}