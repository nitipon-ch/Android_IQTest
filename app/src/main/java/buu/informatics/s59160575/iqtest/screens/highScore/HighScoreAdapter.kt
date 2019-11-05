package buu.informatics.s59160575.iqtest.screens.highScore

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
        holder.textView.text = item.username+"            "+item.score
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.text_item_view, parent, false) as TextView
        return TextItemViewHolder(view)
    }


}