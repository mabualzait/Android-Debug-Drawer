package com.debugdrawer;

/**
 * Debug overlay that handles the UI presentation of the debug drawer.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006J\u0010\u0010\u0010\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0006H\u0002J\b\u0010\u0011\u001a\u00020\u000eH\u0002J\u0006\u0010\u0012\u001a\u00020\u000eJ\u0006\u0010\u0013\u001a\u00020\u000eJ\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006J\b\u0010\u0015\u001a\u00020\u000eH\u0002J\u0006\u0010\u0016\u001a\u00020\u000eJ\u0006\u0010\u0017\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/debugdrawer/DebugOverlay;", "", "activity", "Landroid/app/Activity;", "modules", "", "Lcom/debugdrawer/modules/DebugModule;", "(Landroid/app/Activity;Ljava/util/List;)V", "drawerContainer", "Landroid/view/View;", "isVisible", "", "overlayView", "addModule", "", "module", "createModuleView", "createOverlayView", "destroy", "hide", "removeModule", "setupModules", "show", "toggle", "debugdrawer_debug"})
public final class DebugOverlay {
    @org.jetbrains.annotations.NotNull
    private final android.app.Activity activity = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.debugdrawer.modules.DebugModule> modules = null;
    @org.jetbrains.annotations.Nullable
    private android.view.View overlayView;
    @org.jetbrains.annotations.Nullable
    private android.view.View drawerContainer;
    private boolean isVisible = false;
    
    public DebugOverlay(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.NotNull
    java.util.List<com.debugdrawer.modules.DebugModule> modules) {
        super();
    }
    
    public final void addModule(@org.jetbrains.annotations.NotNull
    com.debugdrawer.modules.DebugModule module) {
    }
    
    public final void removeModule(@org.jetbrains.annotations.NotNull
    com.debugdrawer.modules.DebugModule module) {
    }
    
    public final void show() {
    }
    
    public final void hide() {
    }
    
    public final void toggle() {
    }
    
    public final boolean isVisible() {
        return false;
    }
    
    public final void destroy() {
    }
    
    private final void createOverlayView() {
    }
    
    private final void setupModules() {
    }
    
    private final android.view.View createModuleView(com.debugdrawer.modules.DebugModule module) {
        return null;
    }
}