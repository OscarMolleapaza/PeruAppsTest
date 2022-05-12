package com.omolleapaza.peruapps.peruappstest.ui.features.main


import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.omolleapaza.peruapps.peruappstest.R
import com.omolleapaza.peruapps.peruappstest.databinding.ActivityMainBinding
import com.omolleapaza.peruapps.peruappstest.ui.base.BaseActivity
import com.omolleapaza.peruapps.peruappstest.utils_class.ConnectionLiveData
import org.koin.android.ext.android.inject
import com.omolleapaza.peruapps.peruappstest.BR


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(MainViewModel::class)  {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var toolbar: Toolbar
    private lateinit var navController: NavController

    private val connectionLiveData: ConnectionLiveData by inject()

    override val getBindingVariable: Int
        get() = BR.viewModel

    override val getLayoutId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        findViewById<Toolbar>(R.id.topAppBar).visibility = View.GONE
    }

    override fun onActivityReady() {
        super.onActivityReady()
        toolbar = viewDataBinding.topAppBar
        setSupportActionBar(toolbar as Toolbar?)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.listPostFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.listPostFragment ) {
                toolbar.visibility = View.GONE
            } else {
                toolbar.visibility = View.VISIBLE
            }
        }


        connectionLiveData.observe(this, {
            myViewModel.setConnectionStatus(it)
        })

    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}