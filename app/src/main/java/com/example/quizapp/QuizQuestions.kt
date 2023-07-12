package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.view.View

class QuizQuestions : AppCompatActivity(),View.OnClickListener {
    private var tvQuestion:TextView?= null
    private var ivImage:ImageView ?=null
    private var ProgressBar:ProgressBar?=null
    private var tvProgress:TextView?=null
    private var tvOption1:TextView?=null
    private var tvOption2:TextView?=null
    private var tvOption3:TextView?=null
    private var tvOption4:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        val questionsList=constants.getQuestions()
        Log.i("size of list is","${questionsList.size}")

        tvQuestion=findViewById(R.id.tv_question)
        ivImage=findViewById(R.id.iv_image)
        ProgressBar=findViewById(R.id.progressBar)
        tvProgress=findViewById(R.id.tv_progress)
        tvOption1=findViewById(R.id.option_one)
        tvOption2=findViewById(R.id.option_two)
        tvOption3=findViewById(R.id.option_three)
        tvOption4=findViewById(R.id.option_four)


        var currentindex = 1
        val question:Question=questionsList[currentindex-1]

        tvQuestion?.text=question.question
        ivImage?.setImageResource(question.image)
        ProgressBar?.progress=currentindex
        tvProgress?.text="${currentindex}/${ProgressBar?.max}"
        tvOption1?.text=question.optionOne
        tvOption2?.text=question.optionTwo
        tvOption3?.text=question.optionThree
        tvOption4?.text=question.optionFour

    }
}