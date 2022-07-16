package com.codinginflow.mvvmtvshow.activities

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.codinginflow.mvvmtvshow.R
import com.codinginflow.mvvmtvshow.adapters.ImageSliderAdapter
import com.codinginflow.mvvmtvshow.databinding.ActivityTvshowDetailsBinding
import com.codinginflow.mvvmtvshow.viewmodels.TVShowDetailsViewModel

class TVShowDetailsActivity : AppCompatActivity() {

    private val activityTVShowDetailsBinding by lazy { DataBindingUtil.setContentView<ActivityTvshowDetailsBinding>(this, R.layout.activity_tvshow_details) }
    private lateinit var tvShowDetailsViewModel: TVShowDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doInitialization()
    }

    private fun doInitialization(){
        tvShowDetailsViewModel = ViewModelProvider(this).get(TVShowDetailsViewModel::class.java)
        activityTVShowDetailsBinding.imageBack.setOnClickListener {
            onBackPressed()
        }
        getTVShowDetails()
    }

    fun getTVShowDetails(){
        activityTVShowDetailsBinding.isLoading = true
        val tvShowId = intent.getIntExtra("id", -1)
        tvShowDetailsViewModel.getTVShowDetails(tvShowId).observe(this){ tvShowDetailsResponse ->
            activityTVShowDetailsBinding.isLoading = false
            tvShowDetailsResponse.tvShowDetails.let {
                loadImageSlider(it.pictures)
                activityTVShowDetailsBinding.tvShowImageURL = it.imagePath
                activityTVShowDetailsBinding.imageTVShow.visibility = View.VISIBLE
                activityTVShowDetailsBinding.description = HtmlCompat.fromHtml(
                    it.description,
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                ).toString()
                activityTVShowDetailsBinding.textDescription.visibility = View.VISIBLE
                activityTVShowDetailsBinding.textReadMore.visibility = View.VISIBLE
                activityTVShowDetailsBinding.textReadMore.setOnClickListener {
                    if(activityTVShowDetailsBinding.textReadMore.text.toString() == "Read More"){
                        activityTVShowDetailsBinding.textDescription.maxLines = Int.MAX_VALUE
                        activityTVShowDetailsBinding.textDescription.ellipsize = null
                        activityTVShowDetailsBinding.textReadMore.text = getString(R.string.read_less)
                    } else {
                        activityTVShowDetailsBinding.textDescription.maxLines = 4
                        activityTVShowDetailsBinding.textDescription.ellipsize = TextUtils.TruncateAt.END
                        activityTVShowDetailsBinding.textReadMore.text = getString(R.string.read_more)
                    }
                }

                loadBasicTVShowDetails()
            }
        }
    }

    fun loadImageSlider(sliderImages: List<String>) {
        activityTVShowDetailsBinding.sliderViewPager.offscreenPageLimit = 1
        activityTVShowDetailsBinding.sliderViewPager.adapter = ImageSliderAdapter(sliderImages)
        activityTVShowDetailsBinding.sliderViewPager.visibility = View.VISIBLE
        activityTVShowDetailsBinding.viewFadingEdge.visibility = View.VISIBLE
        setupSliderIndicators(sliderImages.size)
        activityTVShowDetailsBinding.sliderViewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentSliderIndicator(position)
            }
        })
    }

    fun setupSliderIndicators(count : Int){
        val indicators = arrayOfNulls<ImageView>(count)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        for(index in indicators.indices){
            indicators[index] = ImageView(applicationContext)
            indicators[index]?.setImageDrawable(ContextCompat.getDrawable(
                applicationContext,
                R.drawable.background_slider_indicator_inactive
            ))
            indicators[index]?.layoutParams = layoutParams
            activityTVShowDetailsBinding.layoutSliderIndicators.addView(indicators[index])
        }
        activityTVShowDetailsBinding.layoutSliderIndicators.visibility = View.VISIBLE
        setCurrentSliderIndicator(0)
    }

    fun setCurrentSliderIndicator(position : Int) {
        val childCount = activityTVShowDetailsBinding.layoutSliderIndicators.childCount
        for(index in 0 until childCount){
            val imageView = activityTVShowDetailsBinding.layoutSliderIndicators.getChildAt(index) as ImageView
            if(index == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.background_slider_indicator_active)
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.background_slider_indicator_inactive)
                )
            }
        }
    }

    fun loadBasicTVShowDetails() {
        activityTVShowDetailsBinding.let {
            it.tvShowName = intent.getStringExtra("name")
            it.networkCountry = intent.getStringExtra("network").plus(" (").plus(intent.getStringExtra("country")).plus(")")
            it.status = intent.getStringExtra("status")
            it.startedDate = intent.getStringExtra("startDate")
            it.textName.visibility = View.VISIBLE
            it.textNetworkCountry.visibility = View.VISIBLE
            it.textStatus.visibility = View.VISIBLE
            it.textStarted.visibility = View.VISIBLE
        }
    }
}