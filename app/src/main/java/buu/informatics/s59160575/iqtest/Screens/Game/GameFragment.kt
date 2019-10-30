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

        viewModel.questionIndex.observe(this, Observer { newQuestionIndex ->
            binding.questionText.text = "Question ${newQuestionIndex.toInt() +1} / 12"

        })

        randomizeQuestions()

        binding.answerButton1.setOnClickListener { view ->
            viewModel.apply {
                if (questionIndex.value!!.toInt() >= questions.size-1){
                    checkScore(0)
                    view.findNavController().navigate(
                        GameFragmentDirections.actionGameFragmentToResultFragment(
                            score.value!!,
                            args.userName
                        )
                    )
                }else{
                    checkScore(0)
                    questionIndex.value = (questionIndex.value)?.plus(1)
                    currentQuestion = questions[questionIndex.value!!]
                    setQuestion()

                    binding.invalidateAll()
                }
            }

        }

        binding.answerButton2.setOnClickListener { view ->
            viewModel.apply {
                if (questionIndex.value!! >= questions.size-1){
                    checkScore(1)
                    view.findNavController().navigate(
                        GameFragmentDirections.actionGameFragmentToResultFragment(
                            score.value!!,
                            args.userName
                        )
                    )
                }else{
                    checkScore(1)
                    questionIndex.value = (questionIndex.value)?.plus(1)
                    currentQuestion = questions[questionIndex.value!!]
                    setQuestion()

                    binding.invalidateAll()

                }
            }
            
        }

        binding.answerButton3.setOnClickListener { view ->
            viewModel.apply {
                if (questionIndex.value!! >= questions.size-1){
                    checkScore(2)
                    view.findNavController().navigate(
                        GameFragmentDirections.actionGameFragmentToResultFragment(
                            score.value!!,
                            args.userName
                        )
                    )
                }else{
                    checkScore(2)
                    questionIndex.value = (questionIndex.value)?.plus(1)
                    currentQuestion = questions[questionIndex.value!!]
                    setQuestion()

                    binding.invalidateAll()
                }
            }
        }

        binding.answerButton4.setOnClickListener { view ->
            viewModel.apply {
                if (questionIndex.value!! >= questions.size-1){
                    checkScore(3)
                    view.findNavController().navigate(
                        GameFragmentDirections.actionGameFragmentToResultFragment(
                            score.value!!,
                            args.userName
                        )
                    )
                }else{
                    checkScore(3)
                    questionIndex.value = (questionIndex.value)?.plus(1)
                    currentQuestion = questions[questionIndex.value!!]
                    setQuestion()
                    binding.invalidateAll()

                }
            }
        }

        return binding.root
    }

    fun randomizeQuestions() {
        viewModel.questions.shuffle()
        viewModel.questionIndex.value = 0
        setQuestion()
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