package com.example.memeit

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.memeit.network.Meme

@BindingAdapter("setImage")
fun bindImage(imgView: ImageView, imgUrl: String?){
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.img_not_found)
        }
    }
}

@BindingAdapter("setImageList")
fun bindImageList(recyclerView: RecyclerView, imgList: List<Meme>?){
    val adapter = recyclerView.adapter as MemeListAdapter
    adapter.submitList(imgList)
}

@BindingAdapter("setApiStatus")
fun bindApiStatus(imgView: ImageView, status: ApiStatus?){
    when(status){
        ApiStatus.LOADING -> {
            imgView.visibility = View.VISIBLE
            imgView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.SUCCESS -> {
            imgView.visibility = View.GONE
        }
        else -> {
            imgView.visibility = View.VISIBLE
            imgView.setImageResource(R.drawable.img_not_found)
        }
    }

}
