package com.ibrahim.nbafacts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.nbafacts.R
import com.ibrahim.nbafacts.databinding.PlayerItemLayoutBinding
import com.ibrahim.nbafacts.model.Player
import com.ibrahim.nbafacts.view.PlayerFragmentDirections
import kotlinx.android.synthetic.main.fragment_player_details.view.*
import kotlinx.android.synthetic.main.player_item_layout.view.*

class PlayerAdapter(val playerList: ArrayList<Player>) :
    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>(), ClickListener {

    class PlayerViewHolder(var view: PlayerItemLayoutBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.player_item_layout, parent, false)

        val view = DataBindingUtil.inflate<PlayerItemLayoutBinding>(
            inflater,
            R.layout.player_item_layout,
            parent,
            false
        )
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.view.player = playerList[position]
        holder.view.listener = this
        /*
        val firstName = playerList[position].firstName
        val heightFeet = playerList[position].heightFeet
        val heightInches = playerList[position].heightInches
        val lastName = playerList[position].lastName
        val playerPosition = playerList[position].position
        val weightPounds = playerList[position].weightPounds
        val teamName = playerList[position].team.fullName

        val action = PlayerFragmentDirections.actionPlayerFragmentToPlayerDetailsFragment(
            firstName,
            lastName,
            heightFeet,
            heightInches,
            playerPosition,
            teamName,
            weightPounds
        )
        holder.view.setOnClickListener {
            Navigation.findNavController(it).navigate(action)
        }
         */
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    fun updatePlayerList(newPlayerList: List<Player>) {
        playerList.clear()
        playerList.addAll(newPlayerList)
        notifyDataSetChanged()
    }

    override fun onItemClick(view: View) {
        val pHeight = view.playerHeight.text.toString()
        val pName = view.title.text.toString()
        val pTeam = view.subtitle.text.toString()
        println("values to pass:")
        println(pHeight + pName + pTeam)
        val action = PlayerFragmentDirections.actionPlayerFragmentToPlayerDetailsFragment(pName, pHeight, pTeam)
        Navigation.findNavController(view).navigate(action)
    }

}