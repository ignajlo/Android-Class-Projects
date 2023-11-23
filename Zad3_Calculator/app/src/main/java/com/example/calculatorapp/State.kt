package com.example.calculatorapp

sealed class State {
    object EMPTY : State()
    object SINGLE_OPERATOR : State()
    object DOUBLE_OPERATOR : State()
    object SINGLE_NUMBER : State()

    object SECOND_NUMBER : State()

//    object FIRST_DOT : State()
//
//    object SECOND_DOT : State()

}