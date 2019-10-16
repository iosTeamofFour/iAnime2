package com.nemesiss.dev.ianime.Adapter

import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.nemesiss.dev.ianime.R

class SplashScrollImageAdapter(var bitmaps: ArrayList<Bitmap>) : RecyclerView.Adapter<SplashScrollImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SplashScrollImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.splash_screen_image, parent, false)
        val holder = SplashScrollImageViewHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return bitmaps.size
    }

    override fun onBindViewHolder(holder: SplashScrollImageViewHolder, position: Int) {
        var bitmap = bitmaps[position]
        if (bitmap.width < bitmap.height) {
            holder.imageView.post {
                val ivWidth = holder.imageView.width
                val lp = holder.imageView.layoutParams
                lp.height = (ivWidth * bitmap.height.toFloat() / bitmap.width).toInt()
                holder.imageView.layoutParams = lp
                holder.imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
            }
        } else {
            // 按照9/16截取
            holder.imageView.post {
                val ivWidth = holder.imageView.width
                val croppedWidth = bitmap.height * 9 / 16
                val x = ((bitmap.width - croppedWidth) / 2)
                val y = 0
                bitmap = Bitmap.createBitmap(bitmap, x, y, croppedWidth, bitmap.height)

                val lp = holder.imageView.layoutParams
                lp.height = (ivWidth * 16 / 9)
                holder.imageView.layoutParams = lp
                holder.imageView.scaleType = ImageView.ScaleType.CENTER
            }
        }
        holder.imageView.setImageBitmap(bitmap)
    }
}

class SplashScrollImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageView: ImageView = view as ImageView
}