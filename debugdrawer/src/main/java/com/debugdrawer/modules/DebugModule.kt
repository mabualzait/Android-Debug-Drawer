package com.debugdrawer.modules

import android.view.View

/**
 * Base interface for all debug modules.
 * Each module represents a specific debugging functionality.
 */
interface DebugModule {
    /**
     * Unique name identifier for this module.
     */
    val name: String

    /**
     * Human-readable title for display in the UI.
     */
    val title: String

    /**
     * Description of what this module does.
     */
    val description: String

    /**
     * Priority for ordering modules in the drawer (lower numbers appear first).
     */
    val priority: Int
        get() = 100

    /**
     * Whether this module is enabled and should be shown.
     */
    val isEnabled: Boolean
        get() = true

    /**
     * Create the view for this module.
     * This view will be added to the debug drawer.
     */
    fun createView(): View

    /**
     * Called when the module is attached to the drawer.
     */
    fun onAttach() {}

    /**
     * Called when the module is detached from the drawer.
     */
    fun onDetach() {}

    /**
     * Called when the debug drawer is shown.
     */
    fun onShow() {}

    /**
     * Called when the debug drawer is hidden.
     */
    fun onHide() {}
}
