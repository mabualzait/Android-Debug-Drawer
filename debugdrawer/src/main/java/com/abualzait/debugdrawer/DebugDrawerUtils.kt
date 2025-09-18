package com.abualzait.debugdrawer

import android.content.Context

/**
 * Utility class for easy access to debug drawer functionality.
 * This class provides static methods for common debug drawer operations.
 */
object DebugDrawerUtils {
    
    private var autoSetup: DebugDrawerAutoSetup? = null

    /**
     * Initialize the utils with an auto-setup instance.
     * This is automatically called by the auto-setup system.
     */
    fun initialize(autoSetup: DebugDrawerAutoSetup) {
        this.autoSetup = autoSetup
    }

    /**
     * Show the debug drawer programmatically.
     * 
     * @param context Application context
     */
    fun show(context: Context) {
        autoSetup?.showDebugDrawer()
    }

    /**
     * Hide the debug drawer programmatically.
     * 
     * @param context Application context
     */
    fun hide(context: Context) {
        autoSetup?.hideDebugDrawer()
    }

    /**
     * Toggle the debug drawer visibility.
     * 
     * @param context Application context
     */
    fun toggle(context: Context) {
        autoSetup?.toggleDebugDrawer()
    }

    /**
     * Check if the debug drawer is currently visible.
     * 
     * @param context Application context
     * @return true if visible, false otherwise
     */
    fun isVisible(context: Context): Boolean {
        return autoSetup?.isDebugDrawerVisible() ?: false
    }

    /**
     * Add a custom module to the debug drawer.
     * 
     * @param context Application context
     * @param module Custom debug module to add
     */
    fun addCustomModule(context: Context, module: com.abualzait.debugdrawer.modules.DebugModule) {
        autoSetup?.addCustomModule(module)
    }

    /**
     * Remove a module from the debug drawer.
     * 
     * @param context Application context
     * @param module Module to remove
     */
    fun removeModule(context: Context, module: com.abualzait.debugdrawer.modules.DebugModule) {
        autoSetup?.removeModule(module)
    }
}
