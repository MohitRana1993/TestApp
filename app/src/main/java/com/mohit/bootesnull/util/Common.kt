@file:Suppress("DEPRECATION")

package com.mohit.bootesnull.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable

import android.view.*
import android.widget.Toast
import com.mohit.bootesnull.R
import java.text.SimpleDateFormat


import java.util.*

@SuppressLint("SimpleDateFormat")
object Common {

    fun getToast(activity: Activity, strTxtToast: String) {
        Toast.makeText(activity, strTxtToast, Toast.LENGTH_SHORT).show()
    }


    open var dialog: Dialog? = null

    open fun dismissLoadingProgress() {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
            dialog = null
        }
    }

    open fun showLoadingProgress(context: Activity) {
        if (dialog != null) {
            dialog!!.dismiss()
            dialog = null
        }
        dialog = Dialog(context)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.setContentView(R.layout.dlg_progress)
        dialog!!.setCancelable(false)
        dialog!!.show()
    }

    val BaseImageUrl="http://3.230.218.97:5001/"

    fun getDateTime(s: String): String? {
        try {
            val sdf = SimpleDateFormat("dd MMM, HH:MM a")
            val netDate = Date(s.toLong() * 1000)
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }

     fun getDateTimeUpdate(s: String): String? {
         return try {
             val sdf = SimpleDateFormat("dd-MM-yyyy")
             val netDate = Date(s.toLong() * 1000)
             sdf.format(netDate)
         } catch (e: Exception) {
             e.toString()
         }
    }

     fun getDate(s: String): String? {
         return try {
             val sdf = SimpleDateFormat("dd MMM")
             val netDate = Date(s.toLong() * 1000)
             sdf.format(netDate)
         } catch (e: Exception) {
             e.toString()
         }
    }

     fun getTime(s: String): String? {
         return try {
             val sdf = SimpleDateFormat("HH:MM a")
             val netDate = Date(s.toLong() * 1000)
             sdf.format(netDate)
         } catch (e: Exception) {
             e.toString()
         }
    }

     fun getDay(s: String): String? {
         return try {
             val sdf = SimpleDateFormat("EEE")
             val netDate = Date(s.toLong() * 1000)
             sdf.format(netDate)
         } catch (e: Exception) {
             e.toString()
         }
    }
}