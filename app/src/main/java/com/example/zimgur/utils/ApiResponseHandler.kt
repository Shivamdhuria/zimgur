package com.example.zimgur.utils

import com.example.zimgur.main.data.ImgurResponse
import com.google.gson.Gson

abstract class ApiResponseHandler<Data>(

        private val response: ApiResult<Any?>
) {

    private val TAG: String = "AppDebug"

    suspend fun getResult(): GenericResult<*> {

        return when (response) {

            is ApiResult.GenericError -> {
                val unwrapped = Gson().fromJson<ImgurResponse<Any>>(response.errorMessage, ImgurResponse::class.java)
                val errorMessage = unwrapped?.data?.let { it } ?: response.errorMessage
                /*
                Doing this because somehow Imgur decided it's best to give error Data field in Imgur Response???? :x
                 */
                GenericResult.GenericError(response.code, errorMessage.toString())
            }

            is ApiResult.NetworkError -> {
                GenericResult.NetworkError
            }

            is ApiResult.Success -> {
                if (response.value == null) {
                    GenericResult.GenericError(0, "Empty Body")
                } else {
                    val imgurResponse = response.value
                    handleSuccess(resultObj = imgurResponse as Data)
                }
            }

        }
    }

    abstract suspend fun handleSuccess(resultObj: Data): GenericResult<*>

}