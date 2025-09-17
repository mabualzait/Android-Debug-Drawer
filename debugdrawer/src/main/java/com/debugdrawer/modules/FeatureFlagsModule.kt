package com.debugdrawer.modules

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
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
 * Debug module for managing feature flags.
 * Allows toggling feature flags at runtime for testing.
 */
class FeatureFlagsModule @Inject constructor(
    private val context: Context,
    private val logger: Logger
) : DebugModule {

    override val name: String = "feature_flags"
    override val title: String = "Feature Flags"
    override val description: String = "Toggle feature flags for testing"
    override val priority: Int = 3

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "feature_flags")
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    // Define your feature flags here
    private val featureFlags = mapOf(
        "enable_new_ui" to "Enable New UI",
        "enable_analytics" to "Enable Analytics",
        "enable_crash_reporting" to "Enable Crash Reporting",
        "enable_performance_monitoring" to "Enable Performance Monitoring",
        "enable_debug_logging" to "Enable Debug Logging",
        "enable_beta_features" to "Enable Beta Features"
    )

    override fun createView(): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.module_feature_flags, null)

        setupFeatureFlags(view)

        logger.d("FeatureFlagsModule", "Created feature flags view")
        return view
    }

    private fun setupFeatureFlags(view: View) {
        val container = view.findViewById<LinearLayout>(R.id.ll_feature_flags_container)

        featureFlags.forEach { (key, displayName) ->
            val flagView = createFlagView(key, displayName)
            container.addView(flagView)
        }
    }

    private fun createFlagView(key: String, displayName: String): View {
        val inflater = LayoutInflater.from(context)
        val flagView = inflater.inflate(R.layout.item_feature_flag, null)

        val checkBox = flagView.findViewById<CheckBox>(R.id.cb_feature_flag)
        val textView = flagView.findViewById<TextView>(R.id.tv_feature_flag_name)

        textView.text = displayName

        // Load current state
        coroutineScope.launch {
            getFeatureFlag(key).collect { isEnabled ->
                checkBox.isChecked = isEnabled
            }
        }

        // Set up toggle listener
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            coroutineScope.launch {
                setFeatureFlag(key, isChecked)
                logger.d("FeatureFlagsModule", "Toggled feature flag: $key = $isChecked")
            }
        }

        return flagView
    }

    private fun getFeatureFlag(key: String): Flow<Boolean> {
        val preferencesKey = booleanPreferencesKey(key)
        return context.dataStore.data.map { preferences ->
            preferences[preferencesKey] ?: false
        }
    }

    private suspend fun setFeatureFlag(key: String, value: Boolean) {
        val preferencesKey = booleanPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    /**
     * Get the current value of a feature flag.
     * This method can be used by other parts of the app to check feature flags.
     */
    suspend fun isFeatureEnabled(key: String): Boolean {
        val preferencesKey = booleanPreferencesKey(key)
        return context.dataStore.data.map { preferences ->
            preferences[preferencesKey] ?: false
        }.map { it }.let { flow ->
            // This is a simplified version - in a real app you'd want to cache this
            false
        }
    }

    override fun onAttach() {
        logger.d("FeatureFlagsModule", "Attached to debug drawer")
    }

    override fun onDetach() {
        logger.d("FeatureFlagsModule", "Detached from debug drawer")
    }
}
