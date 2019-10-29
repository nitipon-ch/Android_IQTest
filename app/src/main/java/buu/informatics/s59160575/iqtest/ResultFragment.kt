package buu.informatics.s59160575.iqtest


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import buu.informatics.s59160575.iqtest.databinding.FragmentResultBinding

/**
 * A simple [Fragment] subclass.
 */
class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private  var IQ = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //get argument
        val args = ResultFragmentArgs.fromBundle(arguments!!)

        IQ = computeIQ(args.scoreResult)

        Toast.makeText(context, "Score : ${args.scoreResult} Name : ${args.userName}", Toast.LENGTH_LONG).show()
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)

        binding.nameResultText.text = "${args.userName}"
        binding.scoreResultText.text = " ${ IQ } "
        binding.mainMenuResultButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_resultFragment_to_startFragment)
        }
        return binding.root
    }

    private fun computeIQ(iq: Int) : Int {
        return when (iq) {
            in 0..2 -> 90
            in 3..4 -> 100
            in 5..6 -> 110
            in 7..8 -> 120
            in 9..10 -> 140
            else -> 0
        }

    }


}
