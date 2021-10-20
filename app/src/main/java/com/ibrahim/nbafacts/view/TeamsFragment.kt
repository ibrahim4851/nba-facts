package com.ibrahim.nbafacts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.nbafacts.R
import com.ibrahim.nbafacts.adapter.TeamsAdapter
import com.ibrahim.nbafacts.viewmodel.TeamsViewModel
import kotlinx.android.synthetic.main.fragment_stats.*
import kotlinx.android.synthetic.main.fragment_teams.*


class TeamsFragment : Fragment() {

    private lateinit var viewModel: TeamsViewModel
    private val teamsAdapter = TeamsAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TeamsViewModel::class.java)
        viewModel.page = MutableLiveData(1)
        viewModel.getData()
        teamsRecView.layoutManager = LinearLayoutManager(context)
        teamsRecView.adapter = teamsAdapter
        observeLiveData()
        swipeRefreshTeam.setOnRefreshListener {
            observeLiveData()
            swipeRefreshTeam.isRefreshing = false
        }
    }

    private fun observeLiveData() {
        viewModel.teamsList.observe(viewLifecycleOwner, Observer { teamsList ->
            teamsList?.let {
                //print("fragmentvalue:\n" + teamsList[0].fullName)
                teamsAdapter.updateTeamsList(teamsList)
                teamsRecView.visibility = View.VISIBLE
            }
        })
        viewModel.teamsError.observe(viewLifecycleOwner, Observer { error ->
            error.let {
                if (it){
                    teamsError.visibility = View.VISIBLE
                }else{
                    teamsError.visibility = View.GONE
                    teamsRecView.visibility = View.VISIBLE
                }
            }
        })
        viewModel.teamsLoading.observe(viewLifecycleOwner, Observer {
            loading ->
            loading?.let {
                if (it){
                    teamsLoading.visibility = View.VISIBLE
                    teamsError.visibility = View.GONE
                    teamsRecView.visibility = View.GONE
                }else{
                    teamsLoading.visibility = View.GONE
                }
            }
        })
    }

}