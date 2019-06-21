package fr.epita.android.gamebox2019


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


class PuzzlePlayFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_puzzle_play, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var puzzleList : PuzzleList = PuzzleList(view)
    }
}
