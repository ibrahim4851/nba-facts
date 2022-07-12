package com.ibrahim.nbafacts.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ibrahim.nbafacts.R
import com.ibrahim.nbafacts.adapter.GamesAdapter
import com.ibrahim.nbafacts.viewmodel.GamesViewModel
import kotlinx.android.synthetic.main.fragment_games.*
import kotlinx.android.synthetic.main.fragment_player.*
import kotlinx.android.synthetic.main.fragment_teams.*
import kotlinx.android.synthetic.main.page_picker_layout.view.*


class GamesFragment : Fragment() {

    private lateinit var viewModel: GamesViewModel
    private val gamesAdapter = GamesAdapter(arrayListOf())
    private lateinit var bottomSheetView : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GamesViewModel::class.java)
        viewModel.page = MutableLiveData(1)
        viewModel.getData()
        bottomSheetView = layoutInflater.inflate(R.layout.page_picker_layout, null, false)
        gamesRecView.layoutManager = LinearLayoutManager(context)
        gamesRecView.adapter = gamesAdapter
        observeLiveData()
        swipeRefreshStats.setOnRefreshListener {
            observeLiveData()
            swipeRefreshStats.isRefreshing = false
        }
        gameCurrentPage.setOnClickListener {
            openBottomDialog()
        }

        nextGame.setOnClickListener {
            if (viewModel.page.value == viewModel.totalPage.value){
                viewModel.page.value = 1
                observeLiveData()
            }
            else{
            viewModel.page.value?.let {
                viewModel.page.value = viewModel.page.value?.inc()
                gameCurrentPage.text = viewModel.page.value.toString()
            }
            viewModel.getData()
            observeLiveData()}
        }

        previousGame.setOnClickListener {
            viewModel.page.value?.let {
                viewModel.page.value = viewModel.page.value?.dec()
                gameCurrentPage.text = viewModel.page.value.toString()
            }
            viewModel.getData()
            observeLiveData()
        }

    }

    fun openBottomDialog(){
        val dialog = BottomSheetDialog(requireActivity())
        bottomSheetView.pagePicker.minValue = 1
        dialog!!.setCancelable(true)
        dialog.setContentView(bottomSheetView)
        bottomSheetView.pagePicker.value = viewModel.page.value!!
        dialog.show()

        bottomSheetView.pageSelectDone.setOnClickListener {
            viewModel.page.value = bottomSheetView.pagePicker.value
            viewModel.getData()
            observeLiveData()
            (bottomSheetView.parent as? ViewGroup)?.removeView(bottomSheetView)
            dialog.dismiss()
        }
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

        viewModel.totalPage.observe(viewLifecycleOwner, Observer { totalPage ->
            totalPage?.let {
                bottomSheetView.pagePicker.maxValue = totalPage
            }
        })

        viewModel.page.observe(viewLifecycleOwner, Observer { page ->
            page?.let {
                gameCurrentPage.setText(viewModel.page.value.toString())
            }?:run{
                gameCurrentPage.setText("")
            }
        })

    }

}