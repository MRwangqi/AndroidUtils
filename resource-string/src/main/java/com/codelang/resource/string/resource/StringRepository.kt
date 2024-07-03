package com.codelang.resource.string.resource

import android.util.Log
import java.util.Locale

object StringRepository {

    private val stringMap = mutableMapOf<String, Map<String, String>>().apply {
        put("zh", mapOf("app_name" to "我是新名称"))
        put("en", mapOf("app_name" to "new app name"))
    }


    @JvmStatic
    fun getString(idName: String): String? {
        // 获取应用当前语言
        val lang = Locale.getDefault().language
        Log.e("StringRepository", "getString: $lang name=$idName")
        return stringMap[lang]?.get(idName)
    }
}