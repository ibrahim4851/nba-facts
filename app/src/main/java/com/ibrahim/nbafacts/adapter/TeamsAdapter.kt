package com.ibrahim.nbafacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.nbafacts.R
import com.ibrahim.nbafacts.databinding.TeamItemLayoutBinding
import com.ibrahim.nbafacts.model.Team

class TeamsAdapter(val teamsList : ArrayList<Team>):
RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder>()
{

    class TeamsViewHolder(var view : TeamItemLayoutBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = DataBindingUtil.inflate<TeamItemLayoutBinding>(
            inflater,
            R.layout.team_item_layout,
            parent,
            false
        )
        return TeamsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.view.team = teamsList[position]
    }

    override fun getItemCount(): Int {
        return teamsList.size
    }

    fun updateTeamsList(newTeamsList: List<Team>){
        teamsList.clear()
        teamsList.addAll(newTeamsList)
        notifyDataSetChanged()
    }

}