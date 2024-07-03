package com.codelang.androidutils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.codelang.resource.string.ProxyContextWrapper

class MainActivity : AppCompatActivity() {


    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ProxyContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tvTitle).text = getString(R.string.app_name)

        Log.e("TAG", "onCreate: "+ resources)

    }
}