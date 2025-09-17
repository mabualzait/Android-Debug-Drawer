package com.debugdrawer.modules;

/**
 * Debug module that displays app and device information.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\bH\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0017H\u0016J\u0010\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0014H\u0002J\u0010\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0014H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\bX\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0014\u0010\r\u001a\u00020\u000eX\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\bX\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\n\u00a8\u0006\u001c"}, d2 = {"Lcom/debugdrawer/modules/AppInfoModule;", "Lcom/debugdrawer/modules/DebugModule;", "context", "Landroid/content/Context;", "logger", "Lcom/debugdrawer/utils/Logger;", "(Landroid/content/Context;Lcom/debugdrawer/utils/Logger;)V", "description", "", "getDescription", "()Ljava/lang/String;", "name", "getName", "priority", "", "getPriority", "()I", "title", "getTitle", "createView", "Landroid/view/View;", "getAppName", "onAttach", "", "onDetach", "setupAppInfo", "view", "setupDeviceInfo", "debugdrawer_debug"})
public final class AppInfoModule implements com.debugdrawer.modules.DebugModule {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final com.debugdrawer.utils.Logger logger = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String name = "app_info";
    @org.jetbrains.annotations.NotNull
    private final java.lang.String title = "App & Device Info";
    @org.jetbrains.annotations.NotNull
    private final java.lang.String description = "Display application and device information";
    private final int priority = 1;
    
    @javax.inject.Inject
    public AppInfoModule(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.debugdrawer.utils.Logger logger) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String getName() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String getTitle() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String getDescription() {
        return null;
    }
    
    @java.lang.Override
    public int getPriority() {
        return 0;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public android.view.View createView() {
        return null;
    }
    
    private final void setupAppInfo(android.view.View view) {
    }
    
    private final void setupDeviceInfo(android.view.View view) {
    }
    
    private final java.lang.String getAppName() {
        return null;
    }
    
    @java.lang.Override
    public void onAttach() {
    }
    
    @java.lang.Override
    public void onDetach() {
    }
    
    @java.lang.Override
    public boolean isEnabled() {
        return false;
    }
    
    @java.lang.Override
    public void onHide() {
    }
    
    @java.lang.Override
    public void onShow() {
    }
}