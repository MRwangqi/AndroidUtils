package com.codelang.resource.string

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Resources
import com.codelang.resource.string.resource.ProxyResourceImpl
import com.codelang.resource.string.xml.XmlResourceImpl

/**
 *
 * Application 与 Activity 的 attachBaseContext 必须复写如下代码，替换成新的 ProxyContextWrapper
 *
 *    override fun attachBaseContext(newBase: Context) {
 *         super.attachBaseContext(ProxyContextWrapper.wrap(newBase))
 *     }
 */
class ProxyContextWrapper private constructor(private val base: Context) : ContextWrapper(base) {

    companion object {
        /**
         * 替换 Context
         */
        fun wrap(context: Context): Context {
            return ProxyContextWrapper(context)
        }

        /**
         * 初始化
         */
        fun init(app: Application) {
            app.registerActivityLifecycleCallbacks(XmlResourceImpl())
        }
    }


    override fun getResources(): Resources {
        return ProxyResourceImpl(
            base.assets,
            base.resources.displayMetrics,
            base.resources.configuration
        )
    }
}