package com.example.simplecalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val calc1 = findViewById<TextView>(R.id.txt1)
        val calc2 = findViewById<TextView>(R.id.txt2)
        val calc3 = findViewById<TextView>(R.id.txt3)
        val calc4 = findViewById<TextView>(R.id.txt4)
        val calc5 = findViewById<TextView>(R.id.txt5)

        calc1.text = intent.getStringExtra("HIS1")
        calc2.text = intent.getStringExtra("HIS2")
        calc3.text = intent.getStringExtra("HIS3")
        calc4.text = intent.getStringExtra("HIS4")
        calc5.text = intent.getStringExtra("HIS5")

        val answerList = intent.getDoubleArrayExtra("ANS")

        fun retrieveCalc(txt:TextView, ans:Double){
            if (txt.text!= null){
                txt.isClickable = true
                txt.setOnClickListener {
                    startActivity(Intent(this, MainActivity::class.java).putExtra("HIS-ANS",ans))
                }
            }
        }

        retrieveCalc(calc1, answerList?.get(0) as Double)
        retrieveCalc(calc2, answerList?.get(1) as Double)
        retrieveCalc(calc3, answerList?.get(2) as Double)
        retrieveCalc(calc4, answerList?.get(3) as Double)
        retrieveCalc(calc5, answerList?.get(4) as Double)
    }
}