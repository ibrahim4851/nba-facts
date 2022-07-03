package com.ibrahim.nbafacts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ibrahim.nbafacts.R
import com.ibrahim.nbafacts.databinding.FragmentPlayerBinding
import com.ibrahim.nbafacts.databinding.FragmentPlayerDetailsBinding
import com.ibrahim.nbafacts.databinding.PlayerItemLayoutBinding
import kotlinx.android.synthetic.main.fragment_player_details.*

class PlayerDetailsFragment : Fragment() {

    private lateinit var pName : String
    private lateinit var heightFeet : String
    //private var position = ""
    //private var weightPounds = ""
    private lateinit var teamName : String
    private lateinit var playerDataBinding : FragmentPlayerDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        playerDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_player_details, container, false)
        return playerDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = PlayerDetailsFragmentArgs
        arguments?.let {
            pName = args.fromBundle(it).playerName.toString()
            heightFeet = args.fromBundle(it).heightFeet.toString()
            //heightInches = args.fromBundle(it).heightInches.toString()
            //lastName = args.fromBundle(it).playerLastName.toString()
            //position = args.fromBundle(it).position.toString()
            //weightPounds = args.fromBundle(it).weight.toString()
            teamName = args.fromBundle(it).playerTeamName.toString()
        }
        //detailsPlayerName.text = pName
        //detailsPlayerHeight.text = heightFeet
        //detailsPlayerTeam.text = teamName
    }
}