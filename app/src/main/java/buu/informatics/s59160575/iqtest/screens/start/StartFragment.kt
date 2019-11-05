package buu.informatics.s59160575.iqtest.screens.start


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import buu.informatics.s59160575.iqtest.R
import buu.informatics.s59160575.iqtest.databinding.FragmentStartBinding

/**
 * A simple [Fragment] subclass.
 */
class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_start,container,false)

        binding.letStartButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_startFragment_to_nameFragment)
        }

        binding.highScoreButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_startFragment_to_scoreFragment)
        }


        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.about_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()

        Log.i("StartFragment", "OnStart called")
    }

    override fun onResume() {
        super.onResume()

        Log.i("StartFragment", "OnResume called")
    }

    override fun onPause() {
        super.onPause()

        Log.i("StartFragment", "OnPause called")
    }

    override fun onStop() {
        super.onStop()

        Log.i("StartFragment", "OnStop called")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.i("StartFragment", "OnDestroy called")
    }



}
