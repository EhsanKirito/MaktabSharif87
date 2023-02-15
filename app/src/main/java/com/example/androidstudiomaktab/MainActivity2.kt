package com.example.androidstudiomaktab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*
import kotlin.time.Duration.Companion.seconds


class MainActivity2 : AppCompatActivity() {

    val quiz = QuizContent
    val clickList = booleanArrayOf(false, false, false, true, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        fun answerLock() {
            btnTrue.isEnabled = false
            clickList[0] = false
            btnFalse.isEnabled = false
            clickList[1] = false
        }
        fun answerUnlock() {
            btnTrue.isEnabled = true
            clickList[0] = true
            btnFalse.isEnabled = true
            clickList[1] = true
        }
        fun scoreShowFalse() {
            quiz.calcScore()
            Toast.makeText(this, "It is Incorrect and Your score is ${quiz.score}", Toast.LENGTH_LONG).show()
            quiz.score = 0
        }

        fun scoreShowTrue() {
            quiz.calcScore()
            Toast.makeText(this, "It is Correct and Your score is ${quiz.score}", Toast.LENGTH_LONG).show()
            quiz.score = 0
        }

        fun isAnswered(questionNumber:Int){
            quiz.isAnswered[questionNumber-1] = true
        }

        fun lockIfAnswered(questionNumber: Int){
            if (quiz.isAnswered[questionNumber-1] && quiz.questionNumber>=1) {
                btnTrue.isEnabled = false
                btnFalse.isEnabled = false
                btnCheat.isEnabled = false
            }

        }


        btnNext.setOnClickListener {
            quiz.nextQuestion()
            txtQuiz.text = quiz.currentQuestion
            answerUnlock()
            if (quiz.questionNumber > 1) {
                btnPrev.isEnabled = true
                clickList[4] = true
            }
            btnCheat.isEnabled = true
            clickList[2] = true
            if (quiz.questionNumber == quiz.lastQuestionNumber) {
                btnNext.isEnabled = false
                clickList[3] = false
            }
            lockIfAnswered(quiz.questionNumber)
        }

        btnPrev.setOnClickListener {
            btnCheat.isEnabled = true
            clickList[2] = true
            quiz.prevQuestion()
            txtQuiz.text = quiz.currentQuestion
            answerUnlock()
            btnNext.isEnabled = true
            clickList[3] = true
            if (quiz.questionNumber == 1) {
                btnPrev.isEnabled = false
                clickList[4] = false
            }
            lockIfAnswered(quiz.questionNumber)
        }

        btnTrue.setOnClickListener {
            btnCheat.isEnabled = false
            clickList[2] = false
            if (quiz.checkAnswer(true)) {
                scoreShowTrue()
            } else if (quiz.cheatStatus){
                Toast.makeText(this, "Cheating is wrong", Toast.LENGTH_LONG).show()
            } else scoreShowFalse()
            answerLock()
            isAnswered(quiz.questionNumber)
        }

        btnFalse.setOnClickListener {
            btnCheat.isEnabled = false
            clickList[2] = false
            if (quiz.checkAnswer(false)) {
                scoreShowTrue()
            }else if (quiz.cheatStatus){
                Toast.makeText(this, "Cheating is wrong", Toast.LENGTH_LONG).show()
            } else scoreShowFalse()
            answerLock()
            isAnswered(quiz.questionNumber)
        }

        btnCheat.setOnClickListener {
            btnCheat.isEnabled = false
            clickList[2] = false
            val cheatData = Intent(this, CheatActivity::class.java)
            cheatData.putExtra("answer", quiz.currentAnswer)
            startActivity(cheatData)

        }

        btnPrev.isEnabled = false
        clickList[4] = false
        btnCheat.isEnabled = false
        clickList[2] = false
        answerLock()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("ANSWER", quiz.currentAnswer)
        outState.putString("QUESTION", quiz.currentQuestion)
        outState.putInt("NUMBER", quiz.questionNumber)
        outState.putBooleanArray("CLICK",clickList)
        outState.putBooleanArray("SCORE",quiz.scoreList)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        quiz.currentAnswer = savedInstanceState.getBoolean("ANSWER")
        quiz.questionNumber = savedInstanceState.getInt("NUMBER")
        quiz.currentQuestion = savedInstanceState.getString("QUESTION").toString()
        txtQuiz.text = quiz.currentQuestion
        btnTrue.isEnabled = savedInstanceState.getBooleanArray("CLICK")?.get(0) ?: false
        btnFalse.isEnabled = savedInstanceState.getBooleanArray("CLICK")?.get(1) ?: false
        btnFalse.isEnabled = savedInstanceState.getBooleanArray("CLICK")?.get(2) ?: false
        btnFalse.isEnabled = savedInstanceState.getBooleanArray("CLICK")?.get(3) ?: false
        btnFalse.isEnabled = savedInstanceState.getBooleanArray("CLICK")?.get(4) ?: false
        quiz.scoreList = savedInstanceState.getBooleanArray("SCORE")!!
    }

}