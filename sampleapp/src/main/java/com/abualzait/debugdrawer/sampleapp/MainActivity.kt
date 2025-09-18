package com.abualzait.debugdrawer.sampleapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.abualzait.debugdrawer.DebugDrawer
import com.abualzait.debugdrawer.modules.AppInfoModule
import com.abualzait.debugdrawer.modules.ClipboardModule
import com.abualzait.debugdrawer.modules.FeatureFlagsModule
import com.abualzait.debugdrawer.modules.LogsModule
import com.abualzait.debugdrawer.modules.NetworkModule
import com.abualzait.debugdrawer.modules.SettingsModule
import com.abualzait.debugdrawer.sampleapp.network.SampleNetworkClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
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

        findViewById<Button>(R.id.btn_generate_test_logs).setOnClickListener {
            generateTestLogs()
        }
    }

    @Suppress("TooGenericExceptionCaught")
    private fun makeSampleNetworkRequest() {
        lifecycleScope.launch {
            try {
                sampleNetworkClient.getUsers()
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

    private fun generateTestLogs() {
        lifecycleScope.launch {
            Log.v("SampleApp", "This is a VERBOSE log message for testing")
            delay(100)
            Log.d("SampleApp", "This is a DEBUG log message for testing")
            delay(100)
            Log.i("SampleApp", "This is an INFO log message for testing")
            delay(100)
            Log.w("SampleApp", "This is a WARNING log message for testing")
            delay(100)
            Log.e("SampleApp", "This is an ERROR log message for testing")
            delay(100)
            Log.wtf("SampleApp", "This is a WTF log message for testing")
            
            // Generate logs with different tags
            Log.d("NetworkModule", "Network request completed successfully")
            Log.i("UserModule", "User logged in successfully")
            Log.w("CacheModule", "Cache miss for key: user_preferences")
            Log.e("DatabaseModule", "Database connection failed")
            
            // Generate some random logs
            repeat(10) { i ->
                delay(50)
                Log.d("RandomModule", "Random log message number $i")
            }
            
            Toast.makeText(this@MainActivity, "Generated test logs! Check the Logcat Viewer in the debug drawer.", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        debugDrawer.destroy()
    }
}
