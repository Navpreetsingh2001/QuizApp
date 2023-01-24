package com.example.myquizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.myquizapp.databinding.ActivityMainBinding
import com.example.myquizapp.databinding.ActivityQuizQuestionBinding
import java.lang.reflect.Type

class QuizQuestionActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding: ActivityQuizQuestionBinding
    private var  mCurrentPosition :Int=1
    private var mQuestionList:ArrayList<Question>?= null
    private var mSelectedOptionPosition :Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)

        mQuestionList = Constants.getQuestion()
        setQuestion()



    }

    private fun setQuestion() {


        val question: Question = mQuestionList!![mCurrentPosition - 1]

        binding.tvProgressBar.progress = mCurrentPosition
        binding.tvProgress.text = "$mCurrentPosition/${binding.tvProgressBar.max}"

        binding.tvQuestion.text = question.question
        binding.tvImage.setImageResource(question.image)
        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour

        if(mCurrentPosition==mQuestionList!!.size){
            binding.btnSubmit.text="FINISH"
        }
        else{
            binding.btnSubmit.text="SUBMIT"
        }
    }

   private fun defaultView(){
       val options =ArrayList<TextView>()
       binding.tvOptionOne.let{
           options.add(0,it)
       }
       binding.tvOptionTwo.let{
           options.add(1,it)
       }
       binding.tvOptionThree.let{
           options.add(2,it)
       }
       binding.tvOptionFour.let{
           options.add(3,it)
       }

       for(option in options){
           option.setTextColor(Color.parseColor("#7A8089"))
           //option.setTextColor(Color.parseColor("#FF0000"))
           option.typeface =Typeface.DEFAULT
           option.background =ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)
       }

    }

    private fun selectedOptiononview(tv:TextView,selectedOptionNum:Int){
        defaultView()

        mSelectedOptionPosition =selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface ,Typeface.BOLD)
        tv.background =ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)
    }
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tv_option_one ->{
                binding.tvOptionOne.let {
                    selectedOptiononview(it,1)
                }
            }
            R.id.tv_option_two ->{
                binding.tvOptionTwo.let {
                    selectedOptiononview(it,2)
                }
            }
            R.id.tv_option_three ->{
                binding.tvOptionThree.let {
                    selectedOptiononview(it,3)
                }
            }
            R.id.tv_option_four ->{
                binding.tvOptionFour.let {
                    selectedOptiononview(it,4)
                }
            }
            R.id.btnSubmit->{
                //
            }

        }
    }
}