package buu.informatics.s59160575.iqtest.screens.highScore


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import buu.informatics.s59160575.iqtest.R
import buu.informatics.s59160575.iqtest.database.GameScoreDatabase
import buu.informatics.s59160575.iqtest.databinding.FragmentScoreBinding

/**
 * A simple [Fragment] subclass.
 */
class HighScoreFragment : Fragment() {
    private lateinit var binding: FragmentScoreBinding
    private lateinit var highScoreViewModel: HighScoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_score,container,false)

        val application = requireNotNull(this.activity).application

        val dataSource = GameScoreDatabase.getInstance(application).gameScoreDatabaseDao

        val viewModelFactory = HighScoreViewModelFactory(dataSource, application)



        highScoreViewModel = ViewModelProviders.of(this, viewModelFactory).get(HighScoreViewModel::class.java)

        binding.lifecycleOwner = this

        binding.highScoreViewModel = highScoreViewModel

        val adapter = HighScoreAdapter()
        binding.scoreList.adapter = adapter

        highScoreViewModel.score.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        binding.mainMennuButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_scoreFragment_to_startFragment)
        }


        return binding.root
    }


}
