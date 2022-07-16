package com.codinginflow.mvvmtvshow.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codinginflow.mvvmtvshow.R
import com.codinginflow.mvvmtvshow.databinding.ItemContainerTvShowBinding
import com.codinginflow.mvvmtvshow.listeners.TVShowsListener
import com.codinginflow.mvvmtvshow.models.TVShow

class TVShowsAdapter(val tvShows: List<TVShow>, val tvShowsListener: TVShowsListener) : RecyclerView.Adapter<TVShowsAdapter.TVShowViewHolder>() {
    //private val tvShows = arrayListOf<TVShow>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val tvShowBinding = DataBindingUtil.inflate<com.codinginflow.mvvmtvshow.databinding.ItemContainerTvShowBinding>(
            layoutInflater, R.layout.item_container_tv_show, parent, false
        )
        return TVShowViewHolder(tvShowBinding)
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        holder.bindTVShow(tvShows.get(position))
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }

    inner class TVShowViewHolder(val itemContainerTvShowBinding: ItemContainerTvShowBinding) :
        RecyclerView.ViewHolder(itemContainerTvShowBinding.root) {
        fun bindTVShow(tvShow: TVShow) {
            itemContainerTvShowBinding.tvShow = tvShow
            itemContainerTvShowBinding.executePendingBindings()
            itemContainerTvShowBinding.root.setOnClickListener {
                tvShowsListener.onTVShowClicked(tvShow)
            }
        }
    }
}