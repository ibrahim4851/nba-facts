package com.ibrahim.nbafacts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.nbafacts.R
import com.ibrahim.nbafacts.databinding.TeamItemLayoutBinding
import com.ibrahim.nbafacts.model.Team
import com.ibrahim.nbafacts.view.TeamsFragmentDirections
import kotlinx.android.synthetic.main.team_item_layout.view.*

class TeamsAdapter(val teamsList: ArrayList<Team>) :
    RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder>(), ClickListener {

    class TeamsViewHolder(var view: TeamItemLayoutBinding) : RecyclerView.ViewHolder(view.root)

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

    fun updateTeamsList(newTeamsList: List<Team>) {
        teamsList.clear()
        teamsList.addAll(newTeamsList)
        notifyDataSetChanged()
    }

    override fun onItemClick(view: View) {
        val teamId = view.teamId.text.toString()
        val action = TeamsFragmentDirections.actionTeamsFragmentToTeamDetailsFragment(teamId)
        Navigation.findNavController(view).navigate(action)
    }

}