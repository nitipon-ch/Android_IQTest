package buu.informatics.s59160575.iqtest.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    lateinit var userName: String
    lateinit var ans: MutableList<Int>

    private val _questionIndex = MutableLiveData<Int>()
    val questionIndex: LiveData<Int>
        get() = _questionIndex

    private val _questionNum = MutableLiveData<Int>()
    val questionNum: LiveData<Int>
        get() = _questionNum

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _currentQuestion = MutableLiveData<Question>()
    val currentQuestion : LiveData<Question>
        get() = _currentQuestion

    private val _currentAnswer = MutableLiveData<MutableList<Int>>()
    val currentAnswer : LiveData<MutableList<Int>>
        get() = _currentAnswer

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    init {
        Log.i("GameViewModel","GameViewModel Create")
        randomizeQuestions()
        setQuestion()
        _score.value = 0

    }

    fun checkGameFinish() : Boolean{
        return questionIndex.value!!.toInt() >= questions.size-1
    }

    fun checkScore(indexAns: Int) {
        Log.i("GameViewModel","current :  ${_currentAnswer.value!![indexAns]}  answer: ${_currentQuestion.value!!.answers[0]}")
        if(_currentAnswer.value!![indexAns] == _currentQuestion.value!!.answers[0]){
            _score.value = (score.value)?.plus(1)
        }
        Log.i("GameFragment", "Score : ${_score.value}")
    }

    fun randomizeQuestions() {
        questions.shuffle()
        _questionIndex.value = 0
        _questionNum.value = _questionIndex.value!!+1
    }

    fun checkCorrect(index: Int) {
            checkScore(index)

            if (checkGameFinish()){
                onGameFinish()
            }else{
                _questionIndex.value = (questionIndex.value)?.plus(1)
                _questionNum.value = _questionIndex.value!!+1
                setQuestion()
            }
    }

    fun setQuestion(){
        _currentQuestion.value = questions[questionIndex.value!!]
        ans =  _currentQuestion.value!!.answers.toMutableList()
        ans.shuffle()
        _currentAnswer.value = ans
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    fun onGameFinish() {
        _eventGameFinish.value = true
    }
}