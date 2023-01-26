package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.myquizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvName.text=intent.getStringExtra(Constants.USER_NAME)

        val totalQuestion =intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val correctAnswers =intent.getIntExtra(Constants.CORRECT_ANSWERS,0)

        binding.tvScore.text ="Your Score is $correctAnswers out of $totalQuestion"

        binding.btnFinish.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }


    }
}