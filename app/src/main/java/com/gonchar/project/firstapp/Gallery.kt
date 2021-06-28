package com.gonchar.project.firstapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.gonchar.project.firstapp.databinding.ActivityGalleryBinding
import com.gonchar.project.firstapp.gallery.RecyclerAdapter
import com.gonchar.project.firstapp.utils.Constants
import com.gonchar.project.firstapp.utils.Constants.Companion.RANGE_CORRECTOR_NEGATIVE
import com.gonchar.project.firstapp.utils.Constants.Companion.REQUEST_CODE

class Gallery : AppCompatActivity(), View.OnClickListener {

    //private lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var binding: ActivityGalleryBinding
    private var uriList: ArrayList<Uri> = ArrayList()
    //private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGalleryBinding.inflate(layoutInflater)
        initListener()
        setContentView(binding.root)

        title = getString(R.string.gal_tb_title)
    }


    /**
     * this method set clickListener to element which needed be clickable
     */
    private fun initListener() {

        binding.btnAdd.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnAdd -> {
                uriList.clear()
                openOpenDocActivity()
            }
        }
    }

    /**
     * this method opens system activity with all photo on the device.
     * uses for choice image sequence
     */
    private fun openOpenDocActivity() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            if (data?.clipData != null) {

                val count = data.clipData!!.itemCount + RANGE_CORRECTOR_NEGATIVE
                for (i in 0..count) {
                    uriList.add(data.clipData!!.getItemAt(i).uri)
                }
                makeRecViewAdapter()
                binding.imageView.setImageURI(data.clipData!!.getItemAt(count).uri)
            } else if (data?.data != null) {
                binding.imageView.setImageURI(data.data)
            }
        }
    }

    /**
     * makeRecViewAdapter method create recycler adapter for recycler view (fills recycler view)
     */
    private fun makeRecViewAdapter() {

        Log.d(Constants.TAG,"makeRecView+++")
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = HORIZONTAL
        binding.recyclerView.layoutManager = linearLayoutManager
        val adapter = RecyclerAdapter(uriList)
        binding.recyclerView.adapter = adapter

    }
}