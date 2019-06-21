package fr.epita.android.gamebox2019

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class GameListAdapter(
    private val context : Context,
    private val data: MutableList<DGame>) : BaseAdapter() {

    // returns the number of items in this adapter
    override fun getCount(): Int {
        return data.size
    }

    // returns the item at the specified position
    override fun getItem(position: Int): DGame {
        return data[position]
    }

    // return the ID of the specified row (customized or row position)
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        // Get the item at the specific position
        val currentItem: DGame = getItem(position)
        // now we build a view
        // first step, acquire a LayoutInflater
        val layoutInflater = LayoutInflater.from(context)
        // now use this LayoutInflater to inflate our row layout resource
        // into a View
        val rowView = layoutInflater.inflate(
            R.layout.game_item,
            parent,false
        )

        val nameTextView = rowView.findViewById<TextView>(R.id.game_name)
        nameTextView.text = currentItem.name

        Glide
            .with(rowView)
            .load(currentItem.picture)
            .into(rowView.findViewById<ImageView>(R.id.picture_game))

        /*Log.w("name: ", currentItem.name)
        Log.w("bool: ", currentItem.playable.toString())*/
        val playable : Boolean = (currentItem.name == "Hangman" || currentItem.name == "SlidingPuzzle")
        if (playable) {
            rowView.findViewById<ImageView>(R.id.picture_playable).setImageResource(R.drawable.playable)
        } else {
            rowView.findViewById<ImageView>(R.id.picture_playable).setImageResource(R.drawable.not_playable)
        }


        return rowView
    }

}