package com.gonchar.project.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.gonchar.project.firstapp.databinding.ActivityViewerBinding


class Viewer : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityViewerBinding
    private lateinit var imageList: Array<String>
    private var iterator = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewer)

        binding = ActivityViewerBinding.inflate(layoutInflater)
        initListener(binding)

        setContentView(binding.root)
        title = getString(R.string.viewer_tb_title_viewer)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu!!.run {
            add(0, 1, 0, getString(R.string.viewer_om_item_drawable))
            add(0, 2, 0,  getString(R.string.viewer_om_item_assets))
            add(0, 3, 0,  getString(R.string.viewer_om_item_internet))
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.title){
            getString(R.string.viewer_om_item_drawable) -> {
                imageList = arrayOf(
                    "android.resource://com.gonchar.project.firstapp/drawable/a",
                    "android.resource://com.gonchar.project.firstapp/drawable/b",
                    "android.resource://com.gonchar.project.firstapp/drawable/c",
                    "android.resource://com.gonchar.project.firstapp/drawable/d",
                    "android.resource://com.gonchar.project.firstapp/drawable/e"
                )
            }
            getString(R.string.viewer_om_item_assets) -> {
                imageList = arrayOf(
                    "file:///android_asset/moto/11.jpg",
                    "file:///android_asset/moto/22.jpg",
                    "file:///android_asset/moto/33.jpg",
                    "file:///android_asset/moto/44.jpg",
                    "file:///android_asset/moto/55.jpg"
                )
            }
            getString(R.string.viewer_om_item_internet) -> {
                imageList = arrayOf(
                    "https://cdn.vox-cdn.com/thumbor/894e49uw2yGjngcAqAzYgpmGkAI=/0x0:4644x2612/1200x800/filters:focal(1951x935:2693x1677)/cdn.vox-cdn.com/uploads/chorus_image/image/67049814/SuperStrata_Studio_White_SideView_2370.0.png",
                    "https://d1wa5qhtul915h.cloudfront.net/app/uploads/2021/04/Bike-Europe-valeo-motor-1-560x373.jpg",
                    "https://i.natgeofe.com/k/70c8c0b3-e6e9-4c8e-9e9b-795e6dcc2b1c/bike-large-front-wheel1.jpg?w=636&h=920",
                    "https://www.canyon.com/dw/image/v2/BCML_PRD/on/demandware.static/-/Library-Sites-canyon-shared/default/dwb8c86591/images/specials/Fat-bike-Dude-wheels-US3.jpg?sw=1280",
                    "https://electrek.co/wp-content/uploads/sites/3/2020/11/radmission-review-header.jpg?quality=82&strip=all&w=1600"
                )
            }
        }
        binding.btnNext.isEnabled = true
        return super.onOptionsItemSelected(item)
    }

    private fun initListener(binding: ActivityViewerBinding) {
        binding.btnNext.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnNext -> {
                checkIterator()
                changeImageViewContent()
            }
        }
    }

    /**
     * changeImageViewContent method changes displayed content in the field for displayed image
     */
    private fun changeImageViewContent() {
        Glide.with(this).load(imageList[iterator]).into(binding.imageView2)
        iterator++
    }

    /**
     * checkIterator method compare iterator value with content array`s last index.
     * this method during content requests let not exceed the array`s indexes range
     */
    private fun checkIterator() {
        if (iterator > imageList.lastIndex)
            iterator = 0
    }
}