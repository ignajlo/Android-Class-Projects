package com.example.calculatorapp
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.exp
import kotlin.math.ln
import kotlin.math.pow
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var textDisplay: TextView
    private var firstDot = false;
    private var secondDot = false;
    private var currentState: State = State.EMPTY
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textDisplay = findViewById(R.id.textDisplay)
        prepareButtons()

    }

    private fun prepareButtons()
    {
        for(button in NumberButton.values())
        {
            setUpNumberButton(button)
        }
        for(button in OneArgumentButton.values())
        {
            setUpOneArgumentButton(button)
        }
        for(button in TwoArgumentButton.values())
        {
            setUpTwoArgumentButton(button)
        }
        setUpEquals()
        setUpClear()
        setUpDot()
    }

    private fun setUpNumberButton(buttonSetup: NumberButton)
    {
        val button: Button = findViewById(buttonSetup.buttonId)
        button.setOnClickListener{
            val newText = "${textDisplay.text}${buttonSetup.text}"
        when(currentState){
            State.EMPTY, State.SINGLE_NUMBER/*, State.FIRST_DOT*/ -> {
                textDisplay.text = newText
                currentState = State.SINGLE_NUMBER
            }
            State.DOUBLE_OPERATOR, State.SECOND_NUMBER/*, State.SECOND_DOT*/ -> {
                textDisplay.text = newText
                currentState = State.SECOND_NUMBER
            }
            else -> {}
        }
        }
    }

    private fun setUpClear() {
        val clearButton: Button = findViewById(R.id.clear)
        clearButton.setOnClickListener {
            textDisplay.text = "" // Clear the text display
            currentState = State.EMPTY
            firstDot = false;
            secondDot = false;
        }
    }

    private fun setUpOneArgumentButton(buttonSetup: OneArgumentButton) {
        val button: Button = findViewById(buttonSetup.buttonId)
        button.setOnClickListener {
            val newText = "${textDisplay.text}${buttonSetup.text}"
            when (buttonSetup) {
                OneArgumentButton.PERCENTAGE -> {
                    handlePercentage()
                }
                OneArgumentButton.LG -> {
                    handleLogarithm()
                }
                else -> {}
            }
        }
    }

    private fun handlePercentage() {
        val expressionText = textDisplay.text.toString()
        val result = calculatePercentage(expressionText)
        textDisplay.text = result.toString()
        currentState = State.SINGLE_NUMBER
        firstDot = true
        secondDot = false
    }
    fun calculatePercentage(value: String): BigDecimal {
        val expression = "($value) / 100"
        return evaluateExpression(expression)
    }

    private fun handleLogarithm() {
        val expressionText = textDisplay.text.toString()
        val result = calculateLogarithm(expressionText)
        textDisplay.text = result.toString()
        currentState = State.SINGLE_NUMBER
        firstDot = true
        secondDot = false
    }
    fun calculateLogarithm(value: String): BigDecimal {
        val expression = "log10($value)"
        return evaluateExpression(expression)
    }


    private fun setUpTwoArgumentButton(buttonSetup: TwoArgumentButton)
    {
        val button: Button = findViewById(buttonSetup.buttonId)
        button.setOnClickListener{
            val newText = "${textDisplay.text} ${buttonSetup.text} "
            when(currentState){
                State.SINGLE_NUMBER -> {
                    textDisplay.text = newText
                    currentState = State.DOUBLE_OPERATOR
                }
                else -> {}
            }
        }
    }

    private fun setUpDot(){
        val button: Button = findViewById(R.id.dot)
        button.setOnClickListener {
            val newText = "${textDisplay.text}${button.text}"
            when(currentState){
                State.SINGLE_NUMBER, State.EMPTY -> {
                    if(!firstDot) {
                        textDisplay.text = newText
                        //currentState = State.FIRST_DOT
                        firstDot=true
                    }
                }
                State.SECOND_NUMBER, State.DOUBLE_OPERATOR -> {
                    if(!secondDot) {
                        textDisplay.text = newText
                        //currentState = State.SECOND_DOT
                        secondDot=true
                    }
                }
                else -> {}
            }
        }

    }

    private fun setUpEquals() {
        val equalsButton: Button = findViewById(R.id.equals)
        equalsButton.setOnClickListener {
            when(currentState) {
                State.SECOND_NUMBER -> {
                    val expressionText = textDisplay.text.toString()
                    val result = evaluateExpression(expressionText)
                    textDisplay.text = result.toString()
                    currentState = State.SINGLE_NUMBER
                    firstDot = true
                    secondDot = false
                }

                else -> {}
            }

        }
    }

    private fun evaluateExpression(expression: String): BigDecimal {
        return try {
            val result = ExpressionBuilder(expression)
                .variables("x", "y")
                .build()
                .evaluate()

            BigDecimal(result, MathContext.DECIMAL64)
            val resultString = result.toString()
            if (!resultString.contains('.')) {
                BigDecimal("$resultString.0", MathContext.DECIMAL64)
            } else {
                BigDecimal(resultString, MathContext.DECIMAL64)
            }
        } catch (e: ArithmeticException) {

            BigDecimal.ZERO
        } catch (e: IllegalArgumentException) {

            BigDecimal.ZERO
        }
    }





}
