package com.codinginflow.mvvmtvshow.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.codinginflow.mvvmtvshow.R
import com.codinginflow.mvvmtvshow.adapters.TVShowsAdapter
import com.codinginflow.mvvmtvshow.databinding.ActivityMainBinding
import com.codinginflow.mvvmtvshow.listeners.TVShowsListener
import com.codinginflow.mvvmtvshow.models.TVShow
import com.codinginflow.mvvmtvshow.viewmodels.MostPopularTVShowsViewModel

//api url: https://www.episodate.com/api/

class MainActivity : AppCompatActivity(), TVShowsListener {

    val viewModel by lazy {
        ViewModelProvider(this).get(MostPopularTVShowsViewModel::class.java)
    }

    val activityMainBinding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main) }
    val tvShows = arrayListOf<TVShow>()
    lateinit var tvShowsAdapter: TVShowsAdapter
    private var currentPage = 1
    private var totalAvailablePages = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        doInitialization()
    }

    private fun doInitialization() {
        activityMainBinding.tvShowsRecyclerView.setHasFixedSize(true)
        tvShowsAdapter = TVShowsAdapter(tvShows, this)
        activityMainBinding.tvShowsRecyclerView.adapter = tvShowsAdapter
        activityMainBinding.tvShowsRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(!activityMainBinding.tvShowsRecyclerView.canScrollVertically(1)){
                    if(currentPage <= totalAvailablePages){
                        currentPage += 1
                        getMostPopularTVShows()
                    }
                }
            }
        })
        getMostPopularTVShows()
    }


    private fun getMostPopularTVShows() {
        toggleLoading()

        viewModel.getMostPopularTVShows(currentPage).observe(this){ mostPopularTVShowsResponse ->
            toggleLoading()

            totalAvailablePages = mostPopularTVShowsResponse.pages
            val oldCount = tvShows.size
            tvShows.addAll(mostPopularTVShowsResponse.tvShows)
            tvShowsAdapter.notifyItemRangeInserted(oldCount, tvShows.size)
        }
    }

    private fun toggleLoading() {
        if (currentPage == 1){
            activityMainBinding.isLoading =
                !(activityMainBinding.isLoading != null && activityMainBinding.isLoading)
        } else {
            activityMainBinding.isLoadingMore =
                !(activityMainBinding.isLoadingMore != null && activityMainBinding.isLoadingMore)
        }
    }

    override fun onTVShowClicked(tvShow: TVShow) {
        val intent = Intent(applicationContext, TVShowDetailsActivity::class.java)
        intent.putExtra("id", tvShow.id)
        intent.putExtra("name", tvShow.name)
        intent.putExtra("startDate", tvShow.startDate)
        intent.putExtra("country", tvShow.country)
        intent.putExtra("network", tvShow.network)
        intent.putExtra("status", tvShow.status)
        startActivity(intent)
    }

}