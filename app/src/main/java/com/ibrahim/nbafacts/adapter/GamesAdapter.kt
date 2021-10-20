package com.ibrahim.nbafacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.nbafacts.R
import com.ibrahim.nbafacts.databinding.GamesItemLayoutBinding
import com.ibrahim.nbafacts.model.Game

class GamesAdapter(val gamesList: ArrayList<Game>):
RecyclerView.Adapter<GamesAdapter.GamesViewHolder>()
{

class GamesViewHolder(var view: GamesItemLayoutBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = DataBindingUtil.inflate<GamesItemLayoutBinding>(
            inflater,
            R.layout.games_item_layout,
            parent,
            false
        )
        return GamesViewHolder(view)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        holder.view.game = gamesList[position]
    }

    override fun getItemCount(): Int {
        return gamesList.size
    }

    fun updateGamesList(newGamesList: List<Game>){
        gamesList.clear()
        gamesList.addAll(newGamesList)
        notifyDataSetChanged()
    }

}