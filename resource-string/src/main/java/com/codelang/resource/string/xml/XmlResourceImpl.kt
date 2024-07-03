package com.codelang.resource.string.xml

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater.Factory2
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class XmlResourceImpl : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        activity.layoutInflater.factory2 = XmlFactory2(activity as AppCompatActivity)
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }
}

class XmlFactory2(private val activity: AppCompatActivity) : Factory2 {
    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        val view = activity.delegate.createView(parent, name, context, attrs)
        when (view) {
            is TextView -> {
                doTextView(view, attrs)
            }
        }

        return view
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return null
    }


    private fun doTextView(textView: TextView, attrs: AttributeSet) {
        val attributeCount = attrs.attributeCount
        for (i in 0 until attributeCount) {
            when (attrs.getAttributeName(i)) {
                "text", "android:text" -> {
                    val attributeValue = attrs.getAttributeValue(i)
                    if (attributeValue != null && attributeValue.startsWith("@")) {
                        val resId = attrs.getAttributeResourceValue(i, 0)
                        textView.setText(resId)
                    }
                }

                "hint", "android:hint" -> {
                    val attributeValue = attrs.getAttributeValue(i)
                    if (attributeValue != null && attributeValue.startsWith("@")) {
                        val resId = attrs.getAttributeResourceValue(i, 0)
                        textView.setHint(resId)
                    }
                }
            }
        }
    }

}