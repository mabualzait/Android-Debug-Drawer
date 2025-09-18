package com.abualzait.debugdrawer.annotations

/**
 * Annotation to enable automatic debug drawer setup.
 * 
 * Usage:
 * 1. Add this annotation to your Application class
 * 2. The debug drawer will be automatically initialized with all default modules
 * 3. Use gestures (long press or double tap) to open the debug drawer
 * 
 * Example:
 * ```kotlin
 * @HiltAndroidApp
 * @EnableDebugDrawer
 * class MyApplication : Application() {
 *     // No additional code needed!
 * }
 * ```
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class EnableDebugDrawer(
    /**
     * Enable gesture-based activation (long press, double tap)
     * Default: true
     */
    val enableGestures: Boolean = true,
    
    /**
     * Enable automatic initialization on app start
     * Default: true
     */
    val autoInitialize: Boolean = true,
    
    /**
     * Show toast messages when debug drawer is activated
     * Default: true
     */
    val showActivationToasts: Boolean = true
)
