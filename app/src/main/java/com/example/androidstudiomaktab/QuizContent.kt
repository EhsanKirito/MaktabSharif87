package com.example.androidstudiomaktab

object QuizContent {
    val questionList = arrayListOf<String>(
        "1+1=2",
        "2+2=5",
        "13*13=189",
        "16*16=256"
    )

    val isAnswered = arrayListOf<Boolean>(
        false,false,false,false
    )

    private val answerList = arrayListOf<Boolean>(
        true,
        false,
        false,
        true
    )

    var scoreList = booleanArrayOf(
        false,false,false,false
    )

    var cheatStatus = false
    var questionNumber = 0
    var currentQuestion = " "
    var currentAnswer = false
    var score = 0
    val lastQuestionNumber = questionList.size

    fun checkAnswer(answer:Boolean):Boolean{
        return if ( !cheatStatus && answer == currentAnswer){
            scoreList[questionNumber-1] = true
            true
        } else false
    }

    fun nextQuestion() {
        currentQuestion = questionList[questionNumber]
        currentAnswer = answerList[questionNumber]
        questionNumber++
        cheatStatus = false
    }

    fun prevQuestion() {
        questionNumber-=2
        currentQuestion = questionList[questionNumber]
        currentAnswer = answerList[questionNumber]
        questionNumber++
        cheatStatus = false
    }

    fun calcScore(){
        for (i in scoreList){
            if (i) score++
        }
    }

}