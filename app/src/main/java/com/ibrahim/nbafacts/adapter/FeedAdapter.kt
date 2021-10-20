package com.ibrahim.nbafacts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.ibrahim.nbafacts.R
import com.ibrahim.nbafacts.databinding.FeedItemLayoutBinding
import com.ibrahim.nbafacts.model.FeedModel
import com.ibrahim.nbafacts.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.feed_item_layout.view.*

class FeedAdapter(val feedList: ArrayList<FeedModel>) :
    RecyclerView.Adapter<FeedAdapter.FeedViewHolder>(), ClickListener {

    class FeedViewHolder(var view: FeedItemLayoutBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.feed_item_layout, parent, false)
        //defining view with using data binding:
        val view = DataBindingUtil.inflate<FeedItemLayoutBinding>(
            inflater,
            R.layout.feed_item_layout,
            parent,
            false
        )
        return FeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.view.feed = feedList[position]
        holder.view.listener = this
    }

    override fun getItemCount(): Int {
        return feedList.size
    }

    fun updateFeed(newFeedList: List<FeedModel>) {
        feedList.clear()
        feedList.addAll(newFeedList)
        notifyDataSetChanged()
    }

    override fun onItemClick(view: View) {
        val id = view.feedIdText.text.toString()
        println("IDVAL: " + id)
        if (id == "1") {
            val action = FeedFragmentDirections.actionFeedFragmentToGamesFragment()
            Navigation.findNavController(view).navigate(action)
        } else if (id == "2") {
            val action = FeedFragmentDirections.actionFeedFragmentToPlayerFragment()
            Navigation.findNavController(view).navigate(action)
        } else {
            val action = FeedFragmentDirections.actionFeedFragmentToTeamsFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

}