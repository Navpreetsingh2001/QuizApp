package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.myquizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var btn_start =binding.btnStart
        var et_name =binding.etName

       btn_start.setOnClickListener{

           if (et_name.text!!.isEmpty()){
               Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
           }
           else{
               val intent = Intent(this,QuizQuestionActivity::class.java)
               startActivity(intent)
               finish()
           }
       }



    }
}