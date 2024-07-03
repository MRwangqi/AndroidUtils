package com.codelang.androidutils

import android.app.Application
import android.content.Context
import com.codelang.resource.string.ProxyContextWrapper

class App : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(ProxyContextWrapper.wrap(base))
    }

    override fun onCreate() {
        super.onCreate()
        ProxyContextWrapper.init(this)
    }
}