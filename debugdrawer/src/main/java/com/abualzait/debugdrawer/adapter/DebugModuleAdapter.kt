package com.abualzait.debugdrawer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.abualzait.debugdrawer.R
import com.abualzait.debugdrawer.modules.DebugModule

/**
 * Adapter for displaying debug modules in a grid view.
 */
class DebugModuleAdapter(
    private val context: Context,
    private val modules: List<DebugModule>,
    private val onModuleClick: (DebugModule) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int = modules.size

    override fun getItem(position: Int): DebugModule = modules[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.debug_module_item, parent, false)

        val module = getItem(position)
        val iconView = view.findViewById<ImageView>(R.id.iv_module_icon)
        val titleView = view.findViewById<TextView>(R.id.tv_module_title)
        val descriptionView = view.findViewById<TextView>(R.id.tv_module_description)

        // Set module icon based on module type
        iconView.setImageResource(getModuleIcon(module.name))
        
        titleView.text = module.title
        descriptionView.text = module.description

        // Set click listener
        view.setOnClickListener {
            onModuleClick(module)
        }

        return view
    }

    private fun getModuleIcon(moduleName: String): Int {
        return when (moduleName) {
            "app_info" -> android.R.drawable.ic_menu_info_details
            "network" -> android.R.drawable.ic_menu_share
            "feature_flags" -> android.R.drawable.ic_menu_preferences
            "logcat" -> android.R.drawable.ic_menu_edit
            "settings" -> android.R.drawable.ic_menu_manage
            "clipboard" -> android.R.drawable.ic_menu_edit
            else -> android.R.drawable.ic_menu_info_details
        }
    }
}
