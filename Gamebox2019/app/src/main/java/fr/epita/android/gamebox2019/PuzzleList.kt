package fr.epita.android.gamebox2019

import android.view.View
import android.widget.ImageView

class PuzzleList(private val view: View) {
    var listDrawable: MutableList<Int> = arrayListOf()

    init {
        listDrawable.add(R.drawable.dog1)
        listDrawable.add(R.drawable.dog2)
        listDrawable.add(R.drawable.dog3)
        listDrawable.add(R.drawable.dog4)
        listDrawable.add(R.drawable.dog5)
        listDrawable.add(R.drawable.dog6)
        listDrawable.add(R.drawable.dog7)
        listDrawable.add(R.drawable.dog8)
        listDrawable.add(R.drawable.empty)
        listDrawable = listDrawable.shuffled() as MutableList<Int>
        load()
    }

    private fun load() {
        view.findViewById<ImageView>(R.id.imageView1).setImageResource(listDrawable[0])
        view.findViewById<ImageView>(R.id.imageView2).setImageResource(listDrawable[1])
        view.findViewById<ImageView>(R.id.imageView3).setImageResource(listDrawable[2])
        view.findViewById<ImageView>(R.id.imageView4).setImageResource(listDrawable[3])
        view.findViewById<ImageView>(R.id.imageView5).setImageResource(listDrawable[4])
        view.findViewById<ImageView>(R.id.imageView6).setImageResource(listDrawable[5])
        view.findViewById<ImageView>(R.id.imageView7).setImageResource(listDrawable[6])
        view.findViewById<ImageView>(R.id.imageView8).setImageResource(listDrawable[7])
        view.findViewById<ImageView>(R.id.imageView9).setImageResource(listDrawable[8])
    }

    fun switchAndLoad(pos1: Int, pos2: Int) {
        val tmp : Int = listDrawable[pos1]
        listDrawable[pos1] = listDrawable[pos2]
        listDrawable[pos2] = tmp
        load()
    }
}