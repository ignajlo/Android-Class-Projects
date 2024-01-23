package com.example.calculatorapp

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.calculatorapp.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.Matchers.closeTo

@RunWith(AndroidJUnit4::class)
@LargeTest
class CalculatorFunctionalTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAdditionOfTwoPositiveIntegers() {
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withText("7.0")))
    }

    @Test
    fun testAdditionOfPositiveIntegerAndZero() {
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withText("3.0")))
    }

    @Test
    fun testAdditionOfTwoNegativeIntegers() {
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.seven)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withText("10.0")))
    }

    @Test
    fun testAdditionOfNegativeIntegerAndZero() {
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.four)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withText("4.0")))
    }

    @Test
    fun testAdditionOfFloatingPointAndInteger() {
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.six)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withText("8.5")))
    }

    @Test
    fun testAdditionOfTwoFloatingPointNumbers() {
        onView(withId(R.id.four)).perform(click())
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.one)).perform(click())
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.seven)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withText("6.0")))
    }

    @Test
    fun testAdditionOfFloatingPointAndZero() {
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.eight)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withText("0.8")))
    }

    @Test
    fun testSubtractionOfTwoPositiveIntegers() {
        onView(withId(R.id.seven)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withText("5.0")))
    }

    @Test
    fun testSubtractionFromZeroPositiveInteger() {
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withText("-5.0")))
    }

    @Test
    fun testSubtractionOfNegativeIntegerFromZero() {
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withText("3.0")))
    }

    //10 tests

    @Test
    fun testSubtractionOfTwoNegativeIntegers() {
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.seven)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withText("4.0")))
    }

    @Test
    fun testSubtractionOfFloatingPointFromInteger() {
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withText("4.8")))
    }

    @Test
    fun testSubtractionOfTwoFloatingPointNumbers() {
        onView(withId(R.id.four)).perform(click())
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.one)).perform(click())
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.seven)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(2.6, 0.01)))
    }

    @Test
    fun testSubtractionOfIntegerFromFloatingPoint() {
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.six)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withText("-3.5")))
    }

    @Test
    fun testMultiplicationOfTwoPositiveIntegers() {
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.multiply)).perform(click())
        onView(withId(R.id.four)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withText("12.0")))
    }

    @Test
    fun testMultiplicationOfPositiveIntegerByZero() {
        onView(withId(R.id.eight)).perform(click())
        onView(withId(R.id.multiply)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withText("0.0")))
    }

    @Test
    fun testMultiplicationOfTwoNegativeIntegers() {
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.multiply)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withText("15.0")))
    }

    @Test
    fun testMultiplicationOfFloatingPointByZero() {
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.six)).perform(click())
        onView(withId(R.id.multiply)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withText("0.0")))
    }

    @Test
    fun testMultiplicationOfTwoFloatingPointNumbers() {
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.multiply)).perform(click())
        onView(withId(R.id.four)).perform(click())
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(10.75, 0.001)))
    }

    @Test
    fun testMultiplicationOfIntegerByFloatingPoint() {
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.multiply)).perform(click())
        onView(withId(R.id.one)).perform(click())
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withText("6.0")))
    }

    //20 tests

    @Test
    fun testDivisionOfTwoPositiveIntegers() {
        onView(withId(R.id.six)).perform(click())
        onView(withId(R.id.divide)).perform(click())
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(3.0, 0.001)))
    }

    @Test
    fun testDivisionOfPositiveIntegerByOne() {
        onView(withId(R.id.eight)).perform(click())
        onView(withId(R.id.divide)).perform(click())
        onView(withId(R.id.one)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(8.0, 0.001)))
    }

    @Test
    fun testDivisionFromZeroPositiveInteger() {
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.divide)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(0.0, 0.001)))
    }

    @Test
    fun testDivisionOfTwoNegativeIntegers() {
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.seven)).perform(click())
        onView(withId(R.id.divide)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(2.333, 0.001)))
    }

    @Test
    fun testDivisionOfNegativeIntegerByOne() {
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.four)).perform(click())
        onView(withId(R.id.divide)).perform(click())
        onView(withId(R.id.one)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(4.0, 0.001)))
    }

    @Test
    fun testDivisionOfFloatingPointByZero() {
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.six)).perform(click())
        onView(withId(R.id.divide)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withText("0")))
    }

    @Test
    fun testDivisionOfTwoFloatingPointNumbers() {
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.divide)).perform(click())
        onView(withId(R.id.four)).perform(click())
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(0.555555, 0.001)))
    }

    @Test
    fun testDivisionOfIntegerByFloatingPoint() {
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.divide)).perform(click())
        onView(withId(R.id.one)).perform(click())
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(4.167, 0.001)))
    }

    @Test
    fun testPercentOperator() {
        onView(withId(R.id.eight)).perform(click())
        onView(withId(R.id.percent)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(0.083, 0.001)))
    }

    @Test
    fun testExponentiation() {
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.power)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(8.0, 0.001)))
    }

    //30 tests

    @Test
    fun testExponentiationOfFloatingPointToNegativePower() {
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.power)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.one)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(149011.6119384765625, 0.001)))
    }

    @Test
    fun testLogarithmFunctionOnInteger() {
        onView(withId(R.id.nine)).perform(click())
        onView(withId(R.id.lg)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(0.954, 0.001)))
    }

    @Test
    fun testLogarithmFunctionOnFloatingPoint() {
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.lg)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(0.3979, 0.001)))
    }

    @Test
    fun testAdditionOfMultipleNumbers() {
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.four)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(9.0, 0.001)))
    }

    @Test
    fun testSubtractionOfMultipleNumbers() {
        onView(withId(R.id.nine)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(4.0, 0.001)))
    }

    @Test
    fun testMultiplicationOfMultipleNumbers() {
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.multiply)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.multiply)).perform(click())
        onView(withId(R.id.four)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(24.0, 0.001)))
    }

    @Test
    fun testDivisionOfMultipleNumbers() {
        onView(withId(R.id.one)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.divide)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.divide)).perform(click())
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(10.0, 0.001)))
    }

    @Test
    fun testCombinationOfDifferentOperations() {
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.multiply)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.four)).perform(click())
        onView(withId(R.id.divide)).perform(click())
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(8.5, 0.001)))
    }

    @Test
    fun testInputtingFloatingPointNumberWithDot() {
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(2.5, 0.001)))
    }

    @Test
    fun testCombinationOfDifferentOperationsWithFloatingPointNumber() {
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.multiply)).perform(click())
        onView(withId(R.id.one)).perform(click())
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(8.25, 0.001)))
    }

    //40 tests

    @Test
    fun testEnteringLargeNumbers() {
        onView(withId(R.id.one)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(6000000.0, 0.001)))
    }

    @Test
    fun testEnteringVerySmallNumbers() {
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.one)).perform(click())
        onView(withId(R.id.multiply)).perform(click())
        onView(withId(R.id.one)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(0.000001, 0.001)))
    }

    @Test
    fun testMathExpressionWithPower() {
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.power)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(8.0, 0.001)))
    }

    @Test
    fun testLogarithmFunctionOnResultOfAnotherOperation() {
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.power)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.lg)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(0.90308998699194358564121668417348, 0.001)))
    }

    @Test
    fun testCombinationOfIntegerAndZero() {
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(5.0, 0.001)))
    }

    @Test
    fun testInvalidMathExpressionWithConsecutiveOperators() {
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withText("5.0")))
    }

    @Test
    fun testCombinationOfEqualityOperatorAndOtherOperations() {
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.multiply)).perform(click())
        onView(withId(R.id.four)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(20.0, 0.001)))
    }

    @Test
    fun testCombinationOfFloatingPointOperationAndEqualityOperator() {
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.multiply)).perform(click())
        onView(withId(R.id.one)).perform(click())
        onView(withId(R.id.dot)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(8.25, 0.001)))
    }

    @Test
    fun testCombinationOfEqualityOperatorAndMultiplication() {
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.multiply)).perform(click())
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(10.0, 0.001)))
    }

    @Test
    fun testCombinationOfDivisionAndEqualityOperator() {
        // Test combination of division and equality operator
        onView(withId(R.id.nine)).perform(click())
        onView(withId(R.id.divide)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.equals)).perform(click())
        onView(withId(R.id.textDisplay)).check(matches(withNumericText(3.0, 0.001)))
    }

    fun withNumericText(value: Double, tolerance: Double): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with numeric text: $value +/- $tolerance")
            }

            override fun matchesSafely(textView: TextView): Boolean {
                val actualValue = textView.text.toString().toDoubleOrNull()
                return actualValue != null && closeTo(value, tolerance).matches(actualValue)
            }
        }
    }

}
