package com.gonchar.project.firstapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gonchar.project.firstapp.utils.Constants.Companion.TAG
import com.gonchar.project.firstapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        initListener(binding)

        setContentView(binding.root)
        title = getString(R.string.main_tb_title)
    }

    /**
     * this method set clickListener to element which needed be clickable
     */
    private fun initListener(main: ActivityMainBinding) {

        binding.btnCalc.setOnClickListener(this)
        binding.btnPic.setOnClickListener(this)
        binding.btnTaskPic.setOnClickListener(this)
        binding.btnTilesMenu.setOnClickListener(this)
        binding.btnCatsList.setOnClickListener(this)
        binding.btnCalendar.setOnClickListener(this)
        binding.btnAnim.setOnClickListener(this)

    }

    /**
     * this method handles different element clicks
     */
    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.btnCalc -> {
                startActivity(Intent(this,Calculator::class.java))
                Log.d(TAG, "calculator button")
            }
            R.id.btnPic -> {
                startActivity(Intent(this, Gallery::class.java))
                Log.d(TAG, "Gallery button")
            }
            R.id.btnTaskPic -> {
                startActivity(Intent(this, Viewer::class.java ))
            }
            R.id.btnTilesMenu -> {
                startActivity(Intent(this, Tiles::class.java))
            }
            R.id.btnCatsList -> {
                startActivity(Intent(this, CatList::class.java))
            }
            R.id.btnCalendar -> {
                Log.d(TAG, "calendar button")
            }
            R.id.btnAnim -> {
                Log.d(TAG, "animation button")
            }

        }
    }
}