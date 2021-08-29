package com.example.international_business_men.view_model

import com.example.international_business_men.repository.models.ErrorModel
import com.google.gson.JsonElement
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response


class ErrorHandler (private val errorModel : ErrorModel) {

    var errorCode : Int
    var message : String

    init {
        message = getErrorMessage()
        errorCode = findErrorCode()
    }

    private fun findErrorCode() : Int {
       errorModel.t?.let{
           if(it is HttpException){ return it.code() }
       }
        return 0
    }

    private fun getErrorResponseMessage(response : ResponseBody) : String {
       return JSONObject(response.toString()).getString("error")
    }

    private fun getErrorMessage() : String {
        errorModel.t?.message?.let{ return it }
        errorModel.errorResponse?.let{ return getErrorResponseMessage(it) }
        return "Unknown Error"
    }
}