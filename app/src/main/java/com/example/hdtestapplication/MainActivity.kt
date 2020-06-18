package com.example.hdtestapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mikepenz.actionitembadge.library.ActionItemBadge
import com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome

class MainActivity : AppCompatActivity() {

    private val apiClient = ApiClient(this)
    private var currentCartSize = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        supportActionBar?.title = "HD native example"
        Cookie.setValue("tipserToken", "eyJraWQiOm51bGwsImFsZyI6IlJTNTEyIn0.eyJpc3MiOiJUaXBzZXIiLCJqdGkiOiI2MHJhUk50QVUyc0Z6WV9yTGtfVnVRIiwiaWF0IjoxNTkyMzc2MzE5LCJleHAiOjE2MjM5MTIzMTksImNhcnRJZCI6IjVlZTliYmZmMGFmZTFiMDAwMTJhMTllMyJ9.LmFgGn06v_2I14br1PeWQaHy2HpF-VrtD30xGfkXgH0b8f1BsD43pcI9B_3Us-BUOTUMt0T1FRO-yCh-a1zQ-tBLRsLsHHB4jLuIHg8bROKDEtHDMnTZXhd8Wqe5Fmyh2K3PIHiCEhmJ1pN1BZ_5yfuOjuQFhQUMPcdtPCRLer-1SVC9n1yOsKnzZcjGnbl0SKBgZvJSiYAIis-g_dndEFmde2ZdNSuUjQctKarEGaNNTzIaHP2STEhdKopgU7WMtel4JxQIBsYz_CW7zPXPUBz0hgwpdMq_9GEqcGstnK-ZRLJBQ9j0kpdqe7HnufU86Qvc08oSOiRQSqq19GOdyw")
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

    fun getTokenFromCookie(): String? {
        return Cookie.getValue("tipserToken")
    }

    fun updateCartSizeFromApi(): Unit {
        val token = getTokenFromCookie();

        if (token != null) {
            apiClient.getCartSize(token) {
                runOnUiThread{
                    this.currentCartSize = it
                    invalidateOptionsMenu()
                }
            }
        }
    }

}