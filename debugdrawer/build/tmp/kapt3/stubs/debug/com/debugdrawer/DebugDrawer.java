package com.debugdrawer;

/**
 * Main debug drawer class that manages the overlay and modules.
 * This class should be initialized in debug builds only.
 */
@javax.inject.Singleton
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000bJ\u0006\u0010\u000f\u001a\u00020\rJ\u0006\u0010\u0010\u001a\u00020\rJ\u000e\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\bJ\u000e\u0010\u0015\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000bJ\u0006\u0010\u0016\u001a\u00020\rJ\u0006\u0010\u0017\u001a\u00020\rR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/debugdrawer/DebugDrawer;", "", "logger", "Lcom/debugdrawer/utils/Logger;", "(Lcom/debugdrawer/utils/Logger;)V", "debugOverlay", "Lcom/debugdrawer/DebugOverlay;", "isInitialized", "", "modules", "", "Lcom/debugdrawer/modules/DebugModule;", "addModule", "", "module", "destroy", "hide", "initialize", "activity", "Landroid/app/Activity;", "isVisible", "removeModule", "show", "toggle", "debugdrawer_debug"})
public final class DebugDrawer {
    @org.jetbrains.annotations.NotNull
    private final com.debugdrawer.utils.Logger logger = null;
    private boolean isInitialized = false;
    @org.jetbrains.annotations.Nullable
    private com.debugdrawer.DebugOverlay debugOverlay;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.debugdrawer.modules.DebugModule> modules = null;
    
    @javax.inject.Inject
    public DebugDrawer(@org.jetbrains.annotations.NotNull
    com.debugdrawer.utils.Logger logger) {
        super();
    }
    
    /**
     * Initialize the debug drawer with the given activity.
     * This should be called in onCreate() of your main activity.
     */
    public final void initialize(@org.jetbrains.annotations.NotNull
    android.app.Activity activity) {
    }
    
    /**
     * Add a debug module to the drawer.
     */
    public final void addModule(@org.jetbrains.annotations.NotNull
    com.debugdrawer.modules.DebugModule module) {
    }
    
    /**
     * Remove a debug module from the drawer.
     */
    public final void removeModule(@org.jetbrains.annotations.NotNull
    com.debugdrawer.modules.DebugModule module) {
    }
    
    /**
     * Show the debug drawer.
     */
    public final void show() {
    }
    
    /**
     * Hide the debug drawer.
     */
    public final void hide() {
    }
    
    /**
     * Toggle the debug drawer visibility.
     */
    public final void toggle() {
    }
    
    /**
     * Check if the drawer is currently visible.
     */
    public final boolean isVisible() {
        return false;
    }
    
    /**
     * Clean up resources.
     */
    public final void destroy() {
    }
}