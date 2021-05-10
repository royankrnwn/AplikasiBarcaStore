package com.example.navigasiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBar: ActionBarDrawerToggle
    private lateinit var navDrawerView: NavigationView

    //Initialise the NavigationBottomBar
    private lateinit var bottomNavigation : BottomNavigationView

    var myAdapter : ProductAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayItem = ArrayList<ProductModel>()
        arrayItem.add(ProductModel("Jersey Home", "Exclusive Match", R.drawable.home ,800000))
        arrayItem.add(ProductModel("Jersey Away", "Exclusive Match", R.drawable.away ,800000))
        arrayItem.add(ProductModel("Jersey 3rd", "Exclusive Match", R.drawable.away2 ,750000))
        arrayItem.add(ProductModel("Jersey El-Clasico Edition", "Exclusive Match", R.drawable.clasico ,1000000))
        arrayItem.add(ProductModel("Jersey Training 1", "Supporters Edition", R.drawable.training ,500000))
        arrayItem.add(ProductModel("Jersey Training 2", "Supporters Edition", R.drawable.training2 ,500000))
        arrayItem.add(ProductModel("Jersey Training 3", "Supporters Edition", R.drawable.training3 ,500000))
        arrayItem.add(ProductModel("Jaket Training", "Supporters Edition", R.drawable.jaket ,900000))
        arrayItem.add(ProductModel("Jersey Kids Home", "Supporters Edition", R.drawable.kidshome ,600000))
        arrayItem.add(ProductModel("Jersey Kids Away", "Supporters Edition", R.drawable.kidsaway ,600000))

        myAdapter = ProductAdapter(this)
        myAdapter!!.setData(arrayItem)

        //product_recycleview berasal dari id recycleview pada activity_main.xml
        product_recycleview.layoutManager = LinearLayoutManager(this)
        product_recycleview.adapter = myAdapter
        //END PRODUCT ITEM

        //START BOTTOM NAVIGATION
        bottomNavigation = findViewById(R.id.navBottom)
        bottomNavigation.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.navigation_home -> {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.history -> {
                    Toast.makeText(this, "Go To history", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    false
                }
            }
        }
        drawerLayout = findViewById(R.id.drawer)
        actionBar = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(actionBar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar.syncState()

        navDrawerView = findViewById(R.id.navDrawer)
        navDrawerView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.myProfile -> {
                    val intent = Intent(applicationContext, Profile::class.java)
                    startActivity(intent)
                    true
                }
                R.id.myContact -> {
                    Toast.makeText(this, "Go to My Contact", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.myHelp -> {
                    Toast.makeText(this, "Go to Our Help", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            this.drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        val searchItem = menu?.findItem(R.id.search)
        if(searchItem != null){
            val searchView = searchItem.actionView as SearchView
            searchView.maxWidth = Int.MAX_VALUE
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(filterString: String?): Boolean {
                    myAdapter!!.filter.filter(filterString)
                    return true
                }

            })
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.shopping) {
            Toast.makeText(this, "View Shopping Chart", Toast.LENGTH_SHORT).show()
            return true
        } else if (id == R.id.account) {
            Toast.makeText(this, "Account Clicked", Toast.LENGTH_SHORT).show()
            return true
        } else if (id == R.id.logout) {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            Toast.makeText(this, "Logout and go to login form", Toast.LENGTH_SHORT).show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}