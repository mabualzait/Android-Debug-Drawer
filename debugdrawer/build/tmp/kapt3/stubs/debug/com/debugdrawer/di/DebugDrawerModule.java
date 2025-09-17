package com.debugdrawer.di;

/**
 * Hilt module for providing debug drawer dependencies.
 */
@dagger.Module
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u001a\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u001a\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\u000f\u001a\u00020\bH\u0007J\u001a\u0010\u0010\u001a\u00020\u00112\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\bH\u0007J\"\u0010\u0014\u001a\u00020\u00152\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0013H\u0007J\u001a\u0010\u0017\u001a\u00020\u00182\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007\u00a8\u0006\u0019"}, d2 = {"Lcom/debugdrawer/di/DebugDrawerModule;", "", "()V", "provideAppInfoModule", "Lcom/debugdrawer/modules/AppInfoModule;", "context", "Landroid/content/Context;", "logger", "Lcom/debugdrawer/utils/Logger;", "provideClipboardModule", "Lcom/debugdrawer/modules/ClipboardModule;", "provideDebugDrawer", "Lcom/debugdrawer/DebugDrawer;", "provideFeatureFlagsModule", "Lcom/debugdrawer/modules/FeatureFlagsModule;", "provideLogger", "provideLogsModule", "Lcom/debugdrawer/modules/LogsModule;", "provideNetworkInterceptor", "Lcom/debugdrawer/utils/NetworkInterceptor;", "provideNetworkModule", "Lcom/debugdrawer/modules/NetworkModule;", "networkInterceptor", "provideSettingsModule", "Lcom/debugdrawer/modules/SettingsModule;", "debugdrawer_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DebugDrawerModule {
    @org.jetbrains.annotations.NotNull
    public static final com.debugdrawer.di.DebugDrawerModule INSTANCE = null;
    
    private DebugDrawerModule() {
        super();
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final com.debugdrawer.utils.Logger provideLogger() {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final com.debugdrawer.utils.NetworkInterceptor provideNetworkInterceptor(@org.jetbrains.annotations.NotNull
    com.debugdrawer.utils.Logger logger) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final com.debugdrawer.DebugDrawer provideDebugDrawer(@org.jetbrains.annotations.NotNull
    com.debugdrawer.utils.Logger logger) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final com.debugdrawer.modules.AppInfoModule provideAppInfoModule(@dagger.hilt.android.qualifiers.ApplicationContext
    @org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.debugdrawer.utils.Logger logger) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final com.debugdrawer.modules.NetworkModule provideNetworkModule(@dagger.hilt.android.qualifiers.ApplicationContext
    @org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.debugdrawer.utils.Logger logger, @org.jetbrains.annotations.NotNull
    com.debugdrawer.utils.NetworkInterceptor networkInterceptor) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final com.debugdrawer.modules.FeatureFlagsModule provideFeatureFlagsModule(@dagger.hilt.android.qualifiers.ApplicationContext
    @org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.debugdrawer.utils.Logger logger) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final com.debugdrawer.modules.LogsModule provideLogsModule(@dagger.hilt.android.qualifiers.ApplicationContext
    @org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.debugdrawer.utils.Logger logger) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final com.debugdrawer.modules.SettingsModule provideSettingsModule(@dagger.hilt.android.qualifiers.ApplicationContext
    @org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.debugdrawer.utils.Logger logger) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final com.debugdrawer.modules.ClipboardModule provideClipboardModule(@dagger.hilt.android.qualifiers.ApplicationContext
    @org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.debugdrawer.utils.Logger logger) {
        return null;
    }
}