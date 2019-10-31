package buu.informatics.s59160575.iqtest.screens.game


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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import buu.informatics.s59160575.iqtest.R
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
        binding.gameViewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.currentQuestion.observe(this, Observer { newQuestion ->
            binding.questionView.setBackgroundResource(newQuestion.image)
        })

        viewModel.currentAnswer.observe( this, Observer { newAnswer ->
            binding.answerButton1.setBackgroundResource(newAnswer[0])
            binding.answerButton2.setBackgroundResource(newAnswer[1])
            binding.answerButton3.setBackgroundResource(newAnswer[2])
            binding.answerButton4.setBackgroundResource(newAnswer[3])
        })

        
        viewModel.eventGameFinish.observe(this, Observer<Boolean> { hasFinished ->

            if (hasFinished) gameFinished(args.userName)

        })

        viewModel.userName = args.userName

        return binding.root
    }

    private fun gameFinished(username: String) {
        view!!.findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToResultFragment(
                viewModel.score.value!!,
                username
            )
        )
        viewModel.onGameFinishComplete()
    }
}
