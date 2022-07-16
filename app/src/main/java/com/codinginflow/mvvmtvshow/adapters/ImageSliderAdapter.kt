package com.codinginflow.mvvmtvshow.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codinginflow.mvvmtvshow.R
import com.codinginflow.mvvmtvshow.databinding.ItemContainerSliderImageBinding

class ImageSliderAdapter(val sliderImages: List<String>) : RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSliderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val sliderImageBinding = DataBindingUtil.inflate<ItemContainerSliderImageBinding>(
            layoutInflater, R.layout.item_container_slider_image, parent, false
        )
        return ImageSliderViewHolder(sliderImageBinding)
    }

    override fun onBindViewHolder(holder: ImageSliderViewHolder, position: Int) {
        holder.bindSliderImage(sliderImages[position])
    }

    override fun getItemCount(): Int {
        return sliderImages.size
    }

    inner class ImageSliderViewHolder(val itemContainerSliderImageBinding: ItemContainerSliderImageBinding) :
        RecyclerView.ViewHolder(itemContainerSliderImageBinding.root) {
        fun bindSliderImage(imageURL: String) {
            itemContainerSliderImageBinding.imageURL = imageURL
        }
    }


}