package com.example.international_business_men.view_model

import com.example.international_business_men.repository.models.ErrorModel
import com.google.gson.JsonElement
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response


class ErrorHandler (private val errorModel : ErrorModel) {


    private fun findErrorCode() : String {
       errorModel.t?.let{
           if(it is HttpException){ return it.code().toString()}
       }
        return ""
    }

    private fun getErrorResponseMessage(response : ResponseBody) : String {
       return JSONObject(response.toString()).getString("error")
    }

    private fun getErrorMessage() : String {
        errorModel.t?.message?.let{ return it }
        errorModel.errorResponse?.let{ return getErrorResponseMessage(it) }
        return "Unknown Error"
    }

    fun getCompleteMessage() : String {
        if(findErrorCode().isNotEmpty()) return getErrorMessage() + " Code: " + findErrorCode()
        return getErrorMessage()
    }
}