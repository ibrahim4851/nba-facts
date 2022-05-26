package com.ibrahim.nbafacts.view

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ibrahim.nbafacts.R
import com.ibrahim.nbafacts.adapter.PlayerAdapter
import com.ibrahim.nbafacts.viewmodel.PlayersViewModel
import kotlinx.android.synthetic.main.fragment_player.*
import kotlinx.android.synthetic.main.page_picker_layout.*
import kotlinx.android.synthetic.main.page_picker_layout.view.*


class PlayerFragment : Fragment() {

    private lateinit var viewModel: PlayersViewModel
    private val playerAdapter = PlayerAdapter(arrayListOf())
    private lateinit var bottomSheetView : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_player, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PlayersViewModel::class.java)
        viewModel.getData()
        observeLiveData()
        bottomSheetView = layoutInflater.inflate(R.layout.page_picker_layout, null, false)
        playerRecView.layoutManager = LinearLayoutManager(context)
        playerRecView.adapter = playerAdapter

        swipeRefreshPlayer.setOnRefreshListener {
            viewModel.getData()
            observeLiveData()
            swipeRefreshPlayer.isRefreshing = false
        }

        next.setOnClickListener {
            viewModel.page.value?.let {
                viewModel.page.value = viewModel.page.value?.inc()
                playerCurrentPage.text = viewModel.page.value.toString()
            }
            viewModel.getData()
            observeLiveData()
        }

        previous.setOnClickListener {
            viewModel.page.value?.let {
                viewModel.page.value = viewModel.page.value?.dec()
                playerCurrentPage.text = viewModel.page.value.toString()
            }
            viewModel.getData()
            observeLiveData()
        }

        playerCurrentPage.setOnClickListener {
            openBottomDialog()
        }

        searchPlayer.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                // If the event is a key-down event on the "enter" button
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    Log.i("edittextdata", searchPlayer.text.toString())
                    viewModel.searchQuery.value = searchPlayer.text.toString()
                    viewModel.searchPlayer()
                    observeLiveData()
                    return true
                }
                return false
            }
        })

    }

    fun openBottomDialog(){
        val dialog = BottomSheetDialog(requireActivity())
        bottomSheetView.pagePicker.minValue = 1
        dialog!!.setCancelable(true)
        dialog.setContentView(bottomSheetView)
        dialog.show()

        bottomSheetView.pageSelectDone.setOnClickListener {
            viewModel.page.value = bottomSheetView.pagePicker.value
            viewModel.getData()
            observeLiveData()
            (bottomSheetView.parent as? ViewGroup)?.removeView(bottomSheetView)
            dialog.dismiss()
        }
    }

    fun observeLiveData() {
        viewModel.playersList.observe(viewLifecycleOwner, Observer { playersList ->
            playersList?.let {
                playerAdapter.updatePlayerList(playersList)
                playerRecView.visibility = View.VISIBLE
            }
        })

        viewModel.totalPage.observe(viewLifecycleOwner, Observer { totalPage ->
            totalPage?.let {
                bottomSheetView.pagePicker.maxValue = totalPage
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

        viewModel.page.observe(viewLifecycleOwner, Observer { page ->
            page?.let {
                playerCurrentPage.setText(viewModel.page.value.toString())
            }?:run{
                playerCurrentPage.setText("")
            }
        })
    }

}