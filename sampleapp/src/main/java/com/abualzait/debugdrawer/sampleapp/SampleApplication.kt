package com.abualzait.debugdrawer.sampleapp

import com.abualzait.debugdrawer.DebugDrawerApplication
import dagger.hilt.android.HiltAndroidApp

/**
 * Sample application class demonstrating the new plug-and-play integration.
 * 
 * With the new auto-setup system, you only need to:
 * 1. Extend DebugDrawerApplication instead of Application
 * 2. Add @HiltAndroidApp annotation
 * 3. That's it! The debug drawer will be automatically initialized
 * 
 * The debug drawer can be activated by:
 * - Long pressing anywhere on the screen
 * - Double tapping anywhere on the screen
 * - Using DebugDrawerUtils.toggle(context) programmatically
 */
@HiltAndroidApp
class SampleApplication : DebugDrawerApplication()
