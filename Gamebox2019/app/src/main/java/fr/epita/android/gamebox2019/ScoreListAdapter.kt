package fr.epita.android.gamebox2019

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ScoreListAdapter (private val context: Context,
                        private val data: MutableList<DScore>): BaseAdapter()
{
    override fun getItem(p0: Int): DScore {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val currentItem: DScore = getItem(position)
        val layoutInflater = LayoutInflater.from(context)
        val rowView = layoutInflater.inflate(R.layout.score_item, parent,false)

        // Putting row data into views
        val dateTextView = rowView.findViewById<TextView>(R.id.scoreDate)
        dateTextView.text = currentItem.date
        val gameTextView = rowView.findViewById<TextView>(R.id.scoreGame)
        gameTextView.text = currentItem.game
        val playerTextView = rowView.findViewById<TextView>(R.id.scorePlayer)
        playerTextView.text = currentItem.player
        val scoreTextView = rowView.findViewById<TextView>(R.id.scoreScore)
        scoreTextView.text = currentItem.score

        // Returning the filled row
        return rowView
    }

}