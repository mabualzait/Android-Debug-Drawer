package com.abualzait.debugdrawer

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.abualzait.debugdrawer.modules.DebugModule
import com.abualzait.debugdrawer.utils.Logger
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

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
    fun `initialize should log success message`() {
        // When
        debugDrawer.initialize(mockActivity)

        // Then
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
    fun `destroy should log destroy message`() {
        // Given
        val mockModule = createMockModule("test_module")
        debugDrawer.initialize(mockActivity)
        debugDrawer.addModule(mockModule)

        // When
        debugDrawer.destroy()

        // Then
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
