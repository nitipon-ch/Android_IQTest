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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //get argument
        val args = ResultFragmentArgs.fromBundle(arguments!!)

        Toast.makeText(context, "Score : ${args.scoreResult} Name : ${args.userName}", Toast.LENGTH_LONG).show()
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)

        binding.nameResultText.text = "Hey!! ${args.userName}"
        binding.mainMenuResultButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_resultFragment_to_startFragment)
        }
        return binding.root
    }


}
