package com.example.hdtestapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
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

}