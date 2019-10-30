package buu.informatics.s59160575.iqtest.Screens.Game


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
import buu.informatics.s59160575.iqtest.Screens.Game.GameFragmentDirections
import buu.informatics.s59160575.iqtest.R
import buu.informatics.s59160575.iqtest.Screens.Game.GameFragmentArgs
import buu.informatics.s59160575.iqtest.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    data class Question(
        val image: Int,
        val answers: List<Int>)

    private val questions: MutableList<Question> = mutableListOf(
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
    private var questionIndex = 0
    var score : Int = 0

    private lateinit var binding: FragmentGameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //get argument
        val args =
            GameFragmentArgs.fromBundle(arguments!!)
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_game, container, false)

        randomizeQuestions()


        binding.answerButton1.setOnClickListener { view ->
           if (questionIndex >= questions.size-1){
               checkScore(0)
               view.findNavController().navigate(
                   GameFragmentDirections.actionGameFragmentToResultFragment(
                       score,
                       args.userName
                   )
               )
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
                view.findNavController().navigate(
                    GameFragmentDirections.actionGameFragmentToResultFragment(
                        score,
                        args.userName
                    )
                )
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
                view.findNavController().navigate(
                    GameFragmentDirections.actionGameFragmentToResultFragment(
                        score,
                        args.userName
                    )
                )
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
                view.findNavController().navigate(
                    GameFragmentDirections.actionGameFragmentToResultFragment(
                        score,
                        args.userName
                    )
                )
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
        if(answers[indexAns] == currentQuestion.answers[0]){
            score++
        }
        Log.i("GameFragment", "Score : ${score}")
    }
}
