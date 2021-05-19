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
        val main = binding
        initListener(main)

        setContentView(main.root)
        title = getString(R.string.main_tb_title)
    }

    /**
     * this method set clickListener to element which needed be clickable
     */
    private fun initListener(main: ActivityMainBinding) {

        main.btnCalc.setOnClickListener(this)
        main.btnPic.setOnClickListener(this)
        main.btnTilesMenu.setOnClickListener(this)
        main.btnCatsList.setOnClickListener(this)
        main.btnCalendar.setOnClickListener(this)
        main.btnAnim.setOnClickListener(this)

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
                Log.d(TAG, "Gallery button")
            }
            R.id.btnTilesMenu -> {
                Log.d(TAG, "Tiles Menu button")
            }
            R.id.btnCatsList -> {
                Log.d(TAG, "Cats List button")
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