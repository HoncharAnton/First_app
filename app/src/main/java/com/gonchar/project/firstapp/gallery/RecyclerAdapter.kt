package com.gonchar.project.firstapp.gallery

import android.net.Uri
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.gonchar.project.firstapp.R
import com.gonchar.project.firstapp.utils.Constants.Companion.TAG


class RecyclerAdapter(imageURI: ArrayList<Uri>) :
    RecyclerView.Adapter<RecyclerAdapter.PhotoHolder>() {

    private val image = imageURI


    class PhotoHolder(v: View) : View.OnClickListener,
        RecyclerView.ViewHolder(v) {

        private val view = v.findViewById<ImageView>(R.id.holder)


        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when (v!!.id) {

                    R.id.holder -> {

                        //
                        Log.d(TAG, "click item")
                    }

            }

        }

        fun bindPhoto(photo: Uri) {
            view.findViewById<ImageView>(R.id.holder).setImageURI(photo)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val inflatedView = parent.inflate(R.layout.rv_item, false)
        return PhotoHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        val item = image[position]
        holder.bindPhoto(item)
    }

    override fun getItemCount() = image.size
}