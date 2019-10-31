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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import buu.informatics.s59160575.iqtest.Screens.Game.GameFragmentDirections
import buu.informatics.s59160575.iqtest.R
import buu.informatics.s59160575.iqtest.Screens.Game.GameFragmentArgs
import buu.informatics.s59160575.iqtest.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel

    private lateinit var binding: FragmentGameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args = GameFragmentArgs.fromBundle(arguments!!)
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_game, container, false)

        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        //binding with gameViewModel add data in fragment_game
//        binding.gameViewModel = viewModel

        viewModel.questionIndex.observe(this, Observer { newQuestionIndex ->
            binding.questionText.text = "Question ${newQuestionIndex.toInt() +1} / 12"

        })

        viewModel.randomizeQuestions()
        setQuestion()

        binding.answerButton1.setOnClickListener { view ->
            checkCorrect(0, args.userName)
        }

        binding.answerButton2.setOnClickListener { view ->
            checkCorrect(1, args.userName)
        }

        binding.answerButton3.setOnClickListener { view ->
            checkCorrect(2, args.userName)
        }

        binding.answerButton4.setOnClickListener { view ->
            checkCorrect(3, args.userName)
        }

        return binding.root
    }

    private fun checkCorrect(index: Int, username: String) {
        viewModel.apply {
            if (questionIndex.value!!.toInt() >= questions.size-1){
                checkScore(0)
                view!!.findNavController().navigate(
                    GameFragmentDirections.actionGameFragmentToResultFragment(
                        score.value!!,
                        username
                    )
                )
            }else{
                checkScore(0)
                _questionIndex.value = (questionIndex.value)?.plus(1)
                currentQuestion = questions[questionIndex.value!!]
                setQuestion()

                binding.invalidateAll()
            }
        }
    }


    fun setQuestion(){
        val imageQuestion : ImageView = binding.questionView
        val textTitle : TextView = binding.questionText
        
        viewModel.apply {
//            textTitle.setText("Question ${questionIndex.value!!.toInt() +1} / 12")

            currentQuestion = questions[questionIndex.value!!]
            answers = currentQuestion.answers.toMutableList()
            answers.shuffle()
            imageQuestion.setImageResource(currentQuestion.image)
        }
        
        setAnswers()
    }

    fun setAnswers() {
        val ImageButton1 : Button = binding.answerButton1
        val ImageButton2 : Button = binding.answerButton2
        val ImageButton3 : Button = binding.answerButton3
        val ImageButton4 : Button = binding.answerButton4
        
        viewModel.apply {
            ImageButton1.setBackgroundResource(answers[0])
            ImageButton2.setBackgroundResource(answers[1])
            ImageButton3.setBackgroundResource(answers[2])
            ImageButton4.setBackgroundResource(answers[3])
        }
        
    }


}
