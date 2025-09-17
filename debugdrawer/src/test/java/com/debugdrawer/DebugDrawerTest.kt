package com.debugdrawer

import com.debugdrawer.modules.DebugModule
import com.debugdrawer.utils.Logger
import io.mockk.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import android.app.Activity
import android.view.View
import android.widget.FrameLayout
import org.junit.Assert.*

@RunWith(RobolectricTestRunner::class)
class DebugDrawerTest {

    private lateinit var debugDrawer: DebugDrawer
    private lateinit var mockLogger: Logger
    private lateinit var mockActivity: Activity

    @Before
    fun setUp() {
        mockLogger = mockk<Logger>(relaxed = true)
        mockActivity = mockk<Activity>(relaxed = true)
        
        // Mock the content view
        val mockRootView = mockk<FrameLayout>(relaxed = true)
        every { mockActivity.findViewById<ViewGroup>(android.R.id.content) } returns mockRootView
        
        debugDrawer = DebugDrawer(mockLogger)
    }

    @Test
    fun `initialize should set initialized flag`() {
        // When
        debugDrawer.initialize(mockActivity)
        
        // Then
        assertTrue(debugDrawer.isInitialized)
        verify { mockLogger.d("DebugDrawer", "Initialized successfully") }
    }

    @Test
    fun `initialize when already initialized should log warning`() {
        // Given
        debugDrawer.initialize(mockActivity)
        
        // When
        debugDrawer.initialize(mockActivity)
        
        // Then
        verify { mockLogger.w("DebugDrawer", "Already initialized") }
    }

    @Test
    fun `addModule should add module to list`() {
        // Given
        val mockModule = createMockModule("test_module")
        debugDrawer.initialize(mockActivity)
        
        // When
        debugDrawer.addModule(mockModule)
        
        // Then
        verify { mockLogger.d("DebugDrawer", "Added module: test_module") }
    }

    @Test
    fun `removeModule should remove module from list`() {
        // Given
        val mockModule = createMockModule("test_module")
        debugDrawer.initialize(mockActivity)
        debugDrawer.addModule(mockModule)
        
        // When
        debugDrawer.removeModule(mockModule)
        
        // Then
        verify { mockLogger.d("DebugDrawer", "Removed module: test_module") }
    }

    @Test
    fun `destroy should reset state`() {
        // Given
        val mockModule = createMockModule("test_module")
        debugDrawer.initialize(mockActivity)
        debugDrawer.addModule(mockModule)
        
        // When
        debugDrawer.destroy()
        
        // Then
        assertFalse(debugDrawer.isInitialized)
        verify { mockLogger.d("DebugDrawer", "Destroyed") }
    }

    private fun createMockModule(name: String): DebugModule {
        return mockk<DebugModule>(relaxed = true).apply {
            every { this@apply.name } returns name
            every { this@apply.title } returns "Test Module"
            every { this@apply.description } returns "Test Description"
            every { this@apply.createView() } returns mockk<View>(relaxed = true)
        }
    }
}
