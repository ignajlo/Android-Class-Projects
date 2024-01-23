package com.example.calculatorapp

import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*
import java.math.BigDecimal
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.math.RoundingMode

@RunWith(RobolectricTestRunner::class)
class MainActivityUnitTest {

    @Test
    fun testCalculatePercentage() {
        val mainActivity = MainActivity()
        val result = mainActivity.calculatePercentage("50")
        assertEquals(BigDecimal("0.5"), result)
    }

    @Test
    fun testCalculateLogarithm() {
        val mainActivity = MainActivity()
        val result = mainActivity.calculateLogarithm("100")
        assertEquals(0, result.compareTo(BigDecimal("2")))
    }

    @Test
    fun testEvaluateExpression() {
        val mainActivity = MainActivity()

        val result1 = mainActivity.evaluateExpression("2+2")
        assertEquals(0, result1.compareTo(BigDecimal("4")))

        val result2 = mainActivity.evaluateExpression("1/0")
        assertEquals(0, result2.compareTo(BigDecimal.ZERO))
    }

    //3

    @Test
    fun testAddingTwoPositiveIntegers() {
        val mainActivity = MainActivity()
        mainActivity.evaluateExpression("2+2")
        val result = mainActivity.evaluateExpression("2+2")
        assertEquals(0, result.compareTo(BigDecimal("4")))
    }

    @Test
    fun testAddingPositiveIntegerAndZero() {
        val mainActivity = MainActivity()
        mainActivity.evaluateExpression("5+0")
        val result = mainActivity.evaluateExpression("5+0")
        assertEquals(0, result.compareTo(BigDecimal("5")))
    }

    @Test
    fun testAddingTwoNegativeIntegers() {
        val mainActivity = MainActivity()
        mainActivity.evaluateExpression("-3+(-5)")
        val result = mainActivity.evaluateExpression("-3+(-5)")
        assertEquals(0, result.compareTo(BigDecimal("-8")))
    }

    @Test
    fun testAddingNegativeIntegerAndZero() {
        val mainActivity = MainActivity()
        mainActivity.evaluateExpression("-2+0")
        val result = mainActivity.evaluateExpression("-2+0")
        assertEquals(0, result.compareTo(BigDecimal("-2")))
    }

    @Test
    fun testAddingFloatingPointAndInteger() {
        val mainActivity = MainActivity()
        mainActivity.evaluateExpression("3.5+2")
        val result = mainActivity.evaluateExpression("3.5+2")
        assertEquals(0, result.compareTo(BigDecimal("5.5")))
    }

    @Test
    fun testAddingTwoFloatingPointNumbers() {
        val mainActivity = MainActivity()
        mainActivity.evaluateExpression("1.5+2.5")
        val result = mainActivity.evaluateExpression("1.5+2.5")
        assertEquals(0, result.compareTo(BigDecimal("4")))
    }

    @Test
    fun testAddingFloatingPointAndZero() {
        val mainActivity = MainActivity()
        mainActivity.evaluateExpression("3.14+0")
        val result = mainActivity.evaluateExpression("3.14+0")
        assertEquals(0, result.compareTo(BigDecimal("3.14")))
    }

    @Test
    fun testSubtractingTwoPositiveIntegers() {
        val mainActivity = MainActivity()
        mainActivity.evaluateExpression("8-3")
        val result = mainActivity.evaluateExpression("8-3")
        assertEquals(0, result.compareTo(BigDecimal("5")))
    }

    @Test
    fun testSubtractingFromZeroPositiveInteger() {
        val mainActivity = MainActivity()
        mainActivity.evaluateExpression("0-7")
        val result = mainActivity.evaluateExpression("0-7")
        assertEquals(0, result.compareTo(BigDecimal("-7")))
    }

    @Test
    fun testSubtractingNegativeIntegerFromZero() {
        val mainActivity = MainActivity()
        mainActivity.evaluateExpression("0-(-5)")
        val result = mainActivity.evaluateExpression("0-(-5)")
        assertEquals(0, result.compareTo(BigDecimal("5")))
    }

    //13

    @Test
    fun testSubtractingTwoNegativeIntegers() {
        val mainActivity = MainActivity()
        mainActivity.evaluateExpression("-8-(-3)")
        val result = mainActivity.evaluateExpression("-8-(-3)")
        assertEquals(0, result.compareTo(BigDecimal("-5")))
    }

    @Test
    fun testSubtractingFloatingPointFromInteger() {
        val mainActivity = MainActivity()
        mainActivity.evaluateExpression("5-2.5")
        val result = mainActivity.evaluateExpression("5-2.5")
        assertEquals(0, result.compareTo(BigDecimal("2.5")))
    }

    @Test
    fun testSubtractingTwoFloatingPointNumbers() {
        val mainActivity = MainActivity()
        mainActivity.evaluateExpression("8.5-3.5")
        val result = mainActivity.evaluateExpression("8.5-3.5")
        assertEquals(0, result.compareTo(BigDecimal("5")))
    }

    @Test
    fun testSubtractingIntegerFromFloatingPoint() {
        val mainActivity = MainActivity()
        mainActivity.evaluateExpression("8.7-3")
        val result = mainActivity.evaluateExpression("8.7-3")
        assertEquals(0, result.setScale(1, RoundingMode.HALF_UP).compareTo(BigDecimal("5.7")))
    }

    @Test
    fun testMultiplyingTwoPositiveIntegers() {
        val mainActivity = MainActivity()
        mainActivity.evaluateExpression("4*3")
        val result = mainActivity.evaluateExpression("4*3")
        assertEquals(0, result.compareTo(BigDecimal("12")))
    }

    @Test
    fun testMultiplyingPositiveIntegerByZero() {
        val mainActivity = MainActivity()
        mainActivity.evaluateExpression("7*0")
        val result = mainActivity.evaluateExpression("7*0")
        assertEquals(0, result.compareTo(BigDecimal.ZERO))
    }

    @Test
    fun testMultiplyingTwoNegativeIntegers() {
        val mainActivity = MainActivity()
        mainActivity.evaluateExpression("-4*(-3)")
        val result = mainActivity.evaluateExpression("-4*(-3)")
        assertEquals(0, result.compareTo(BigDecimal("12")))
    }

    @Test
    fun testMultiplyingFloatingPointByZero() {
        val mainActivity = MainActivity()
        mainActivity.evaluateExpression("2.5*0")
        val result = mainActivity.evaluateExpression("2.5*0")
        assertEquals(0, result.compareTo(BigDecimal.ZERO))
    }

    @Test
    fun testMultiplyingTwoFloatingPointNumbers() {
        val mainActivity = MainActivity()
        mainActivity.evaluateExpression("1.5*2.5")
        val result = mainActivity.evaluateExpression("1.5*2.5")
        assertEquals(0, result.compareTo(BigDecimal("3.75")))
    }

    @Test
    fun testMultiplyingIntegerByFloatingPoint() {
        val mainActivity = MainActivity()
        mainActivity.evaluateExpression("4*2.5")
        val result = mainActivity.evaluateExpression("4*2.5")
        assertEquals(0, result.compareTo(BigDecimal("10")))
    }



}
