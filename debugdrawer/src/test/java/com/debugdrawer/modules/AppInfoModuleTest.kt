package com.debugdrawer.modules

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.debugdrawer.utils.Logger
import io.mockk.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.junit.Assert.*

@RunWith(RobolectricTestRunner::class)
class AppInfoModuleTest {

    private lateinit var context: Context
    private lateinit var mockLogger: Logger
    private lateinit var appInfoModule: AppInfoModule

    @Before
    fun setUp() {
        context = RuntimeEnvironment.getApplication()
        mockLogger = mockk<Logger>(relaxed = true)
        appInfoModule = AppInfoModule(context, mockLogger)
    }

    @Test
    fun `module properties should be correct`() {
        // Then
        assertEquals("app_info", appInfoModule.name)
        assertEquals("App & Device Info", appInfoModule.title)
        assertEquals("Display application and device information", appInfoModule.description)
        assertEquals(1, appInfoModule.priority)
        assertTrue(appInfoModule.isEnabled)
    }

    @Test
    fun `createView should return valid view`() {
        // When
        val view = appInfoModule.createView()
        
        // Then
        assertNotNull(view)
        verify { mockLogger.d("AppInfoModule", "Created app info view") }
    }

    @Test
    fun `onAttach should log message`() {
        // When
        appInfoModule.onAttach()
        
        // Then
        verify { mockLogger.d("AppInfoModule", "Attached to debug drawer") }
    }

    @Test
    fun `onDetach should log message`() {
        // When
        appInfoModule.onDetach()
        
        // Then
        verify { mockLogger.d("AppInfoModule", "Detached from debug drawer") }
    }
}
