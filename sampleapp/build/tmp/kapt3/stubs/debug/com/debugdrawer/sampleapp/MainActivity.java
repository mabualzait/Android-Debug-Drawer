package com.debugdrawer.sampleapp;

/**
 * Main activity demonstrating the debug drawer usage.
 */
@dagger.hilt.android.AndroidEntryPoint
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u00103\u001a\u000204H\u0002J\u0012\u00105\u001a\u0002042\b\u00106\u001a\u0004\u0018\u000107H\u0014J\b\u00108\u001a\u000204H\u0014J\b\u00109\u001a\u000204H\u0002J\b\u0010:\u001a\u000204H\u0002J\b\u0010;\u001a\u000204H\u0002J\b\u0010<\u001a\u000204H\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0015\u001a\u00020\u00168\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\u001b\u001a\u00020\u001c8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001e\u0010!\u001a\u00020\"8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001e\u0010\'\u001a\u00020(8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001e\u0010-\u001a\u00020.8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102\u00a8\u0006="}, d2 = {"Lcom/debugdrawer/sampleapp/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "appInfoModule", "Lcom/debugdrawer/modules/AppInfoModule;", "getAppInfoModule", "()Lcom/debugdrawer/modules/AppInfoModule;", "setAppInfoModule", "(Lcom/debugdrawer/modules/AppInfoModule;)V", "clipboardModule", "Lcom/debugdrawer/modules/ClipboardModule;", "getClipboardModule", "()Lcom/debugdrawer/modules/ClipboardModule;", "setClipboardModule", "(Lcom/debugdrawer/modules/ClipboardModule;)V", "debugDrawer", "Lcom/debugdrawer/DebugDrawer;", "getDebugDrawer", "()Lcom/debugdrawer/DebugDrawer;", "setDebugDrawer", "(Lcom/debugdrawer/DebugDrawer;)V", "featureFlagsModule", "Lcom/debugdrawer/modules/FeatureFlagsModule;", "getFeatureFlagsModule", "()Lcom/debugdrawer/modules/FeatureFlagsModule;", "setFeatureFlagsModule", "(Lcom/debugdrawer/modules/FeatureFlagsModule;)V", "logsModule", "Lcom/debugdrawer/modules/LogsModule;", "getLogsModule", "()Lcom/debugdrawer/modules/LogsModule;", "setLogsModule", "(Lcom/debugdrawer/modules/LogsModule;)V", "networkModule", "Lcom/debugdrawer/modules/NetworkModule;", "getNetworkModule", "()Lcom/debugdrawer/modules/NetworkModule;", "setNetworkModule", "(Lcom/debugdrawer/modules/NetworkModule;)V", "sampleNetworkClient", "Lcom/debugdrawer/sampleapp/network/SampleNetworkClient;", "getSampleNetworkClient", "()Lcom/debugdrawer/sampleapp/network/SampleNetworkClient;", "setSampleNetworkClient", "(Lcom/debugdrawer/sampleapp/network/SampleNetworkClient;)V", "settingsModule", "Lcom/debugdrawer/modules/SettingsModule;", "getSettingsModule", "()Lcom/debugdrawer/modules/SettingsModule;", "setSettingsModule", "(Lcom/debugdrawer/modules/SettingsModule;)V", "makeSampleNetworkRequest", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "setupDebugDrawer", "setupUI", "testFeatureFlags", "testSettings", "sampleapp_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    @javax.inject.Inject
    public com.debugdrawer.DebugDrawer debugDrawer;
    @javax.inject.Inject
    public com.debugdrawer.modules.AppInfoModule appInfoModule;
    @javax.inject.Inject
    public com.debugdrawer.modules.NetworkModule networkModule;
    @javax.inject.Inject
    public com.debugdrawer.modules.FeatureFlagsModule featureFlagsModule;
    @javax.inject.Inject
    public com.debugdrawer.modules.LogsModule logsModule;
    @javax.inject.Inject
    public com.debugdrawer.modules.SettingsModule settingsModule;
    @javax.inject.Inject
    public com.debugdrawer.modules.ClipboardModule clipboardModule;
    @javax.inject.Inject
    public com.debugdrawer.sampleapp.network.SampleNetworkClient sampleNetworkClient;
    
    public MainActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.debugdrawer.DebugDrawer getDebugDrawer() {
        return null;
    }
    
    public final void setDebugDrawer(@org.jetbrains.annotations.NotNull
    com.debugdrawer.DebugDrawer p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.debugdrawer.modules.AppInfoModule getAppInfoModule() {
        return null;
    }
    
    public final void setAppInfoModule(@org.jetbrains.annotations.NotNull
    com.debugdrawer.modules.AppInfoModule p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.debugdrawer.modules.NetworkModule getNetworkModule() {
        return null;
    }
    
    public final void setNetworkModule(@org.jetbrains.annotations.NotNull
    com.debugdrawer.modules.NetworkModule p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.debugdrawer.modules.FeatureFlagsModule getFeatureFlagsModule() {
        return null;
    }
    
    public final void setFeatureFlagsModule(@org.jetbrains.annotations.NotNull
    com.debugdrawer.modules.FeatureFlagsModule p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.debugdrawer.modules.LogsModule getLogsModule() {
        return null;
    }
    
    public final void setLogsModule(@org.jetbrains.annotations.NotNull
    com.debugdrawer.modules.LogsModule p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.debugdrawer.modules.SettingsModule getSettingsModule() {
        return null;
    }
    
    public final void setSettingsModule(@org.jetbrains.annotations.NotNull
    com.debugdrawer.modules.SettingsModule p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.debugdrawer.modules.ClipboardModule getClipboardModule() {
        return null;
    }
    
    public final void setClipboardModule(@org.jetbrains.annotations.NotNull
    com.debugdrawer.modules.ClipboardModule p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.debugdrawer.sampleapp.network.SampleNetworkClient getSampleNetworkClient() {
        return null;
    }
    
    public final void setSampleNetworkClient(@org.jetbrains.annotations.NotNull
    com.debugdrawer.sampleapp.network.SampleNetworkClient p0) {
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupDebugDrawer() {
    }
    
    private final void setupUI() {
    }
    
    private final void makeSampleNetworkRequest() {
    }
    
    private final void testFeatureFlags() {
    }
    
    private final void testSettings() {
    }
    
    @java.lang.Override
    protected void onDestroy() {
    }
}