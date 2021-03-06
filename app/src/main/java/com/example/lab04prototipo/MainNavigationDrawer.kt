package com.example.lab04prototipo

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.lab04prototipo.Data.DataBaseDummy
import com.example.lab04prototipo.Entities.Usuario
import com.example.lab04prototipo.helper.CrudTipoAviones
import com.example.lab04prototipo.helper.ListaVuelos
import com.example.lab04prototipo.helper.MisReservas

class MainNavigationDrawer : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {

    private var DB = DataBaseDummy
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_navigation_drawer)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //var profileName: String? = getIntent().getStringExtra("usuario");
       // holder.itemView.findViewById<TextView>(R.id.userText)?.text = profileName

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_navigation_drawer, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun message(text: String?){
        Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){

          /*  R.id.nav_tipoA -> {
                val i = Intent(this, CrudTipoAviones::class.java)
                startActivity(i)
            }*/
            R.id.nav_gallery -> {
                val i = Intent(this, ListaVuelos::class.java)

                var user: Usuario? = intent.extras?.get("usuario") as Usuario?
                intent.putExtra("usuario", user)

                startActivity(i)
            }
            R.id.nav_misReservas ->{
                val i = Intent(this, MisReservas::class.java)
                startActivity(i)
            }
            R.id.nav_slideshow ->{
                logout()
            }
        }
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
    fun logout(){
        val intent = Intent(applicationContext, MainActivity::class.java)
         //intent.putExtra("model", DB)
         startActivity(intent)
         finish()
    }

}