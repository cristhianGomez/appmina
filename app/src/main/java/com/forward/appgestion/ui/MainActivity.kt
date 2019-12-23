package com.forward.appgestion.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.forward.appgestion.R
import com.forward.appgestion.data.repository.LoginRepository
import com.forward.appgestion.domain.LoginDataSource
import com.forward.appgestion.domain.Result
import com.forward.appgestion.ui.specificRegisterList.SpecificRegisterFragment
import com.forward.appgestion.ui.generalRegisters.GeneralRegisterFragment
import com.forward.appgestion.ui.informacion.InformacionFragment
import com.forward.appgestion.ui.login.LoginActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {


    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var specificRegisterFragment: SpecificRegisterFragment
    private lateinit var generalRegisterFragment: GeneralRegisterFragment
    private lateinit var informacionFragment: InformacionFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.title = "AppMina"
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.generalRegisterFragment,
                    R.id.informacionFragment,
                R.id.specificRegisterFragment
                ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(this)
//        navView.bringToFront()


    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val bundle = Bundle()
        when (item.itemId) {
            R.id.alimentadores2300 -> {
                bundle.putInt("id", 1)
                findNavController(R.id.nav_host_fragment).navigate(R.id.specificRegisterFragment,bundle)
            }
            R.id.fajas2300 -> {
                bundle.putInt("id", 2)
                findNavController(R.id.nav_host_fragment).navigate(R.id.specificRegisterFragment,bundle)
            }
            R.id.alimentadores2400 -> {
                bundle.putInt("id", 3)
                findNavController(R.id.nav_host_fragment).navigate(R.id.specificRegisterFragment,bundle)
            }
            R.id.fajas2400 -> {
                bundle.putInt("id", 4)
                findNavController(R.id.nav_host_fragment).navigate(R.id.specificRegisterFragment,bundle)
            }
            R.id.chancadores2400 -> {
                bundle.putInt("id", 5)
                findNavController(R.id.nav_host_fragment).navigate(R.id.specificRegisterFragment,bundle)
            }
            R.id.colectores2400 -> {
                bundle.putInt("id", 6)
                findNavController(R.id.nav_host_fragment).navigate(R.id.specificRegisterFragment,bundle)
            }
            R.id.zarandas2400 -> {
                bundle.putInt("id", 7)
                findNavController(R.id.nav_host_fragment).navigate(R.id.specificRegisterFragment,bundle)
            }
            R.id.alimentadores2500 -> {
                bundle.putInt("id", 8)
                findNavController(R.id.nav_host_fragment).navigate(R.id.specificRegisterFragment,bundle)
            }
            R.id.fajas2500 -> {
                bundle.putInt("id", 9)
                findNavController(R.id.nav_host_fragment).navigate(R.id.specificRegisterFragment,bundle)
            }
            R.id.hpgr2500-> {
                bundle.putInt("id", 10)
                findNavController(R.id.nav_host_fragment).navigate(R.id.specificRegisterFragment,bundle)
            }
            R.id.colectores2500-> {
                bundle.putInt("id", 11)
                findNavController(R.id.nav_host_fragment).navigate(R.id.specificRegisterFragment,bundle)
            }

        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        menu.findItem(R.id.action_search).isVisible = false
        return super.onCreateOptionsMenu(menu)
    }

        override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        //var fragment: Fragment = when (menuItem.itemId) {
          // else -> FirstFragment()
        when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this,"Update", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_logout->{
                MaterialAlertDialogBuilder(this)
                    .setMessage(getString(R.string.alert_dialog_message_logout))
                    .setNegativeButton(getString(R.string.alert_dialog_cancel),null)
                    .setPositiveButton(getString(R.string.alert_dialog_logout)) { _, _ ->
                        val result: Result<String> = LoginRepository(LoginDataSource())
                            .logout(application)
                        if (result is Result.Success){
                            startLoginActivity()
                        }else{
                            Toast.makeText(this,
                                getString(R.string.message_error_resource),
                                Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    .show()

                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)){
                drawer_layout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }
    private fun startLoginActivity(){
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
        Toast.makeText(this,
            getString(R.string.message_logout_resource),
            Toast.LENGTH_SHORT)
            .show()
        finish()
    }

}
