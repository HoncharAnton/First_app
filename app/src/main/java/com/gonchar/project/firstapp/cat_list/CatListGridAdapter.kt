package com.gonchar.project.firstapp.cat_list

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

class CatListGridAdapter(val context: Context, private val url: ArrayList<String>) : BaseAdapter() {

    override fun getCount() = url.size

    override fun getItem(position: Int) = url[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val image: ImageView

        if (convertView == null) {
            image = ImageView(context)
            Glide.with(context).load(url[position]).into(image)
        } else {
            image = convertView as ImageView
        }
        image.id = position
        return image
    }
}