package com.abualzait.debugdrawer

import android.app.Application
import com.abualzait.debugdrawer.annotations.EnableDebugDrawer
import com.abualzait.debugdrawer.utils.Logger
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Initializer that automatically sets up the debug drawer based on annotations.
 * This class is automatically injected and will initialize the debug drawer
 * when the application starts.
 */
@Singleton
class DebugDrawerInitializer @Inject constructor(
    private val autoSetup: DebugDrawerAutoSetup,
    private val logger: Logger,
) {
    private var isInitialized = false

    /**
     * Initialize the debug drawer if the application class has the @EnableDebugDrawer annotation.
     */
    fun initializeIfEnabled(application: Application) {
        if (isInitialized) {
            logger.w("DebugDrawerInitializer", "Already initialized")
            return
        }

        // Check if the application class has the @EnableDebugDrawer annotation
        val hasAnnotation = application.javaClass.isAnnotationPresent(EnableDebugDrawer::class.java)
        
        if (hasAnnotation) {
            logger.d("DebugDrawerInitializer", "Found @EnableDebugDrawer annotation, initializing...")
            
            // Initialize with annotation settings
            autoSetup.initialize(application)
            isInitialized = true
            
            // Initialize utils for static access
            DebugDrawerUtils.initialize(autoSetup)
            
            logger.d("DebugDrawerInitializer", "Debug drawer auto-setup completed")
        } else {
            logger.d("DebugDrawerInitializer", "No @EnableDebugDrawer annotation found, skipping auto-setup")
        }
    }

    /**
     * Get the auto-setup instance for manual control.
     */
    fun getAutoSetup(): DebugDrawerAutoSetup = autoSetup
}
