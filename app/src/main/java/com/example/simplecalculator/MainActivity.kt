package com.example.simplecalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    val calculator = Calculator()
    var dispText = " "
    var calcHistoty = mutableListOf<String>("","","","","")
    var ansHistory = mutableListOf<Double>(0.0,0.0,0.0,0.0,0.0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnHistory = findViewById<Button>(R.id.History)
        val btn0 = findViewById<Button>(R.id.zero)
        val btn1 = findViewById<Button>(R.id.one)
        val btn2 = findViewById<Button>(R.id.two)
        val btn3 = findViewById<Button>(R.id.three)
        val btn4 = findViewById<Button>(R.id.four)
        val btn5 = findViewById<Button>(R.id.five)
        val btn6 = findViewById<Button>(R.id.six)
        val btn7 = findViewById<Button>(R.id.seven)
        val btn8 = findViewById<Button>(R.id.eight)
        val btn9 = findViewById<Button>(R.id.nine)
        val btnPlus = findViewById<Button>(R.id.plus)
        val btnMinus = findViewById<Button>(R.id.minus)
        val btnMultiply = findViewById<Button>(R.id.multiply)
        val btnDivision = findViewById<Button>(R.id.division)
        val btnEquals = findViewById<Button>(R.id.equals)
        val btnDot = findViewById<Button>(R.id.dot)
        val btnDelete = findViewById<ImageButton>(R.id.delete)
        val display = findViewById<TextView>(R.id.displayer)
        val numberList = listOf<Button>(btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9)

        val historyResult = intent.getDoubleExtra("HIS-ANS",0.0)

        fun displayFun(){
            if (calculator.numberChange == 1) {
                dispText = calculator.firstNumber.toInt().toString()
            }else if (calculator.numberChange == 2) {
                dispText = calculator.firstNumber.toString()
            }
            else if (calculator.numberChange == 3){
                if (calculator.dotState1) dispText = "${calculator.firstNumber} ${calculator.func.funcs} ${calculator.secondNumber.toInt()}"
                else if (!calculator.dotState1) dispText = "${calculator.firstNumber.toInt()} ${calculator.func.funcs} ${calculator.secondNumber.toInt()}"
            } else if (calculator.numberChange == 4){
                dispText = "${calculator.firstNumber} ${calculator.func.funcs} ${calculator.secondNumber}"
            }
            else if (calculator.numberChange == 5) {
                if (calculator.dotState2) dispText = "${calculator.firstNumber} ${calculator.func.funcs} ${calculator.secondNumber} = ${calculator.result}"
                else if (!calculator.dotState2) dispText = "${calculator.firstNumber.toInt()} ${calculator.func.funcs} ${calculator.secondNumber.toInt()} = ${calculator.result.toInt()}"
            } else {
                dispText = "Can't Divide By Zero"
            }
            display.text = dispText
        }

        if (historyResult != 0.0){
            calculator.firstNumber = historyResult
            displayFun()
        }

        fun setNumberListener(n:Int, btn:Button){
            btn.setOnClickListener {
            calculator.setNumberOnButton(n)
                displayFun()
            }
        }

        for (btn in numberList){
            setNumberListener(numberList.indexOf(btn), btn)
        }

        btnDelete.setOnClickListener {
            ansHistory.add(0,calculator.result)
            calculator.reset()
            calcHistoty.add(0,display.text.toString())
            display.text = " "
        }

        btnHistory.setOnClickListener {
            val ansHistList = doubleArrayOf(ansHistory[0], ansHistory[1],ansHistory[2],ansHistory[3],ansHistory[4])
            startActivity(Intent(this, HistoryActivity::class.java).apply {
                this.putExtra("HIS1",calcHistoty[0])
                this.putExtra("HIS2",calcHistoty[1])
                this.putExtra("HIS3",calcHistoty[2])
                this.putExtra("HIS4",calcHistoty[3])
                this.putExtra("HIS5",calcHistoty[4])

                this.putExtra("ANS", ansHistList)
            })
        }

        btnDot.setOnClickListener {
            calculator.dotInt = 0.1
            if (calculator.numberChange == 1) {
                calculator.numberChange = 2
                calculator.dotState1 = true
            }
            else if (calculator.numberChange == 3) {
                calculator.numberChange = 4
                calculator.dotState2 = true
            }

            displayFun()
        }

        btnPlus.setOnClickListener {
            calculator.numberChange = 3
            calculator.func = Func.Sum
            displayFun()
        }
        btnMinus.setOnClickListener {
            calculator.numberChange = 3
            calculator.func = Func.Minus
            displayFun()
        }
        btnMultiply.setOnClickListener {
            calculator.numberChange = 3
            calculator.func = Func.Multi
            displayFun()
        }
        btnDivision.setOnClickListener {
            calculator.numberChange = 3
            calculator.func = Func.Div
            displayFun()
        }

        btnEquals.setOnClickListener {
            calculator.numberChange = 5
            calculator.Equal()
            displayFun()
        }

    }
}