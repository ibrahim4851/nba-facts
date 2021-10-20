package com.ibrahim.nbafacts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.nbafacts.R
import com.ibrahim.nbafacts.adapter.FeedAdapter
import com.ibrahim.nbafacts.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*


class FeedFragment : Fragment() {

    private lateinit var viewModel: FeedViewModel
    private val feedAdapter = FeedAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.getData()

        feedRecView.layoutManager = LinearLayoutManager(context)
        feedRecView.adapter = feedAdapter
        observeLiveData()

        swipeRefreshFeed.setOnRefreshListener {

        }
    }

    fun observeLiveData() {
        viewModel.feed.observe(viewLifecycleOwner, Observer { feedData ->
            feedData?.let {
                feedRecView.visibility = View.VISIBLE
                feedAdapter.updateFeed(feedData)
            }
        })

        viewModel.feedError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    feedError.visibility = View.VISIBLE
                } else {
                    feedError.visibility = View.GONE
                }
            }
        })

        viewModel.feedLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    feedLoading.visibility = View.VISIBLE
                    feedError.visibility = View.GONE
                    feedRecView.visibility = View.GONE
                } else {
                    feedLoading.visibility = View.GONE
                }
            }
        })
    }

}