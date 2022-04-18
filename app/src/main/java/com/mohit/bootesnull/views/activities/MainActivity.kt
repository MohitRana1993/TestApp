package com.mohit.bootesnull.views.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.mohit.bootesnull.R
import com.mohit.bootesnull.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.activity_main_navhostfragment) as NavHostFragment
        navController = navHostFragment.navController
        clicks()
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.eventDetailFragment) {
            navigationDirection(R.id.eventsFragment)
        } else {
            finish()
        }
    }

    private fun clicks() {
        binding.backDetail.setOnClickListener {
            onBackPressed()
        }
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.eventsFragment) {
                binding.tvTitle.text = resources.getString(R.string.events_list)
                binding.backDetail.visibility = View.GONE
                binding.eventMenu.visibility = View.GONE
                binding.eventShare.visibility = View.GONE
            } else {
                binding.tvTitle.text = resources.getString(R.string.event_detail)
                binding.backDetail.visibility = View.VISIBLE
                binding.eventMenu.visibility = View.VISIBLE
                binding.eventShare.visibility = View.VISIBLE
            }
        }
    }


    private fun navigationDirection(
        fragmentId: Int,
        args: Bundle? = null, removeBackStack: Boolean = false
    ) {
        navController.navigate(fragmentId, args)
    }

}