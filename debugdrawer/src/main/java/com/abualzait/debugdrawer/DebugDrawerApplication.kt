package com.abualzait.debugdrawer

import android.app.Application
import com.abualzait.debugdrawer.annotations.EnableDebugDrawer
import com.abualzait.debugdrawer.utils.Logger
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * Base Application class that automatically sets up the debug drawer.
 * 
 * Usage:
 * 1. Extend this class instead of Application
 * 2. Add @HiltAndroidApp annotation to your class
 * 3. The debug drawer will be automatically initialized
 * 
 * Example:
 * ```kotlin
 * @HiltAndroidApp
 * class MyApplication : DebugDrawerApplication() {
 *     // No additional code needed!
 * }
 * ```
 */
@EnableDebugDrawer
abstract class DebugDrawerApplication : Application() {
    
    @Inject
    lateinit var debugDrawerInitializer: DebugDrawerInitializer
    
    @Inject
    lateinit var logger: Logger

    override fun onCreate() {
        super.onCreate()
        
        // Automatically initialize debug drawer
        debugDrawerInitializer.initializeIfEnabled(this)
        
        logger.d("DebugDrawerApplication", "Application started with debug drawer auto-setup")
    }
}
