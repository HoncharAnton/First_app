package com.gonchar.project.firstapp.utils

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

/**
     * This method create and show message - toast message
     * @param massage value for showing
     * @param context layout context for where need show the message
     */
    fun showErrMessage(massage: String, context: Context){
        Toast.makeText(context, massage, LENGTH_SHORT).show()
    }
