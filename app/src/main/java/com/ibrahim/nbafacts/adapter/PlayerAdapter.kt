package com.ibrahim.nbafacts.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Color.RED
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.Log
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
        val abbreviation = playerList[position].team.abbreviation
        val context = holder.view.root.context
        setDrawableColors(context, abbreviation, holderBackground, holderBackground2)
        holder.view.listener = this

    }

    fun setDrawableColors(context: Context, abbreviation: String, drawable1: ImageView, drawable2: ImageView) {
        val res: Resources = context.getResources()
        val packageName: String = context.getPackageName()
        Log.i("abbreviation", abbreviation)
        val colorId1: Int = res.getIdentifier(abbreviation, "color", packageName)
        val colorId2: Int = res.getIdentifier(abbreviation+"2", "color", packageName)
        val desiredColor1: Int = ContextCompat.getColor(context, colorId1)
        val desiredColor2: Int = ContextCompat.getColor(context, colorId2)
        drawable1.setColorFilter(desiredColor1)
        drawable2.setColorFilter(desiredColor2)
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
        val action = PlayerFragmentDirections.actionPlayerFragmentToPlayerDetailsFragment(
            pName,
            pHeight,
            pTeam
        )
        Navigation.findNavController(view).navigate(action)
    }
}