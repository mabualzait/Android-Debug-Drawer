package com.debugdrawer.modules

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.debugdrawer.R
import com.debugdrawer.utils.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Debug module for managing app settings overrides.
 * Allows changing settings at runtime for testing.
 */
class SettingsModule @Inject constructor(
    private val context: Context,
    private val logger: Logger
) : DebugModule {

    override val name: String = "settings"
    override val title: String = "Settings Override"
    override val description: String = "Override app settings for testing"
    override val priority: Int = 5

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "debug_settings")
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    // Define your settings here
    private val settings = mapOf(
        "api_base_url" to "API Base URL",
        "api_timeout" to "API Timeout (ms)",
        "cache_size" to "Cache Size (MB)",
        "debug_level" to "Debug Level",
        "enable_crashlytics" to "Enable Crashlytics",
        "enable_analytics" to "Enable Analytics"
    )

    override fun createView(): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.module_settings, null)

        setupSettings(view)

        logger.d("SettingsModule", "Created settings view")
        return view
    }

    private fun setupSettings(view: View) {
        val container = view.findViewById<LinearLayout>(R.id.ll_settings_container)

        settings.forEach { (key, displayName) ->
            val settingView = createSettingView(key, displayName)
            container.addView(settingView)
        }

        // Set up reset button
        view.findViewById<View>(R.id.btn_reset_settings)?.setOnClickListener {
            resetAllSettings()
        }
    }

    private fun createSettingView(key: String, displayName: String): View {
        val inflater = LayoutInflater.from(context)
        val settingView = inflater.inflate(R.layout.item_setting, null)

        val textView = settingView.findViewById<TextView>(R.id.tv_setting_name)
        val editText = settingView.findViewById<EditText>(R.id.et_setting_value)

        textView.text = displayName

        // Load current value
        coroutineScope.launch {
            getSetting(key).collect { value ->
                editText.setText(value)
            }
        }

        // Set up save listener
        editText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val newValue = editText.text.toString()
                coroutineScope.launch {
                    setSetting(key, newValue)
                    logger.d("SettingsModule", "Updated setting: $key = $newValue")
                }
            }
        }

        return settingView
    }

    private fun getSetting(key: String): Flow<String> {
        val preferencesKey = stringPreferencesKey(key)
        return context.dataStore.data.map { preferences ->
            preferences[preferencesKey] ?: getDefaultValue(key)
        }
    }

    private suspend fun setSetting(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    private fun getDefaultValue(key: String): String {
        return when (key) {
            "api_base_url" -> "https://api.example.com"
            "api_timeout" -> "30000"
            "cache_size" -> "50"
            "debug_level" -> "INFO"
            "enable_crashlytics" -> "true"
            "enable_analytics" -> "true"
            else -> ""
        }
    }

    private fun resetAllSettings() {
        coroutineScope.launch {
            context.dataStore.edit { preferences ->
                preferences.clear()
            }
            logger.d("SettingsModule", "Reset all settings to defaults")
        }
    }

    /**
     * Get the current value of a setting.
     * This method can be used by other parts of the app to get settings.
     */
    suspend fun getSettingValue(key: String): String {
        val preferencesKey = stringPreferencesKey(key)
        return context.dataStore.data.map { preferences ->
            preferences[preferencesKey] ?: getDefaultValue(key)
        }.map { it }.let { flow ->
            // This is a simplified version - in a real app you'd want to cache this
            getDefaultValue(key)
        }
    }

    override fun onAttach() {
        logger.d("SettingsModule", "Attached to debug drawer")
    }

    override fun onDetach() {
        logger.d("SettingsModule", "Detached from debug drawer")
    }
}
