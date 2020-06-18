package com.example.hdtestapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mikepenz.actionitembadge.library.ActionItemBadge
import com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome

class MainActivity : AppCompatActivity() {

    private var currentCartSize = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        supportActionBar?.title = "HD native example"
        TokenManager.initializeToken(this)
    }

    override fun onResume() {
        super.onResume()
        this.updateCartSizeFromApi()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true;
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        if (this.currentCartSize > 0) {
            ActionItemBadge.update(
                this,
                menu.findItem(R.id.item_samplebadge),
                FontAwesome.Icon.faw_shopping_cart,
                ActionItemBadge.BadgeStyles.DARK_GREY,
                this.currentCartSize
            )
        } else {
            ActionItemBadge.hide(menu.findItem(R.id.item_samplebadge))
        }
        return true;
    }

    fun onButton1Click(v: View?) {
        val myIntent = Intent(baseContext, Product1Activity::class.java)
        startActivity(myIntent)
    }

    fun onButton2Click(v: View?) {
        val myIntent = Intent(baseContext, Product2Activity::class.java)
        startActivity(myIntent)
    }

    fun onButton3Click(v: View?) {
        val myIntent = Intent(baseContext, CheckoutActivity::class.java)
        startActivity(myIntent)
    }

    private fun updateCartSizeFromApi(): Unit {
        val token = TokenManager.getCurrentToken();

        if (token != null) {
            ApiClient.getCartSize(token) {
                runOnUiThread{
                    this.currentCartSize = it
                    invalidateOptionsMenu()
                }
            }
        }
    }

}