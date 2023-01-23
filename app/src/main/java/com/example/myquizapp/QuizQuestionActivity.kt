package com.example.myquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myquizapp.databinding.ActivityMainBinding
import com.example.myquizapp.databinding.ActivityQuizQuestionBinding

class QuizQuestionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val questionList =Constants.getQuestion()
        Log.i("Question size is","${questionList.size}")

        for(i in questionList){
            Log.e("Questions",i.question)
        }
        var currentPosition =1
        val question : Question =questionList[currentPosition-1]

        binding.tvProgressBar.progress =currentPosition
        binding.tvProgress.text ="$currentPosition/${binding.tvProgressBar.max}"

        binding.tvQuestion.text =question.question
        binding.tvImage.setImageResource(question.image)
        binding.tvOptionOne.text =question.optionOne
        binding.tvOptionTwo.text =question.optionTwo
        binding.tvOptionThree.text =question.optionThree
        binding.tvOptionFour.text =question.optionFour


    }
}