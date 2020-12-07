package com.malkinfo.animaldog.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.malkinfo.animaldog.R

fun getProgressDrawable(c:Context):CircularProgressDrawable{
    return CircularProgressDrawable(c).apply {
        strokeWidth = 20f
        centerRadius = 50f
    }
}
fun ImageView.loadImage(uri:String,progressDrawable:CircularProgressDrawable){
    val option = RequestOptions()
            .placeholder(progressDrawable)
            .error(R.mipmap.ic_launcher)
    Glide.with(context)
            .setDefaultRequestOptions(option)
            .load(uri)
            .into(this)
}