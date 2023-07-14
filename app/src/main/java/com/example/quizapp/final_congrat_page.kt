package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class final_congrat_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_congrat_page)
        val TV_username:TextView=findViewById(R.id.tv_username)
        val TV_score:TextView=findViewById(R.id.tv_score)
        val finishBtn:Button=findViewById(R.id.finishBtn)

        TV_username.text = intent.getStringExtra(constants.USER_NAME)
        val total = intent.getIntExtra(constants.TOTAL_QUESTIONS,0)
        val correct = intent.getIntExtra(constants.CORRECT_ANSWER,0)

        TV_score.text="your score id ${correct} from ${total}"

        finishBtn.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}