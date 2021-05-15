package com.gonchar.project.firstapp.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import com.gonchar.project.firstapp.utils.Constants.Companion.TAG

class Utils {


    /**
     * This method create and show message - toast message
     * @param massage value for showing
     * @param context layout context for where need show the message
     */
    fun showErrMessage(massage: String, context: Context){
        Toast.makeText(context, massage, LENGTH_LONG).show()
    }



}