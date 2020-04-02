package com.example.zimgur.remote

import com.example.zimgur.main.data.ImgurResponse
import com.google.gson.Gson
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/*
Custom Convertor for unwrapping Imgur Response and just Returning T, This will make make code more readable.
    val data: T,
    val success: Boolean,
    val status: Int,

https://blog.davidmedenjak.com/android/2016/07/12/retrofit-converter-unwrapping.html
 */
class ImgurResponseConverterFactory(gson: Gson) : Converter.Factory() {

    private val gsonConverterFactory: GsonConverterFactory = GsonConverterFactory.create(gson)

    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? {

        val wrappedType = object : ParameterizedType {
            override fun getRawType(): Type = ImgurResponse::class.java

            override fun getOwnerType(): Type? = null

            override fun getActualTypeArguments(): Array<Type> = arrayOf(type)

        }
        val gsonConverter: Converter<ResponseBody, *>? = gsonConverterFactory.responseBodyConverter(wrappedType, annotations, retrofit)
        return ImgurResponseConverter(gsonConverter as Converter<ResponseBody, ImgurResponse<Any>>)
    }

    override fun requestBodyConverter(
            type: Type?, parameterAnnotations: Array<Annotation>, methodAnnotations: Array<Annotation>, retrofit: Retrofit): Converter<*, RequestBody>? {
        return gsonConverterFactory.requestBodyConverter(type!!, parameterAnnotations, methodAnnotations, retrofit)
    }
}