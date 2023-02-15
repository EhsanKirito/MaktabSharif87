package com.example.simplecalculator

class Calculator {
    var numberChange = 1
    var firstNumber = 0.0
    var secondNumber = 0.0
    var result = 0.0
    var func:Func = Func.Sum
    var dotInt = 0.1
    var dotState1 = false
    var dotState2 = false
}

enum class Func(var funcs:String){
    Sum("+"), Minus("-"), Multi("*"), Div("/")
}

fun Calculator.Sum() {
    result = firstNumber + secondNumber
}

fun Calculator.minus() {
    result = firstNumber - secondNumber
}

fun Calculator.Mult() {
    result = firstNumber * secondNumber
}

fun Calculator.Div() {
    if (secondNumber != 0.0) {
        result = firstNumber / secondNumber
    } else numberChange = 6
}

fun Calculator.Equal(): Double {
    when(func){
        Func.Sum -> this.Sum()
        Func.Minus -> this.minus()
        Func.Multi -> this.Mult()
        Func.Div -> this.Div()
    }
    return result
}

fun Calculator.setNumberOnButton(pressedButton: Int) {
    if (numberChange == 1) {
        firstNumber = firstNumber * 10 + pressedButton
    } else if(numberChange == 2) {
        firstNumber += pressedButton * dotInt
        dotInt /= 10
    }else if(numberChange == 3) {
        secondNumber = secondNumber * 10 + pressedButton
    }else if(numberChange == 4) {
        secondNumber += pressedButton * dotInt
        dotInt /= 10
    } else {
        this.reset()
        this.setNumberOnButton(pressedButton)
    }
}

fun Calculator.reset() {
    numberChange = 1
    firstNumber = 0.0
    secondNumber = 0.0
    result = 0.0
    func = Func.Sum
    dotState1 = false
    dotState2 = false
}