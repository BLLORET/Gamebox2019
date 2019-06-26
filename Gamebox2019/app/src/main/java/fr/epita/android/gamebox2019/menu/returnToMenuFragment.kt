package fr.epita.android.gamebox2019.menu


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.epita.android.gamebox2019.MainActivity
import fr.epita.android.gamebox2019.R
import kotlinx.android.synthetic.main.fragment_return_to_menu.*

class returnToMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_return_to_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        menuButton.setOnClickListener {
            (activity as MainActivity).printListGame()
        }
    }
}
