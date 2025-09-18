package com.abualzait.debugdrawer

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.abualzait.debugdrawer.modules.AppInfoModule
import com.abualzait.debugdrawer.modules.ClipboardModule
import com.abualzait.debugdrawer.modules.FeatureFlagsModule
import com.abualzait.debugdrawer.modules.LogsModule
import com.abualzait.debugdrawer.modules.NetworkModule
import com.abualzait.debugdrawer.modules.SettingsModule
import com.abualzait.debugdrawer.utils.Logger
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Auto-setup class that provides plug-and-play integration for the debug drawer.
 * This class handles automatic initialization, module registration, and gesture detection.
 */
@Singleton
class DebugDrawerAutoSetup @Inject constructor(
    private val logger: Logger,
    private val appInfoModule: AppInfoModule,
    private val networkModule: NetworkModule,
    private val featureFlagsModule: FeatureFlagsModule,
    private val logsModule: LogsModule,
    private val settingsModule: SettingsModule,
    private val clipboardModule: ClipboardModule,
) {
    private var debugDrawer: DebugDrawer? = null
    private var isInitialized = false
    private var gestureDetector: GestureDetector? = null
    private var currentActivity: Activity? = null

    /**
     * Initialize the debug drawer with automatic setup.
     * Call this from your Application class.
     */
    fun initialize(application: Application) {
        if (isInitialized) {
            logger.w("DebugDrawerAutoSetup", "Already initialized")
            return
        }

        // Create debug drawer instance
        debugDrawer = DebugDrawer(logger)
        
        // Register all default modules
        registerDefaultModules()
        
        // Set up activity lifecycle callbacks for automatic initialization
        setupActivityLifecycleCallbacks(application)
        
        isInitialized = true
        logger.d("DebugDrawerAutoSetup", "Auto-setup initialized successfully")
    }

    /**
     * Register all default debug modules.
     */
    private fun registerDefaultModules() {
        debugDrawer?.let { drawer ->
            drawer.addModule(appInfoModule)
            drawer.addModule(networkModule)
            drawer.addModule(featureFlagsModule)
            drawer.addModule(logsModule)
            drawer.addModule(settingsModule)
            drawer.addModule(clipboardModule)
            logger.d("DebugDrawerAutoSetup", "Registered 6 default modules")
        }
    }

    /**
     * Set up activity lifecycle callbacks for automatic initialization.
     */
    private fun setupActivityLifecycleCallbacks(application: Application) {
        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                // Auto-initialize on first activity creation
                if (!isInitialized) return
                
                currentActivity = activity
                debugDrawer?.initialize(activity)
                setupGestureDetection(activity)
                logger.d("DebugDrawerAutoSetup", "Auto-initialized for activity: ${activity.javaClass.simpleName}")
            }

            override fun onActivityStarted(activity: Activity) {
                currentActivity = activity
            }

            override fun onActivityResumed(activity: Activity) {
                currentActivity = activity
            }

            override fun onActivityPaused(activity: Activity) {
                // Hide debug drawer when activity is paused
                if (debugDrawer?.isVisible() == true) {
                    debugDrawer?.hide()
                }
            }

            override fun onActivityStopped(activity: Activity) {
                // No action needed
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                // No action needed
            }

            override fun onActivityDestroyed(activity: Activity) {
                if (activity == currentActivity) {
                    currentActivity = null
                    debugDrawer?.destroy()
                }
            }
        })
    }

    /**
     * Set up gesture detection for easy activation.
     */
    private fun setupGestureDetection(activity: Activity) {
        gestureDetector = GestureDetector(activity, object : GestureDetector.SimpleOnGestureListener() {
            override fun onLongPress(e: MotionEvent) {
                // Long press to open debug drawer
                debugDrawer?.toggle()
                showActivationToast(activity)
            }

            override fun onDoubleTap(e: MotionEvent): Boolean {
                // Double tap to open debug drawer
                debugDrawer?.toggle()
                showActivationToast(activity)
                return true
            }
        })

        // Set up touch listener on root view
        val rootView = activity.findViewById<View>(android.R.id.content)
        rootView?.setOnTouchListener { _, event ->
            gestureDetector?.onTouchEvent(event) ?: false
        }
    }

    /**
     * Show a toast indicating debug drawer activation.
     */
    private fun showActivationToast(context: Context) {
        if (debugDrawer?.isVisible() == true) {
            Toast.makeText(context, "Debug Drawer Opened", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Debug Drawer Closed", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Manually show the debug drawer.
     */
    fun showDebugDrawer() {
        debugDrawer?.show()
    }

    /**
     * Manually hide the debug drawer.
     */
    fun hideDebugDrawer() {
        debugDrawer?.hide()
    }

    /**
     * Toggle the debug drawer visibility.
     */
    fun toggleDebugDrawer() {
        debugDrawer?.toggle()
    }

    /**
     * Check if debug drawer is visible.
     */
    fun isDebugDrawerVisible(): Boolean = debugDrawer?.isVisible() ?: false

    /**
     * Add a custom module to the debug drawer.
     */
    fun addCustomModule(module: com.abualzait.debugdrawer.modules.DebugModule) {
        debugDrawer?.addModule(module)
        logger.d("DebugDrawerAutoSetup", "Added custom module: ${module.name}")
    }

    /**
     * Remove a module from the debug drawer.
     */
    fun removeModule(module: com.abualzait.debugdrawer.modules.DebugModule) {
        debugDrawer?.removeModule(module)
        logger.d("DebugDrawerAutoSetup", "Removed module: ${module.name}")
    }

    /**
     * Clean up resources.
     */
    fun destroy() {
        debugDrawer?.destroy()
        debugDrawer = null
        gestureDetector = null
        currentActivity = null
        isInitialized = false
        logger.d("DebugDrawerAutoSetup", "Auto-setup destroyed")
    }
}
