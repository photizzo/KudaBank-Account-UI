package com.agromall.tellerium

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.agromall.domain.interactor.user.LoginUser
import com.agromall.domain.model.user.User
import com.agromall.presentation.state.UIState
import com.agromall.presentation.viewmodel.UsersViewModel
import com.agromall.tellerium.databinding.ActivityMainBinding
import com.agromall.tellerium.util.showSnackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val usersViewModel: UsersViewModel by viewModels()


    private val pages by lazy {
        listOf(
            Pair("Account Activity", ReportFragment.make()),
            Pair("Income & Expense",ReportFragment.make())
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        viewBinding.initView()
    }

    private fun  ActivityMainBinding.initView() {
        pagerMore.adapter = FragmentStateAdapter(this@MainActivity as FragmentActivity, pages.map { it.second })
        TabLayoutMediator(tabLayout, pagerMore) { tab, position ->
            tab.text = pages[position].first
        }.attach()
        pagerMore.isUserInputEnabled = false

    }
}

