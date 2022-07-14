package com.ibrahim.nbafacts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ibrahim.nbafacts.R
import com.ibrahim.nbafacts.databinding.FragmentPlayerBinding
import com.ibrahim.nbafacts.databinding.FragmentPlayerDetailsBinding
import com.ibrahim.nbafacts.databinding.PlayerItemLayoutBinding
import com.ibrahim.nbafacts.viewmodel.PlayerDetailsViewModel
import kotlinx.android.synthetic.main.fragment_player_details.*

class PlayerDetailsFragment : Fragment() {

    private lateinit var viewModel : PlayerDetailsViewModel
    private var playerId = ""
    private lateinit var playerDataBinding : FragmentPlayerDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        playerDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_player_details, container, false)
        return playerDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            playerId = PlayerDetailsFragmentArgs.fromBundle(it).playerId.toString()
        }
        viewModel = ViewModelProviders.of(this).get(PlayerDetailsViewModel::class.java)
        viewModel.playerId.value = playerId
        viewModel.getData()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.player.observe(viewLifecycleOwner, Observer { player->
            player?.let {
                playerDataBinding.selectedPlayer = player
            }
        })
    }

}