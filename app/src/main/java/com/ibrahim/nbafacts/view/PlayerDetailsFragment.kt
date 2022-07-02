package com.ibrahim.nbafacts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ibrahim.nbafacts.R

class PlayerDetailsFragment : Fragment() {

    private var pName = ""
    private var heightFeet = ""
    //private var position = ""
    //private var weightPounds = ""
    private var teamName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = PlayerDetailsFragmentArgs
        arguments.let {
            pName = args.fromBundle(it!!).playerName.toString()
            heightFeet = args.fromBundle(it!!).heightFeet.toString()
            //heightInches = args.fromBundle(it!!).heightInches.toString()
            //lastName = args.fromBundle(it!!).playerLastName.toString()
            //position = args.fromBundle(it!!).position.toString()
            //weightPounds = args.fromBundle(it!!).weight.toString()
            teamName = args.fromBundle(it!!).playerTeamName.toString()
        }

        println("details: ")
        println(pName + "\t" + teamName+ "\t" + heightFeet)
    }
}