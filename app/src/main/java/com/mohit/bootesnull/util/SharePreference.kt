package com.mohit.bootesnull.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences




class SharePreference {
    var pref: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    @SuppressLint("CommitPrefEdits")
    constructor(context: Context) {
        if (pref == null) {
            pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
            editor = pref?.edit()
        }
    }

    fun getStringPref(key: String): String? {
        return pref?.getString(key, "")
    }

    fun getIntPref(key: String): Int {
        return pref!!.getInt(key, -1)
    }

    fun setIntPref(key: String, value: Int) {
        editor?.putInt(key, value)?.apply()
    }

    fun setStringPref(key: String, value: String?) {
        editor?.putString(key, value)?.apply()
    }

    fun getBooleanPref(key: String): Boolean {
        return pref!!.getBoolean(key, false)
    }

    fun setBooleanPref(key: String, value: Boolean) {
        editor?.putBoolean(key, value)?.apply()
    }


    companion object {
        var sharePreference: SharePreference? = null
        val PREF_NAME: String = "BootesNull"
        val PRIVATE_MODE: Int = 0
        val check="check"
        val check_photos="check_photos"

        @SuppressLint("CommitPrefEdits")
        fun getInstance(context: Context): SharePreference {
            if (sharePreference == null) {
                sharePreference = SharePreference(context)
            }
            return sharePreference!!
        }

    }

    fun mClearPreg() {
        editor?.clear()
        editor?.commit()
    }

}