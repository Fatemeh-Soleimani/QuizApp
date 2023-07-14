package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlin.reflect.typeOf

class QuizQuestions : AppCompatActivity(),View.OnClickListener {

    private var currentindex:Int = 1
    private var questionsList:ArrayList<Question>?= null
    private var mSelectedOption:Int = 0

    private var mUserName:String?=null
    private var mCorrectAnswer:Int=0
//    private var mTotalQuestions:Int=questionsList!!.size

    private var tvQuestion:TextView?= null
    private var ivImage:ImageView ?=null
    private var ProgressBar:ProgressBar?=null
    private var tvProgress:TextView?=null
    private var tvOption1:TextView?=null
    private var tvOption2:TextView?=null
    private var tvOption3:TextView?=null
    private var tvOption4:TextView?=null
    private var submitBtn:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName= intent.getStringExtra(constants.USER_NAME)


        tvQuestion=findViewById(R.id.tv_question)
        ivImage=findViewById(R.id.iv_image)
        ProgressBar=findViewById(R.id.progressBar)
        tvProgress=findViewById(R.id.tv_progress)
        tvOption1=findViewById(R.id.option_one)
        tvOption2=findViewById(R.id.option_two)
        tvOption3=findViewById(R.id.option_three)
        tvOption4=findViewById(R.id.option_four)
        submitBtn=findViewById(R.id.submitButton)

        questionsList=constants.getQuestions()


        tvOption1?.setOnClickListener(this)
        tvOption2?.setOnClickListener(this)
        tvOption3?.setOnClickListener(this)
        tvOption4?.setOnClickListener(this)
        submitBtn?.setOnClickListener(this)




        setQuestion()

    }

    private fun setQuestion() {
        defaultOptionView()
        val question: Question = questionsList!![currentindex - 1]

        tvQuestion?.text = question.question
        ivImage?.setImageResource(question.image)
        ProgressBar?.progress = currentindex
        tvProgress?.text = "${currentindex}/${ProgressBar?.max}"
        tvOption1?.text = question.optionOne
        tvOption2?.text = question.optionTwo
        tvOption3?.text = question.optionThree
        tvOption4?.text = question.optionFour

        if(currentindex==questionsList!!.size){
            submitBtn?.text=="FINISH"
        }else{
            submitBtn?.text=="SUBMIT"
        }


    }

    private fun defaultOptionView(){

        val options=ArrayList<TextView>()
        tvOption1?.let { options.add(0,it) }
        tvOption2?.let { options.add(1,it) }
        tvOption3?.let { options.add(2,it) }
        tvOption4?.let { options.add(3,it) }

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface= Typeface.DEFAULT
            option.background=ContextCompat.getDrawable(this,R.drawable.default_option_bg)
        }
    }

    private fun selectedOptionView(tv:TextView,selctedOptionNum:Int){
        defaultOptionView()
        mSelectedOption=selctedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background=ContextCompat.getDrawable(this,R.drawable.selected_border_bg)
    }
    override fun onClick(view: View?) {

        when(view?.id){
            R.id.option_one->{
                tvOption1?.let {
                    selectedOptionView(it,1) }
            }
            R.id.option_two->{
                tvOption2?.let {
                    selectedOptionView(it,2) }
            }
            R.id.option_three->{
                tvOption3?.let {
                    selectedOptionView(it,3) }
            }
            R.id.option_four->{
                tvOption4?.let {
                    selectedOptionView(it,4) }
            }
            R.id.submitButton->{
                if(mSelectedOption==0) {
                    submitBtn?.text= "SUBMIT"
                    currentindex++
                    when {
                        currentindex <= questionsList!!.size -> {
                            setQuestion()
                        }
                        else ->{
                            if(mCorrectAnswer>=5) {
                                val intent = Intent(this, final_congrat_page::class.java)
                                intent.putExtra(constants.USER_NAME, mUserName)
                                intent.putExtra(constants.TOTAL_QUESTIONS, questionsList?.size)
                                intent.putExtra(constants.CORRECT_ANSWER, mCorrectAnswer)
                                startActivity(intent)
                                finish()
                            }
                            else{
                                val intent = Intent(this, fail_page::class.java)
                                intent.putExtra(constants.USER_NAME, mUserName)
                                intent.putExtra(constants.TOTAL_QUESTIONS, questionsList?.size)
                                intent.putExtra(constants.CORRECT_ANSWER, mCorrectAnswer)
                                startActivity(intent)
                                finish()
                            }
                        }
                    }
                }
                else{
                    val question=questionsList?.get(currentindex-1)
                    if(question!!.correctAnswer!=mSelectedOption){
                        answerView(mSelectedOption,R.drawable.wrong_option_bg)
                    }else{
                        mCorrectAnswer++
                    }

                    if(currentindex==questionsList!!.size){
                        submitBtn?.text= "FINISH"
                    }
                    else{
                        submitBtn?.text="Go To Next Questions"
                    }
                    answerView(question.correctAnswer,R.drawable.correct_option_bg)
                    mSelectedOption=0

                }
            }
        }

    }
    private fun answerView(answer:Int,drawableView:Int){
        when(answer){
            1->{tvOption1?.background=ContextCompat.getDrawable(this,drawableView)}
            2->{tvOption2?.background=ContextCompat.getDrawable(this,drawableView)}
            3->{tvOption3?.background=ContextCompat.getDrawable(this,drawableView)}
            4->{tvOption4?.background=ContextCompat.getDrawable(this,drawableView)}

        }
    }
}