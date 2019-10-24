package buu.informatics.s59160575.iqtest


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import buu.informatics.s59160575.iqtest.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    data class Question(
        val image: Int,
        val answers: List<Int>)

    private val questions: MutableList<Question> = mutableListOf(
        Question(image = R.drawable.question_one,
            answers = listOf(R.drawable.ans1_1, R.drawable.ans1_2, R.drawable.ans1_3, R.drawable.ans1_4)),
        Question(image = R.drawable.question_two,
            answers = listOf(R.drawable.ans2_1, R.drawable.ans2_2, R.drawable.ans2_3, R.drawable.ans2_4)),
        Question(image = R.drawable.question_three,
            answers = listOf(R.drawable.ans3_1, R.drawable.ans3_2, R.drawable.ans3_3, R.drawable.ans3_4))

    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<Int>
    private var questionIndex = 0
    private var score = 0

    private lateinit var binding: FragmentGameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        randomizeQuestions()


        binding.answerButton1.setOnClickListener { view ->
           if (questionIndex >= questions.size-1){
               checkScore(0)
               view.findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
           }else{
               checkScore(0)
               questionIndex++
               currentQuestion = questions[questionIndex]
               setQuestion()

               binding.invalidateAll()

           }


        }
        binding.answerButton2.setOnClickListener { view ->
            if (questionIndex >= questions.size-1){
                checkScore(1)
                view.findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
            }else{
                checkScore(1)
                questionIndex++
                currentQuestion = questions[questionIndex]
                setQuestion()

                binding.invalidateAll()

            }
        }
        binding.answerButton3.setOnClickListener { view ->
            if (questionIndex >= questions.size-1){
                checkScore(2)
                view.findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
            }else{
                checkScore(2)
                questionIndex++
                currentQuestion = questions[questionIndex]
                setQuestion()

                binding.invalidateAll()

            }
        }
        binding.answerButton4.setOnClickListener { view ->
            if (questionIndex >= questions.size-1){
                checkScore(3)
                view.findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
            }else{
                checkScore(3)
                questionIndex++
                currentQuestion = questions[questionIndex]
                setQuestion()
                binding.invalidateAll()

            }
        }

        return binding.root
    }

    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    private fun setQuestion(){
        val imageQuestion : ImageView = binding.questionView
        val textTitle : TextView = binding.questionText

        textTitle.setText("Question ${questionIndex+1} / 12")

        currentQuestion = questions[questionIndex]

        answers = currentQuestion.answers.toMutableList()
        answers.shuffle()
        imageQuestion.setImageResource(currentQuestion.image)
        setAnswers()
    }

    private fun setAnswers() {
        val ImageButton1 : Button = binding.answerButton1
        val ImageButton2 : Button = binding.answerButton2
        val ImageButton3 : Button = binding.answerButton3
        val ImageButton4 : Button = binding.answerButton4

        ImageButton1.setBackgroundResource(answers[0])
        ImageButton2.setBackgroundResource(answers[1])
        ImageButton3.setBackgroundResource(answers[2])
        ImageButton4.setBackgroundResource(answers[3])
    }

    private fun checkScore(indexAns: Int) {
        Log.i("GameFragment", "${answers[indexAns]}  :  ${currentQuestion.answers[0]}")
        if(answers[indexAns] == currentQuestion.answers[0]){
            score++
        }
        Log.i("GameFragment", "Score : ${score}")
    }
}
