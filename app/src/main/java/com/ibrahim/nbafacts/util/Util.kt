package com.ibrahim.nbafacts.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.ibrahim.nbafacts.R

fun ImageView.downloadFromUrl(url: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .centerCrop()
        .into(this)

}

fun placeHolderProgressBar(context: Context): CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        centerRadius = 40f
        strokeWidth = 8f
        start()
    }
}

//below annotation makes it enable to use that function in xml files
@BindingAdapter("android:downloadUrl")
fun downloadImage(view: ImageView, url: String?){
    view.downloadFromUrl(url, placeHolderProgressBar(view.context))
}