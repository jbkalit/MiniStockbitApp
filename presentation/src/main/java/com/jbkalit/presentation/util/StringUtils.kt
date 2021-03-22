package com.jbkalit.presentation.util

import android.util.Log
import android.util.Patterns
import java.net.URI
import java.net.URISyntaxException

class StringUtils {

    companion object {

        fun isBlank(string: CharSequence?): Boolean {
            return string == null || string.toString().isEmpty()
                    || string.toString().trim().isEmpty()
        }

        fun isValidEmailAddress(string: CharSequence): Boolean {
            return !isBlank(string) && Patterns.EMAIL_ADDRESS.matcher(string).matches()
        }

        fun checkValidUrl(url: String): Boolean {
            return try {
                val uri = URI(url)
                uri.host != null
            } catch (e: URISyntaxException) {
                Log.e("ERROR", "Invalid url: $url")
                false
            }
        }
    }

}
