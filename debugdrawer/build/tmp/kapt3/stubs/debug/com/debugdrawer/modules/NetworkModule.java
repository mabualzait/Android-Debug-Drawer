package com.debugdrawer.modules;

/**
 * Debug module that displays network request logs.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u001cH\u0016J\b\u0010\u001e\u001a\u00020\u001cH\u0016J\b\u0010\u001f\u001a\u00020\u001cH\u0002J\u0010\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u001aH\u0002R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\fX\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\fX\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u0014X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\fX\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000e\u00a8\u0006\""}, d2 = {"Lcom/debugdrawer/modules/NetworkModule;", "Lcom/debugdrawer/modules/DebugModule;", "context", "Landroid/content/Context;", "logger", "Lcom/debugdrawer/utils/Logger;", "networkInterceptor", "Lcom/debugdrawer/utils/NetworkInterceptor;", "(Landroid/content/Context;Lcom/debugdrawer/utils/Logger;Lcom/debugdrawer/utils/NetworkInterceptor;)V", "adapter", "Lcom/debugdrawer/modules/NetworkLogAdapter;", "description", "", "getDescription", "()Ljava/lang/String;", "listView", "Landroid/widget/ListView;", "name", "getName", "priority", "", "getPriority", "()I", "title", "getTitle", "createView", "Landroid/view/View;", "onAttach", "", "onDetach", "onShow", "refreshLogs", "setupNetworkLogs", "view", "debugdrawer_debug"})
public final class NetworkModule implements com.debugdrawer.modules.DebugModule {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final com.debugdrawer.utils.Logger logger = null;
    @org.jetbrains.annotations.NotNull
    private final com.debugdrawer.utils.NetworkInterceptor networkInterceptor = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String name = "network";
    @org.jetbrains.annotations.NotNull
    private final java.lang.String title = "Network Logs";
    @org.jetbrains.annotations.NotNull
    private final java.lang.String description = "View HTTP request and response logs";
    private final int priority = 2;
    @org.jetbrains.annotations.Nullable
    private android.widget.ListView listView;
    @org.jetbrains.annotations.Nullable
    private com.debugdrawer.modules.NetworkLogAdapter adapter;
    
    @javax.inject.Inject
    public NetworkModule(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.debugdrawer.utils.Logger logger, @org.jetbrains.annotations.NotNull
    com.debugdrawer.utils.NetworkInterceptor networkInterceptor) {
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
    
    private final void setupNetworkLogs(android.view.View view) {
    }
    
    private final void refreshLogs() {
    }
    
    @java.lang.Override
    public void onShow() {
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
}