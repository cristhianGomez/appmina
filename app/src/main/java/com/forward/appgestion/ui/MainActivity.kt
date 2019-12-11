package com.forward.appgestion.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.forward.appgestion.R
import com.forward.appgestion.data.repository.LoginRepository
import com.forward.appgestion.domain.LoginDataSource
import com.forward.appgestion.domain.Result
import com.forward.appgestion.ui.login.LoginActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.generalRegisterFragment,
                R.id.alimen23ListFragment,
                R.id.fajas23ListFragment,
                R.id.alimen24ListFragment ,
                R.id.fajas24ListFragment ,
                R.id.chancad24ListFragment ,
                R.id.colect24ListFragment ,
                R.id.zarandas24ListFragment ,
                R.id.alimen25ListFragment ,
                R.id.fajas25ListFragment ,
                R.id.mpgr25ListFragment ,
                R.id.colect25ListFragment
                ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
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
