package com.gonchar.project.firstapp.gallery

import android.graphics.BitmapFactory
import android.media.Image
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.gonchar.project.firstapp.Gallery
import com.gonchar.project.firstapp.R


class RecyclerAdapter(private val uriList: ArrayList<Uri>) :
    RecyclerView.Adapter<RecyclerAdapter.PhotoHolder>() {


    class PhotoHolder(v: View) :
        RecyclerView.ViewHolder(v) {

        private val view = v.findViewById<ImageView>(R.id.holder)

        init {
            view.setOnClickListener{


                Toast.makeText(view.context, "click adapter", Toast.LENGTH_SHORT).show()
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
        val item = uriList[position]
        holder.bindPhoto(item)
    }

    override fun getItemCount() = uriList.size
}