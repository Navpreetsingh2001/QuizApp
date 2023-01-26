package com.example.myquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.myquizapp.databinding.ActivityMainBinding
import com.example.myquizapp.databinding.ActivityQuizQuestionBinding
import java.lang.reflect.Type

class QuizQuestionActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding: ActivityQuizQuestionBinding
    private var  mCurrentPosition :Int=1
    private var mQuestionList:ArrayList<Question>?= null
    private var mSelectedOptionPosition :Int=0
    private var mUsername :String? =null
    private var mCorrectAnswer:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mUsername= intent.getStringExtra(Constants.USER_NAME)

        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)

        mQuestionList = Constants.getQuestion()
        setQuestion()

    }

    private fun setQuestion() {
        defaultView()

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

    private fun selectedOptiononView(tv:TextView,selectedOptionNum:Int){
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
                    selectedOptiononView(it,1)
                }
            }
            R.id.tv_option_two ->{
                binding.tvOptionTwo.let {
                    selectedOptiononView(it,2)
                }
            }
            R.id.tv_option_three ->{
                binding.tvOptionThree.let {
                    selectedOptiononView(it,3)
                }
            }
            R.id.tv_option_four ->{
                binding.tvOptionFour.let {
                    selectedOptiononView(it,4)
                }
            }
            R.id.btnSubmit->{
                if (mSelectedOptionPosition==0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionList!!.size ->{
                            setQuestion()
                        }
                        else ->{
                            val intent =Intent(this,ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME,mUsername)
                            intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswer)
                            intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }

                }
                else{
                    val question =mQuestionList!!.get(mCurrentPosition-1)
                    if (question!!.correctAnswer!=mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition,R.drawable.wrong_option_bg)
                    }
                    else{
                        mCorrectAnswer++
                    }
                    answerView(question.correctAnswer,R.drawable.correct_option_bg)

                    if (mCurrentPosition ==mQuestionList!!.size){
                        binding.btnSubmit.text="FINISH"
                    }
                    else{
                        binding.btnSubmit.text ="GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition =0
                }
            }

        }
    }
    private fun answerView(answer:Int,drawableView:Int){
        when(answer){
            1->{
                binding.tvOptionOne.background =ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            2->{
                binding.tvOptionTwo.background =ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            3->{
                binding.tvOptionThree.background =ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            4->{
                binding.tvOptionFour.background =ContextCompat.getDrawable(
                    this,drawableView
                )
            }
        }
    }
}