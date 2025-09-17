package com.debugdrawer.sampleapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.debugdrawer.DebugDrawer
import com.debugdrawer.modules.AppInfoModule
import com.debugdrawer.modules.ClipboardModule
import com.debugdrawer.modules.FeatureFlagsModule
import com.debugdrawer.modules.LogsModule
import com.debugdrawer.modules.NetworkModule
import com.debugdrawer.modules.SettingsModule
import com.debugdrawer.sampleapp.network.SampleNetworkClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Main activity demonstrating the debug drawer usage.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var debugDrawer: DebugDrawer

    @Inject
    lateinit var appInfoModule: AppInfoModule

    @Inject
    lateinit var networkModule: NetworkModule

    @Inject
    lateinit var featureFlagsModule: FeatureFlagsModule

    @Inject
    lateinit var logsModule: LogsModule

    @Inject
    lateinit var settingsModule: SettingsModule

    @Inject
    lateinit var clipboardModule: ClipboardModule

    @Inject
    lateinit var sampleNetworkClient: SampleNetworkClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupDebugDrawer()
        setupUI()
    }

    private fun setupDebugDrawer() {
        // Initialize the debug drawer
        debugDrawer.initialize(this)

        // Add all modules
        debugDrawer.addModule(appInfoModule)
        debugDrawer.addModule(networkModule)
        debugDrawer.addModule(featureFlagsModule)
        debugDrawer.addModule(logsModule)
        debugDrawer.addModule(settingsModule)
        debugDrawer.addModule(clipboardModule)
    }

    private fun setupUI() {
        findViewById<Button>(R.id.btn_toggle_debug_drawer).setOnClickListener {
            debugDrawer.toggle()
        }

        findViewById<Button>(R.id.btn_make_network_request).setOnClickListener {
            makeSampleNetworkRequest()
        }

        findViewById<Button>(R.id.btn_test_feature_flags).setOnClickListener {
            testFeatureFlags()
        }

        findViewById<Button>(R.id.btn_test_settings).setOnClickListener {
            testSettings()
        }
    }

    private fun makeSampleNetworkRequest() {
        lifecycleScope.launch {
            try {
                val result = sampleNetworkClient.getUsers()
                Toast.makeText(this@MainActivity, "Network request completed", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Network request failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun testFeatureFlags() {
        lifecycleScope.launch {
            val isNewUIEnabled = featureFlagsModule.isFeatureEnabled("enable_new_ui")
            val isAnalyticsEnabled = featureFlagsModule.isFeatureEnabled("enable_analytics")

            val message = "Feature Flags:\nNew UI: $isNewUIEnabled\nAnalytics: $isAnalyticsEnabled"
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun testSettings() {
        lifecycleScope.launch {
            val apiUrl = settingsModule.getSettingValue("api_base_url")
            val timeout = settingsModule.getSettingValue("api_timeout")

            val message = "Settings:\nAPI URL: $apiUrl\nTimeout: $timeout"
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        debugDrawer.destroy()
    }
}
