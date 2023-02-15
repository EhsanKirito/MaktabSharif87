package com.example.androidstudiomaktab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cheat.*

class CheatActivity : AppCompatActivity() {
    val quiz = QuizContent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        val answer = intent.getBooleanExtra("answer", false).toString()

        btnShow.setOnClickListener {
            txtCheat.text = answer
            quiz.cheatStatus = true
        }

    }

}