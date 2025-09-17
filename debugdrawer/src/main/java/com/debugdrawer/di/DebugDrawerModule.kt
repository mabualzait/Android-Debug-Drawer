package com.debugdrawer.di

import android.content.Context
import com.debugdrawer.DebugDrawer
import com.debugdrawer.modules.*
import com.debugdrawer.utils.Logger
import com.debugdrawer.utils.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing debug drawer dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object DebugDrawerModule {

    @Provides
    @Singleton
    fun provideLogger(): Logger = Logger()

    @Provides
    @Singleton
    fun provideNetworkInterceptor(logger: Logger): NetworkInterceptor = NetworkInterceptor(logger)

    @Provides
    @Singleton
    fun provideDebugDrawer(logger: Logger): DebugDrawer = DebugDrawer(logger)

    @Provides
    @Singleton
    fun provideAppInfoModule(
        @ApplicationContext context: Context,
        logger: Logger
    ): AppInfoModule = AppInfoModule(context, logger)

    @Provides
    @Singleton
    fun provideNetworkModule(
        @ApplicationContext context: Context,
        logger: Logger,
        networkInterceptor: NetworkInterceptor
    ): NetworkModule = NetworkModule(context, logger, networkInterceptor)

    @Provides
    @Singleton
    fun provideFeatureFlagsModule(
        @ApplicationContext context: Context,
        logger: Logger
    ): FeatureFlagsModule = FeatureFlagsModule(context, logger)

    @Provides
    @Singleton
    fun provideLogsModule(
        @ApplicationContext context: Context,
        logger: Logger
    ): LogsModule = LogsModule(context, logger)

    @Provides
    @Singleton
    fun provideSettingsModule(
        @ApplicationContext context: Context,
        logger: Logger
    ): SettingsModule = SettingsModule(context, logger)

    @Provides
    @Singleton
    fun provideClipboardModule(
        @ApplicationContext context: Context,
        logger: Logger
    ): ClipboardModule = ClipboardModule(context, logger)
}
