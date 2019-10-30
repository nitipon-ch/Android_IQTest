package buu.informatics.s59160575.iqtest.Screens.Game

import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import buu.informatics.s59160575.iqtest.R

class GameViewModel : ViewModel() {
    data class Question(
        val image: Int,
        val answers: List<Int>)

    val questions: MutableList<Question> = mutableListOf(
        Question(
            image = R.drawable.question_one,
            answers = listOf(
                R.drawable.ans1_1,
                R.drawable.ans1_2,
                R.drawable.ans1_3,
                R.drawable.ans1_4
            )
        ),
        Question(
            image = R.drawable.question_two,
            answers = listOf(
                R.drawable.ans2_1,
                R.drawable.ans2_2,
                R.drawable.ans2_3,
                R.drawable.ans2_4
            )
        ),
        Question(
            image = R.drawable.question_three,
            answers = listOf(
                R.drawable.ans3_1,
                R.drawable.ans3_2,
                R.drawable.ans3_3,
                R.drawable.ans3_4
            )
        ),
        Question(
            image = R.drawable.question_four,
            answers = listOf(
                R.drawable.ans4_1,
                R.drawable.ans4_2,
                R.drawable.ans4_3,
                R.drawable.ans4_4
            )
        ),
        Question(
            image = R.drawable.question_five,
            answers = listOf(
                R.drawable.ans5_1,
                R.drawable.ans5_2,
                R.drawable.ans5_3,
                R.drawable.ans5_4
            )
        ),
        Question(
            image = R.drawable.question_six,
            answers = listOf(
                R.drawable.ans6_1,
                R.drawable.ans6_2,
                R.drawable.ans6_3,
                R.drawable.ans6_4
            )
        ),
        Question(
            image = R.drawable.question_seven,
            answers = listOf(
                R.drawable.ans7_1,
                R.drawable.ans7_2,
                R.drawable.ans7_3,
                R.drawable.ans7_4
            )
        ),
        Question(
            image = R.drawable.question_eigth,
            answers = listOf(
                R.drawable.ans8_1,
                R.drawable.ans8_2,
                R.drawable.ans8_3,
                R.drawable.ans8_4
            )
        ),
        Question(
            image = R.drawable.question_nine,
            answers = listOf(
                R.drawable.ans9_1,
                R.drawable.ans9_2,
                R.drawable.ans9_3,
                R.drawable.ans9_4
            )
        ),
        Question(
            image = R.drawable.question_ten,
            answers = listOf(
                R.drawable.ans10_1,
                R.drawable.ans10_2,
                R.drawable.ans10_3,
                R.drawable.ans10_4
            )
        )

    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<Int>
    var questionIndex = 0
    var score : Int = 0
    init {
        Log.i("GameViewModel","GameViewModel Create")
    }

    fun checkScore(indexAns: Int) {
        if(answers[indexAns] == currentQuestion.answers[0]){
            score++
        }
        Log.i("GameFragment", "Score : ${score}")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }
}