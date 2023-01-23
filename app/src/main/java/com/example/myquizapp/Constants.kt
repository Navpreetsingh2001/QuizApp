package com.example.myquizapp

object Constants {
    fun getQuestion():ArrayList<Question>{
        val questionList =ArrayList<Question>()
            //1
            val que1 =Question(
                1 , "What country does this flag belong to?",R.drawable.ic_flag_of_argentina,
                "Pakistan","Nepal" ,"Brazil" ,"Argentina",4
            )
            questionList.add(que1)
            val que2 =Question(
                2 , "What country does this flag belong to?",R.drawable.ic_flag_of_australia,
                "Pakistan","Australia" ,"kuwait" ,"Germany",2
            )
            questionList.add(que2)
            val que3 =Question(
                3 , "What country does this flag belong to?",R.drawable.ic_flag_of_belgium,
                "Belgium","Nepal" ,"Brazil" ,"Argentina",1
            )
            questionList.add(que3)
            val que4 =Question(
                4 , "What country does this flag belong to?",R.drawable.ic_flag_of_brazil,
                "Pakistan","Nepal" ,"Brazil" ,"Argentina",3
            )
            questionList.add(que4)
            val que5 =Question(
                5 , "What country does this flag belong to?",R.drawable.ic_flag_of_denmark,
                "denmark","Fiji" ,"New Zealand" ,"Usa",1
            )
            questionList.add(que5)
            return questionList

    }


}