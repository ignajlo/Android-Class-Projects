package com.example.calculatorapp

enum class TwoArgumentButton (val buttonId: Int, val text: String) {
    POWER(R.id.power, "^"),
    DIVIDE(R.id.divide, "/"),
    MULTIPLY(R.id.multiply, "*"),
    SUBTRACT(R.id.minus, "-"),
    ADD(R.id.plus, "+")

}