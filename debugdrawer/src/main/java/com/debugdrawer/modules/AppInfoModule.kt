package com.debugdrawer.modules

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.debugdrawer.R
import com.debugdrawer.utils.Logger
import javax.inject.Inject

/**
 * Debug module that displays app and device information.
 */
class AppInfoModule @Inject constructor(
    private val context: Context,
    private val logger: Logger
) : DebugModule {

    override val name: String = "app_info"
    override val title: String = "App & Device Info"
    override val description: String = "Display application and device information"
    override val priority: Int = 1

    override fun createView(): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.module_app_info, null)

        setupAppInfo(view)
        setupDeviceInfo(view)

        logger.d("AppInfoModule", "Created app info view")
        return view
    }

    private fun setupAppInfo(view: View) {
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            
            view.findViewById<TextView>(R.id.tv_app_name).text = getAppName()
            view.findViewById<TextView>(R.id.tv_package_name).text = context.packageName
            view.findViewById<TextView>(R.id.tv_version_name).text = packageInfo.versionName
            view.findViewById<TextView>(R.id.tv_version_code).text = packageInfo.longVersionCode.toString()
            view.findViewById<TextView>(R.id.tv_target_sdk).text = packageInfo.applicationInfo.targetSdkVersion.toString()
            view.findViewById<TextView>(R.id.tv_min_sdk).text = packageInfo.applicationInfo.minSdkVersion.toString()
        } catch (e: PackageManager.NameNotFoundException) {
            logger.e("AppInfoModule", "Failed to get package info", e)
        }
    }

    private fun setupDeviceInfo(view: View) {
        view.findViewById<TextView>(R.id.tv_device_model).text = Build.MODEL
        view.findViewById<TextView>(R.id.tv_device_manufacturer).text = Build.MANUFACTURER
        view.findViewById<TextView>(R.id.tv_android_version).text = "Android ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})"
        view.findViewById<TextView>(R.id.tv_build_id).text = Build.ID
        view.findViewById<TextView>(R.id.tv_build_type).text = Build.TYPE
        view.findViewById<TextView>(R.id.tv_build_time).text = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault())
            .format(java.util.Date(Build.TIME))
    }

    private fun getAppName(): String {
        return try {
            val applicationInfo = context.packageManager.getApplicationInfo(context.packageName, 0)
            context.packageManager.getApplicationLabel(applicationInfo).toString()
        } catch (e: PackageManager.NameNotFoundException) {
            "Unknown"
        }
    }

    override fun onAttach() {
        logger.d("AppInfoModule", "Attached to debug drawer")
    }

    override fun onDetach() {
        logger.d("AppInfoModule", "Detached from debug drawer")
    }
}
