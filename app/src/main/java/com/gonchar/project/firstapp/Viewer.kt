package com.gonchar.project.firstapp

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.gonchar.project.firstapp.databinding.ActivityViewerBinding
import jp.wasabeef.glide.transformations.BlurTransformation

class Viewer : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityViewerBinding
    private lateinit var imageList: Array<String>
    private var iterator = 0
    lateinit var currentImageUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewerBinding.inflate(layoutInflater)
        initListener(binding)
        setContentView(binding.root)
        title = getString(R.string.viewer_tb_title_viewer)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        when(v!!.id){
            binding.viewerIv.id -> menuInflater.inflate(R.menu.iv_shape_menu, menu)
        }
        super.onCreateContextMenu(menu, v, menuInfo)
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.rectangle -> {
                Glide.with(this)
                    .load(currentImageUrl)
                    .apply(RequestOptions.overrideOf(800,600))
                    .into(binding.viewerIv)
            }
            R.id.rounded_corner -> {
                Glide.with(this)
                    .load(currentImageUrl)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(36)))
                    .into(binding.viewerIv)
            }
            R.id.circle -> {
                Glide.with(this)
                    .load(currentImageUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .into(binding.viewerIv)
            }
            R.id.sat_min -> setSaturationFilter(0f)
            R.id.sat_half -> setSaturationFilter(0.5f)
            R.id.sat_max -> setSaturationFilter(1f)

            R.id.normal -> setBlurFilter(1)
            R.id.solid -> setBlurFilter(2)
            R.id.outer -> setBlurFilter(3)
            R.id.inner -> setBlurFilter(4)
        }
        return super.onContextItemSelected(item)
    }

    /**
     * setBlurFilter makes image transformation with blur options and set it to the imageView
     * @param blurLevel degree of the blur processing
     */
    private fun setBlurFilter(blurLevel: Int) {
        Glide.with(this)
            .load(currentImageUrl)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(5,blurLevel)))
            .into(binding.viewerIv)
    }

    /**
     * setImageFilter creates image filter (saturation) ans set it to the imageView
     * @param satLevel it is saturation level value
     */
    private fun setSaturationFilter(satLevel: Float) {
        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(satLevel)
        val filter = ColorMatrixColorFilter(colorMatrix)
        binding.viewerIv.colorFilter = filter

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
        registerForContextMenu(binding.viewerIv)
        binding.btnNext.isEnabled = true
        changeImageViewContent()
        iterator = 1
        return super.onOptionsItemSelected(item)
    }

    /**
     * this method set clickListener to element which needed be clickable
     */
    private fun initListener(binding: ActivityViewerBinding) {
        binding.btnNext.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnNext -> {
                changeImageViewContent()
                iterator++
            }
        }
    }

    /**
     * changeImageViewContent method changes displayed content in the field for displayed image
     */
    private fun changeImageViewContent() {
        checkIterator()
        Glide.with(this).load(imageList[iterator]).into(binding.viewerIv)
        currentImageUrl = imageList[iterator]
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