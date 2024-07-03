package com.codelang.resource.string.resource

import android.content.res.AssetManager
import android.content.res.Configuration
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.Log
import java.util.Locale

internal class ProxyResourceImpl(
    assets: AssetManager,
    metrics: DisplayMetrics,
    config: Configuration
) :
    Resources(assets, metrics, config) {

    override fun getString(id: Int): String {
        // todo 1、根据 id 获取 string name
        val name = getResourceEntryName(id)
        Log.e("ProxyResourceImpl", "getString: id=$id name=$name")
        // todo 2、获取当前语言环境
        // todo 2、根据语言与 name 从 repository 里面查找对应的 value，查到就直接返回，查不到则交由 resource
        return StringRepository.getString(name) ?: super.getString(id)
    }

    override fun getString(id: Int, vararg formatArgs: Any?): String {
        val name = getResourceEntryName(id)
        Log.e("ProxyResourceImpl", "getString2: id=$id name=$name")
        return StringRepository.getString(name) ?: super.getString(id, formatArgs)
    }

    override fun getText(id: Int): CharSequence {
        val name = getResourceEntryName(id)
        Log.e("ProxyResourceImpl", "getText: id=$id name=$name")
        return StringRepository.getString(name) ?: super.getText(id)
    }

    override fun getText(id: Int, def: CharSequence?): CharSequence {
        val name = getResourceEntryName(id)
        Log.e("ProxyResourceImpl", "getText2: id=$id name=$name")
        return StringRepository.getString(name) ?: super.getText(id, def)
    }
}