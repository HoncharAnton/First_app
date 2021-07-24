package com.gonchar.project.firstapp.cat_list

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gonchar.project.firstapp.R
import com.gonchar.project.firstapp.gallery.inflate

class CatListRecAdapter(
    private val titleList: ArrayList<String>,
    private val addressList: ArrayList<String>,
    private val context: Context
) :
    RecyclerView.Adapter<CatListRecAdapter.CatHolder>() {

    class CatHolder(v: View) :
        RecyclerView.ViewHolder(v) {

        private val title = v.findViewById<TextView>(R.id.catName)
        private val photo = v.findViewById<ImageView>(R.id.catHolder)

        fun bindTitle(name: String) {
            title.text = name
        }

        fun bindPhoto(URL: String, context: Context) {
            Glide.with(context).load(URL).into(photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatListRecAdapter.CatHolder {
        val inflatedView = parent.inflate(R.layout.cat_list_item, false)
        return CatHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: CatHolder, position: Int) {
        holder.bindTitle(titleList[position])
        holder.bindPhoto(addressList[position], context)
    }

    override fun getItemCount() = titleList.size

}