package com.mike.moviehubdemo.utility

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class Converters {
    private val moshi: Moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromString(value: String?): List<Int>? {
        if (value == null) return null

        val listType = Types.newParameterizedType(List::class.java, Integer::class.javaObjectType)
        val adapter: JsonAdapter<List<Int>> = moshi.adapter(listType)

        return adapter.fromJson(value)
    }

    @TypeConverter
    fun listToString(list: List<Int>?): String? {
        if (list == null) return null

        val listType = Types.newParameterizedType(List::class.java, Integer::class.javaObjectType)
        val adapter: JsonAdapter<List<Int>> = moshi.adapter(listType)

        return adapter.toJson(list)
    }
}