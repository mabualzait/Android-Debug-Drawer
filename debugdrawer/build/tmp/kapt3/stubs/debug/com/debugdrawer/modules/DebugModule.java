package com.debugdrawer.modules;

/**
 * Base interface for all debug modules.
 * Each module represents a specific debugging functionality.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0011\u001a\u00020\u0012H&J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\b\u0010\u0016\u001a\u00020\u0014H\u0016J\b\u0010\u0017\u001a\u00020\u0014H\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\bR\u0012\u0010\t\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u0005R\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0005\u00a8\u0006\u0018"}, d2 = {"Lcom/debugdrawer/modules/DebugModule;", "", "description", "", "getDescription", "()Ljava/lang/String;", "isEnabled", "", "()Z", "name", "getName", "priority", "", "getPriority", "()I", "title", "getTitle", "createView", "Landroid/view/View;", "onAttach", "", "onDetach", "onHide", "onShow", "debugdrawer_debug"})
public abstract interface DebugModule {
    
    /**
     * Unique name identifier for this module.
     */
    @org.jetbrains.annotations.NotNull
    public abstract java.lang.String getName();
    
    /**
     * Human-readable title for display in the UI.
     */
    @org.jetbrains.annotations.NotNull
    public abstract java.lang.String getTitle();
    
    /**
     * Description of what this module does.
     */
    @org.jetbrains.annotations.NotNull
    public abstract java.lang.String getDescription();
    
    public abstract int getPriority();
    
    public abstract boolean isEnabled();
    
    /**
     * Create the view for this module.
     * This view will be added to the debug drawer.
     */
    @org.jetbrains.annotations.NotNull
    public abstract android.view.View createView();
    
    /**
     * Called when the module is attached to the drawer.
     */
    public abstract void onAttach();
    
    /**
     * Called when the module is detached from the drawer.
     */
    public abstract void onDetach();
    
    /**
     * Called when the debug drawer is shown.
     */
    public abstract void onShow();
    
    /**
     * Called when the debug drawer is hidden.
     */
    public abstract void onHide();
    
    /**
     * Base interface for all debug modules.
     * Each module represents a specific debugging functionality.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
        
        public static int getPriority(@org.jetbrains.annotations.NotNull
        com.debugdrawer.modules.DebugModule $this) {
            return 0;
        }
        
        public static boolean isEnabled(@org.jetbrains.annotations.NotNull
        com.debugdrawer.modules.DebugModule $this) {
            return false;
        }
        
        /**
         * Called when the module is attached to the drawer.
         */
        public static void onAttach(@org.jetbrains.annotations.NotNull
        com.debugdrawer.modules.DebugModule $this) {
        }
        
        /**
         * Called when the module is detached from the drawer.
         */
        public static void onDetach(@org.jetbrains.annotations.NotNull
        com.debugdrawer.modules.DebugModule $this) {
        }
        
        /**
         * Called when the debug drawer is shown.
         */
        public static void onShow(@org.jetbrains.annotations.NotNull
        com.debugdrawer.modules.DebugModule $this) {
        }
        
        /**
         * Called when the debug drawer is hidden.
         */
        public static void onHide(@org.jetbrains.annotations.NotNull
        com.debugdrawer.modules.DebugModule $this) {
        }
    }
}