package buu.informatics.s59160575.iqtest


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
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
    private val numQuestions = Math.min((questions.size + 1) / 2, 3)

    private lateinit var binding: FragmentGameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        randomizeQuestions()



        binding.answerButton1.setOnClickListener { view ->
           if (questionIndex == questions.size-1){
               view.findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
           }else{
               questionIndex++
               currentQuestion = questions[questionIndex]
               setQuestion()
               binding.invalidateAll()
           }


        }
        binding.answerButton2.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
        }
        binding.answerButton3.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
        }
        binding.answerButton4.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
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


}
