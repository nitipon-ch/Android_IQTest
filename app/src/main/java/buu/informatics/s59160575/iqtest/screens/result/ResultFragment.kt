package buu.informatics.s59160575.iqtest.screens.result


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import buu.informatics.s59160575.iqtest.R
import buu.informatics.s59160575.iqtest.databinding.FragmentResultBinding

/**
 * A simple [Fragment] subclass.
 */
class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private lateinit var viewModel: ResultViewModel

    private  var IQ = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //get argument

        viewModel = ViewModelProviders.of(this).get(ResultViewModel::class.java)

        val args =
            ResultFragmentArgs.fromBundle(
                arguments!!
            )

        IQ = viewModel.computeIQ(args.scoreResult)

        Toast.makeText(context, "your score correct is ${args.scoreResult} ", Toast.LENGTH_LONG).show()
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_result, container, false)

        binding.nameResultText.text = "${args.userName}"
        binding.scoreResultText.text = " ${ IQ } "
        binding.mainMenuResultButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_resultFragment_to_startFragment)
        }
        setHasOptionsMenu(true)
        return binding.root
    }


    // Creating our Share Intent
    private fun getShareIntent() : Intent {
        val args =
            ResultFragmentArgs.fromBundle(
                arguments!!
            )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, getString(R.string.share_success_text, args.userName, IQ))
        return shareIntent
    }

    // Starting an Activity with our new Intent
    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    // Showing the Share Menu Item Dynamically
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.result_menu, menu)
        // check if the activity resolves
        if (null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            // hide the menu item if it doesn't resolve
            menu.findItem(R.id.share)?.setVisible(false)
        }
    }

    // Sharing from the Menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}



