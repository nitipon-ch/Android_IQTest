package buu.informatics.s59160575.iqtest.screens.highScore

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import buu.informatics.s59160575.iqtest.R
import buu.informatics.s59160575.iqtest.TextItemViewHolder
import buu.informatics.s59160575.iqtest.database.GameScore

class HighScoreAdapter: RecyclerView.Adapter<TextItemViewHolder>()  {
    var data =  listOf<GameScore>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]


        Log.i("HighscoreAdapt", "${item.username}")


        holder.textView.text = "      "+item.username+makeBlank(item)+item.score
    }

    fun makeBlank( item : GameScore) : String{
        var blank = "                "


        return blank
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.text_item_view, parent, false) as TextView
        return TextItemViewHolder(view)
    }


}