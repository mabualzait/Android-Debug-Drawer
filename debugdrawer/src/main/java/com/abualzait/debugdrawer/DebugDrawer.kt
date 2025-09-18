package com.abualzait.debugdrawer

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.GridView
import android.widget.LinearLayout
import com.abualzait.debugdrawer.R
import com.abualzait.debugdrawer.adapter.DebugModuleAdapter
import com.abualzait.debugdrawer.modules.DebugModule
import com.abualzait.debugdrawer.utils.Logger
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
    private var currentModule: DebugModule? = null

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
    private var mainMenuView: View? = null
    private var moduleView: View? = null
    private var isVisible = false
    private var currentModule: DebugModule? = null
    private var moduleAdapter: DebugModuleAdapter? = null

    fun addModule(module: DebugModule) {
        // Module will be added to the adapter when it's shown
        moduleAdapter?.notifyDataSetChanged()
    }

    fun removeModule(module: DebugModule) {
        // Module will be removed from the adapter when it's shown
        moduleAdapter?.notifyDataSetChanged()
    }

    fun show() {
        if (isVisible) return

        createOverlayView()
        overlayView?.let { view ->
            val rootView = activity.findViewById<ViewGroup>(android.R.id.content)
            rootView.addView(view)
            isVisible = true
            showMainMenu()
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
        mainMenuView = null
        moduleView = null
        moduleAdapter = null
        currentModule = null
    }

    private fun createOverlayView() {
        val inflater = activity.layoutInflater
        overlayView = inflater.inflate(R.layout.debug_drawer_overlay, null).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT,
            )
        }

        // Set up overlay click to close
        overlayView?.findViewById<View>(R.id.debug_overlay_background)?.setOnClickListener {
            hide()
        }
    }

    private fun showMainMenu() {
        val inflater = activity.layoutInflater
        mainMenuView = inflater.inflate(R.layout.debug_drawer_main_menu, null)
        
        val drawerContainer = overlayView?.findViewById<ViewGroup>(R.id.debug_drawer_container)
        drawerContainer?.removeAllViews()
        drawerContainer?.addView(mainMenuView)

        // Set up modules grid
        val modulesGrid = mainMenuView?.findViewById<GridView>(R.id.gv_modules)
        moduleAdapter = DebugModuleAdapter(activity, modules) { module ->
            showModule(module)
        }
        modulesGrid?.adapter = moduleAdapter

        // Set up close button
        mainMenuView?.findViewById<View>(R.id.btn_close_drawer)?.setOnClickListener {
            hide()
        }
    }

    private fun showModule(module: DebugModule) {
        currentModule = module
        val inflater = activity.layoutInflater
        moduleView = inflater.inflate(R.layout.debug_module_view, null)
        
        val drawerContainer = overlayView?.findViewById<ViewGroup>(R.id.debug_drawer_container)
        drawerContainer?.removeAllViews()
        drawerContainer?.addView(moduleView)

        // Set module title
        moduleView?.findViewById<android.widget.TextView>(R.id.tv_module_title)?.text = module.title

        // Add module content
        val contentContainer = moduleView?.findViewById<FrameLayout>(R.id.fl_module_content)
        val moduleContentView = module.createView()
        contentContainer?.addView(moduleContentView)

        // Set up back button
        moduleView?.findViewById<View>(R.id.btn_back_to_menu)?.setOnClickListener {
            showMainMenu()
        }

        // Set up close button
        moduleView?.findViewById<View>(R.id.btn_close_drawer)?.setOnClickListener {
            hide()
        }
    }
}
