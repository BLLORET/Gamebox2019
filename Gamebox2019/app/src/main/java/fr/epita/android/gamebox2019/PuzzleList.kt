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

    private fun switch(pos1: Int, pos2: Int){
        val tmp : Int = listDrawable[pos1]
        listDrawable[pos1] = listDrawable[pos2]
        listDrawable[pos2] = tmp
    }

    fun switchAndLoad(actualPos: Int, posLeft: Int, posRight: Int, posUp: Int, posDown: Int) {

        if (listDrawable[actualPos] != R.drawable.empty) {
            if (posLeft != -1 && listDrawable[posLeft] == R.drawable.empty) {
                switch(actualPos, posLeft)
            } else if (posRight != -1 && listDrawable[posRight] == R.drawable.empty) {
                switch(actualPos, posRight)
            } else if (posUp != -1 && listDrawable[posUp] == R.drawable.empty) {
                switch(actualPos, posUp)
            } else if (posDown != -1 && listDrawable[posDown] == R.drawable.empty) {
                switch(actualPos, posDown)
            }
        }
        load()
    }

    fun isWin(): Boolean {
        return listDrawable[0] == R.drawable.dog1 && listDrawable[1] == R.drawable.dog2
                && listDrawable[2] == R.drawable.dog3 && listDrawable[3] == R.drawable.dog4
                && listDrawable[4] == R.drawable.dog5 && listDrawable[5] == R.drawable.dog6
                && listDrawable[6] == R.drawable.dog7 && listDrawable[7] == R.drawable.dog8
    }
}