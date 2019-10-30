package buu.informatics.s59160575.iqtest.Screens.Result

import android.util.Log
import androidx.lifecycle.ViewModel

class ResultViewModel : ViewModel() {
    init {
        Log.i("ResultViewModel" , "ResultViewModel Create")
    }

    fun computeIQ(iq: Int) : Int {
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