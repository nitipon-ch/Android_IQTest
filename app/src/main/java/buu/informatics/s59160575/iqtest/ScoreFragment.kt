package buu.informatics.s59160575.iqtest


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import buu.informatics.s59160575.iqtest.databinding.FragmentScoreBinding
import buu.informatics.s59160575.iqtest.databinding.FragmentStartBinding

/**
 * A simple [Fragment] subclass.
 */
class ScoreFragment : Fragment() {
    private lateinit var binding: FragmentScoreBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_score,container,false)
        return binding.root
    }


}
