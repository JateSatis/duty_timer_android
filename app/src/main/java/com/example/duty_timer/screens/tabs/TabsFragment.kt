package com.example.duty_timer.screens.tabs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.duty_timer.R
import com.example.duty_timer.databinding.FragmentTabsBinding

class TabsFragment : Fragment() {

    private lateinit var binding: FragmentTabsBinding
    private lateinit var navController: NavController

    private var currentTab = R.id.timer_menu_tab
    private val tabsCurrentFragmentMap = mutableMapOf(
        R.id.timer_menu_tab to R.id.timerFragment,
        R.id.events_menu_tab to R.id.eventsFragment,
        R.id.chatrooms_menu_tab to R.id.chatroomsFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTabsBinding.inflate(inflater, container, false)

        val navHost = childFragmentManager.findFragmentById(R.id.tabsGraphContainer) as NavHostFragment
        navController = navHost.navController

        requireActivity().onBackPressedDispatcher.addCallback {
            navController.navigateUp()
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            navigateToTab(it.itemId)
            true
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val newTab = getTabFromDestination(destination.id)
            tabsCurrentFragmentMap[newTab] = destination.id
            updateBottomNavigationView(newTab)
        }

        return binding.root
    }

    private fun navigateToTab(tabId: Int) {
        navController.navigate(tabsCurrentFragmentMap[tabId] ?: R.id.timerFragment)
    }

    private fun updateBottomNavigationView(newTab: Int) {
        if (newTab != currentTab) {
            binding.bottomNavigationView.menu.findItem(currentTab).isChecked = false
            binding.bottomNavigationView.menu.findItem(newTab).isChecked = true
            currentTab = newTab
        }
    }

    private fun getTabFromDestination(destinationId: Int): Int {
        return when(destinationId) {
            in arrayOf(R.id.timerFragment, R.id.addFriendFragment) -> R.id.timer_menu_tab
            in arrayOf(R.id.eventsFragment, R.id.addEventFragment) -> R.id.events_menu_tab
            in arrayOf(R.id.chatroomsFragment) -> R.id.chatrooms_menu_tab
            else -> R.id.timer_menu_tab
        }
    }
}