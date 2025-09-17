package com.debugdrawer.utils

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class LoggerTest {

    private lateinit var logger: Logger

    @Before
    fun setUp() {
        logger = Logger()
    }

    @Test
    fun `verbose log should work`() {
        // When
        logger.v("TestTag", "Test message")

        // Then - should not throw exception
        // In a real test, you'd verify the log was written
    }

    @Test
    fun `debug log should work`() {
        // When
        logger.d("TestTag", "Test message")

        // Then - should not throw exception
    }

    @Test
    fun `info log should work`() {
        // When
        logger.i("TestTag", "Test message")

        // Then - should not throw exception
    }

    @Test
    fun `warning log should work`() {
        // When
        logger.w("TestTag", "Test message")

        // Then - should not throw exception
    }

    @Test
    fun `error log should work`() {
        // When
        logger.e("TestTag", "Test message")

        // Then - should not throw exception
    }

    @Test
    fun `error log with throwable should work`() {
        // Given
        val throwable = RuntimeException("Test exception")

        // When
        logger.e("TestTag", "Test message", throwable)

        // Then - should not throw exception
    }

    @Test
    fun `wtf log should work`() {
        // When
        logger.wtf("TestTag", "Test message")

        // Then - should not throw exception
    }

    @Test
    fun `wtf log with throwable should work`() {
        // Given
        val throwable = RuntimeException("Test exception")

        // When
        logger.wtf("TestTag", "Test message", throwable)

        // Then - should not throw exception
    }
}
