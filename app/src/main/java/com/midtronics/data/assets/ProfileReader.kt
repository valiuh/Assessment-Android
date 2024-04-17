package com.midtronics.data.assets

import android.content.Context
import com.google.gson.Gson
import com.midtronics.data.Profile
import java.nio.charset.Charset

class ProfileReader(
    private val context: Context,
    private val fileName: String
) {

    fun getProfile(): Profile {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            val json = String(buffer, Charset.defaultCharset())

           return Gson().fromJson(json, Profile::class.java)
    }
}