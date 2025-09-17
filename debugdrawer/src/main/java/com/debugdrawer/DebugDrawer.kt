package com.debugdrawer

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.debugdrawer.modules.DebugModule
import com.debugdrawer.utils.Logger
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Main debug drawer class that manages the overlay and modules.
 * This class should be initialized in debug builds only.
 */
@Singleton
class DebugDrawer @Inject constructor(
    private val logger: Logger,
) {
    private var isInitialized = false
    private var debugOverlay: DebugOverlay? = null
    private val modules = mutableListOf<DebugModule>()

    /**
     * Initialize the debug drawer with the given activity.
     * This should be called in onCreate() of your main activity.
     */
    fun initialize(activity: Activity) {
        if (isInitialized) {
            logger.w("DebugDrawer", "Already initialized")
            return
        }

        if (debugOverlay == null) {
            debugOverlay = DebugOverlay(activity, modules)
        }

        isInitialized = true
        logger.d("DebugDrawer", "Initialized successfully")
    }

    /**
     * Add a debug module to the drawer.
     */
    fun addModule(module: DebugModule) {
        modules.add(module)
        debugOverlay?.addModule(module)
        logger.d("DebugDrawer", "Added module: ${module.name}")
    }

    /**
     * Remove a debug module from the drawer.
     */
    fun removeModule(module: DebugModule) {
        modules.remove(module)
        debugOverlay?.removeModule(module)
        logger.d("DebugDrawer", "Removed module: ${module.name}")
    }

    /**
     * Show the debug drawer.
     */
    fun show() {
        debugOverlay?.show()
    }

    /**
     * Hide the debug drawer.
     */
    fun hide() {
        debugOverlay?.hide()
    }

    /**
     * Toggle the debug drawer visibility.
     */
    fun toggle() {
        debugOverlay?.toggle()
    }

    /**
     * Check if the drawer is currently visible.
     */
    fun isVisible(): Boolean = debugOverlay?.isVisible() ?: false

    /**
     * Clean up resources.
     */
    fun destroy() {
        debugOverlay?.destroy()
        debugOverlay = null
        modules.clear()
        isInitialized = false
        logger.d("DebugDrawer", "Destroyed")
    }
}

/**
 * Debug overlay that handles the UI presentation of the debug drawer.
 */
internal class DebugOverlay(
    private val activity: Activity,
    private val modules: MutableList<DebugModule>,
) {
    private var overlayView: View? = null
    private var drawerContainer: View? = null
    private var isVisible = false

    fun addModule(module: DebugModule) {
        // Module will be added to the drawer when it's shown
    }

    fun removeModule(module: DebugModule) {
        // Module will be removed from the drawer when it's shown
    }

    fun show() {
        if (isVisible) return

        createOverlayView()
        overlayView?.let { view ->
            val rootView = activity.findViewById<ViewGroup>(android.R.id.content)
            rootView.addView(view)
            isVisible = true
            android.util.Log.d("DebugDrawer", "Debug drawer shown with ${modules.size} modules")
        }
    }

    fun hide() {
        overlayView?.let { view ->
            val rootView = activity.findViewById<ViewGroup>(android.R.id.content)
            rootView.removeView(view)
        }
        isVisible = false
    }

    fun toggle() {
        if (isVisible) hide() else show()
    }

    fun isVisible(): Boolean = isVisible

    fun destroy() {
        hide()
        overlayView = null
        drawerContainer = null
    }

    private fun createOverlayView() {
        val inflater = activity.layoutInflater
        overlayView = inflater.inflate(R.layout.debug_drawer_overlay, null).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT,
            )
        }

        // Set up the drawer container
        drawerContainer = overlayView?.findViewById(R.id.debug_drawer_container)

        // Add modules to the drawer
        setupModules()

        // Set up close button
        overlayView?.findViewById<View>(R.id.btn_close_drawer)?.setOnClickListener {
            hide()
        }

        // Set up overlay click to close
        overlayView?.findViewById<View>(R.id.debug_overlay_background)?.setOnClickListener {
            hide()
        }
    }

    private fun setupModules() {
        val modulesContainer = drawerContainer?.findViewById<android.widget.LinearLayout>(R.id.ll_modules_container)
        modulesContainer?.removeAllViews()

        android.util.Log.d("DebugDrawer", "Setting up ${modules.size} modules")
        modules.forEach { module ->
            android.util.Log.d("DebugDrawer", "Adding module: ${module.name}")
            val moduleView = createModuleView(module)
            modulesContainer?.addView(moduleView)
        }
    }

    private fun createModuleView(module: DebugModule): View {
        val inflater = activity.layoutInflater
        val moduleContainer = inflater.inflate(R.layout.debug_module_container, null)

        // Set module title
        moduleContainer.findViewById<android.widget.TextView>(R.id.tv_module_title).text = module.title

        // Set module description
        moduleContainer.findViewById<android.widget.TextView>(R.id.tv_module_description).text = module.description

        // Add module content
        val contentContainer = moduleContainer.findViewById<android.widget.LinearLayout>(R.id.ll_module_content)
        val moduleContentView = module.createView()
        contentContainer.addView(moduleContentView)

        return moduleContainer
    }
}
