package com.example.quizapp

import com.example.quizapp.extensions.isZero
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Unit test class for Extensions.
 */
class ExtensionsTest {

    /**
     * Test method to verify behavior of [isZero] with zero value.
     */
    @Test
    fun `isZero with zero value`() {
        val zero = 0
        assertTrue(zero.isZero())
    }

    /**
     * Test method to verify behavior of [isZero] with non-zero value.
     */
    @Test
    fun `isZero with non-zero value`() {
        val nonZero = 5
        assertFalse(nonZero.isZero())
    }
}