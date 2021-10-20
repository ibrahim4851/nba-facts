package com.ibrahim.nbafacts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.nbafacts.R
import com.ibrahim.nbafacts.adapter.GamesAdapter
import com.ibrahim.nbafacts.viewmodel.GamesViewModel
import kotlinx.android.synthetic.main.fragment_stats.*


class GamesFragment : Fragment() {

    private lateinit var viewModel: GamesViewModel
    private val gamesAdapter = GamesAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GamesViewModel::class.java)
        viewModel.page = MutableLiveData(1)
        viewModel.getData()
        gamesRecView.layoutManager = LinearLayoutManager(context)
        gamesRecView.adapter = gamesAdapter
        observeLiveData()

    }

    fun observeLiveData(){
        viewModel.gamesList.observe(viewLifecycleOwner, Observer { gamesList ->
            gamesList?.let {
                gamesAdapter.updateGamesList(gamesList)
                gamesRecView.visibility = View.VISIBLE
            }
        })

        viewModel.gamesError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it){
                    gamesError.visibility = View.VISIBLE
                }else{
                    gamesError.visibility = View.GONE
                    gamesRecView.visibility = View.VISIBLE
                }
            }
        })
        viewModel.gamesLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it){
                    gamesLoading.visibility = View.VISIBLE
                    gamesError.visibility = View.GONE
                    gamesRecView.visibility = View.GONE
                }else{
                    gamesLoading.visibility = View.GONE
                }
            }
        })
    }

}