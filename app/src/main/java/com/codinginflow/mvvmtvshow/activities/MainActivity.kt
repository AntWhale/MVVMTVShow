package com.codinginflow.mvvmtvshow.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.codinginflow.mvvmtvshow.R
import com.codinginflow.mvvmtvshow.adapters.TVShowsAdapter
import com.codinginflow.mvvmtvshow.databinding.ActivityMainBinding
import com.codinginflow.mvvmtvshow.models.TVShow
import com.codinginflow.mvvmtvshow.viewmodels.MostPopularTVShowsViewModel

//api url: https://www.episodate.com/api/

class MainActivity : AppCompatActivity() {

    val viewModel by lazy {
        ViewModelProvider(this).get(MostPopularTVShowsViewModel::class.java)
    }

    val activityMainBinding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main) }
    val tvShows = arrayListOf<TVShow>()
    lateinit var tvShowsAdapter: TVShowsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        doInitialization()
    }

    private fun doInitialization() {
        activityMainBinding.tvShowsRecyclerView.setHasFixedSize(true)
        tvShowsAdapter = TVShowsAdapter(tvShows)
        activityMainBinding.tvShowsRecyclerView.adapter = tvShowsAdapter
        getMostPopularTVShows()
    }


    private fun getMostPopularTVShows() {
        activityMainBinding.isLoading = true

        viewModel.getMostPopularTVShows(0).observe(this){ mostPopularTVShowsResponse ->
            activityMainBinding.isLoading = false

            tvShows.addAll(mostPopularTVShowsResponse.tvShows)
            tvShowsAdapter.notifyDataSetChanged()
        }
    }


}