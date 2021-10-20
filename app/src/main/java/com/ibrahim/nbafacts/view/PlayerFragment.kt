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
import com.ibrahim.nbafacts.adapter.PlayerAdapter
import com.ibrahim.nbafacts.viewmodel.PlayersViewModel
import kotlinx.android.synthetic.main.fragment_player.*
import kotlin.math.absoluteValue

class PlayerFragment : Fragment() {

    private lateinit var viewModel: PlayersViewModel
    private val playerAdapter = PlayerAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PlayersViewModel::class.java)
        viewModel.page = MutableLiveData(1)
        viewModel.getData()
        playerRecView.layoutManager = LinearLayoutManager(context)
        playerRecView.adapter = playerAdapter
        observeLiveData()
        swipeRefreshPlayer.setOnRefreshListener {
            observeLiveData()
            swipeRefreshPlayer.isRefreshing = false
        }
    }

    fun observeLiveData() {
        viewModel.playersList.observe(viewLifecycleOwner, Observer { playersList ->
            playersList?.let {
                playerAdapter.updatePlayerList(playersList)
                playerRecView.visibility = View.VISIBLE
            }
        })

        viewModel.playersError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    playerErrorText.visibility = View.VISIBLE
                } else {
                    playerErrorText.visibility = View.GONE
                    playerRecView.visibility = View.VISIBLE
                }
            }
        })

        viewModel.playersLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    playersProgressBar.visibility = View.VISIBLE
                    playerErrorText.visibility = View.GONE
                    playerRecView.visibility = View.GONE
                } else {
                    playersProgressBar.visibility = View.GONE
                }
            }
        })

        viewModel.page.observe(viewLifecycleOwner, Observer { pageCount ->
            pageCount?.let {
                previous.setOnClickListener {
                    viewModel.changePage()
                    viewModel.getData()
                    println("pagecount: " + pageCount)
                }
            }
        })
    }


}