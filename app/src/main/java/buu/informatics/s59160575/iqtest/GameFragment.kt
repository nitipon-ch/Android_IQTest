package buu.informatics.s59160575.iqtest


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import buu.informatics.s59160575.iqtest.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        binding.answerButton1.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
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


}
