package buu.informatics.s59160575.iqtest.screens.name


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import buu.informatics.s59160575.iqtest.R
import buu.informatics.s59160575.iqtest.databinding.FragmentNameBinding

/**
 * A simple [Fragment] subclass.
 */
class NameFragment : Fragment() {
    private lateinit var binding: FragmentNameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_name, container, false)
        binding.startButton.setOnClickListener { view ->
            checkName()

        }

        return binding.root
    }

    private fun checkName() {
        var username = binding.nameEditText.text.toString()
        if(username.isEmpty()){
            Toast.makeText(context, "Please Input your name", Toast.LENGTH_LONG).show()
        }else{
            view!!.findNavController().navigate(
                NameFragmentDirections.actionNameFragmentToGameFragment(
                    username
                )
            )
        }
    }


}
