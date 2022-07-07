package com.ibrahim.nbafacts.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color.RED
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
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

    private lateinit var holder: PlayerViewHolder

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

    private fun <T : Drawable> T.mutate(function: T.() -> Unit) {
        mutate()
        function()
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.view.player = playerList[position]
        val holderBackground = holder.view.root.playerItemTeamColorFirst
        val holderBackground2 = holder.view.root.playerItemTeamColorSecond
        when(playerList[position].team.abbreviation){
            "BOS"->setDrawableColors(R.color.BostonWhite, R.color.CelticGreen, holderBackground, holderBackground2)
            "ATL"->holderBackground.setBackgroundColor(ContextCompat.getColor(holder.view.root.context, R.color.TorchRed))
        }
        /*
        if (playerList[position].team.abbreviation == "BOS"){
            holder.view.root.setBackgroundColor(R.color.CelticGreen)
        }
         */
        holder.view.listener = this


    }

    fun setDrawableColors(color1: Int, color2: Int, drawable1:ImageView, drawable2: ImageView) {
        drawable1.setColorFilter(ContextCompat.getColor(holder.view.root.context, color1))
        drawable2.setColorFilter(ContextCompat.getColor(holder.view.root.context, color2))
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