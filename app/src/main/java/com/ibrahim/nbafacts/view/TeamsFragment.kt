package com.ibrahim.nbafacts.view

import GridSpacingItemDecoration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ibrahim.nbafacts.R
import com.ibrahim.nbafacts.adapter.TeamsAdapter
import com.ibrahim.nbafacts.viewmodel.TeamsViewModel
import kotlinx.android.synthetic.main.fragment_teams.*
import kotlinx.android.synthetic.main.page_picker_layout.view.*


class TeamsFragment : Fragment() {

    private lateinit var viewModel: TeamsViewModel
    private val teamsAdapter = TeamsAdapter(arrayListOf())
    private lateinit var bottomSheetView : View


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
        bottomSheetView = layoutInflater.inflate(R.layout.page_picker_layout, null, false)
        viewModel.page = MutableLiveData(1)
        viewModel.getData()
        teamsRecView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        val spanCount = 2
        val spacing = 100
        val includeEdge = true
        teamsRecView.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
        teamsRecView.adapter = teamsAdapter
        observeLiveData()
        swipeRefreshTeam.setOnRefreshListener {
            observeLiveData()
            swipeRefreshTeam.isRefreshing = false
        }
        teamCurrentPage.setOnClickListener {
            openBottomDialog()
        }
        nextTeam.setOnClickListener {
            if (viewModel.page.value == viewModel.totalPage.value){
                viewModel.page.value = 1
                teamCurrentPage.text = viewModel.page.value.toString()
                viewModel.getData()
                observeLiveData()
            }
            else{
                viewModel.page.value?.let {
                    viewModel.page.value = viewModel.page.value?.inc()
                    teamCurrentPage.text = viewModel.page.value.toString()
                    viewModel.getData()
                    observeLiveData()}
            }
        }
        previousTeam.setOnClickListener {
            viewModel.page.value?.let {
                viewModel.page.value = viewModel.page.value?.dec()
                teamCurrentPage.text = viewModel.page.value.toString()
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
        dialog.show()

        bottomSheetView.pageSelectDone.setOnClickListener {
            viewModel.page.value = bottomSheetView.pagePicker.value
            viewModel.getData()
            observeLiveData()
            (bottomSheetView.parent as? ViewGroup)?.removeView(bottomSheetView)
            dialog.dismiss()
        }
    }

    private fun observeLiveData() {
        viewModel.teamsList.observe(viewLifecycleOwner, Observer { teamsList ->
            teamsList?.let {
                teamsAdapter.updateTeamsList(teamsList)
                teamsRecView.visibility = View.VISIBLE
            }
        })
        viewModel.totalPage.observe(viewLifecycleOwner, Observer { totalPage->
            totalPage?.let {
                bottomSheetView.pagePicker.maxValue = totalPage
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
        viewModel.page.observe(viewLifecycleOwner, Observer { page ->
            page?.let {
                teamCurrentPage.setText(viewModel.page.value.toString())
            }?:run{
                teamCurrentPage.setText("")
            }
        })
    }

}