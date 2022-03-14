package com.example.sfy_prueba.view_model

import com.example.sfy_prueba.repository.models.ErrorModel
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.HttpException


class ErrorHandler(private val errorModel: ErrorModel) {


    private fun findErrorCode(): String {
        errorModel.t?.let {
            if (it is HttpException) {
                return it.code().toString()
            }
        }
        return ""
    }

    private fun getErrorResponseMessage(response: ResponseBody): String {
        return response.toString()
        //return JSONObject(response.toString()).getString("content")
    }

    private fun getErrorMessage(): String {
        errorModel.t?.message?.let { return it }
        errorModel.errorResponse?.let { return getErrorResponseMessage(it) }
        return "Unknown Error"
    }

    fun getCompleteMessage(): String {
        if (findErrorCode().isNotEmpty()) return getErrorMessage() + " Code: " + findErrorCode()
        return getErrorMessage()
    }
}