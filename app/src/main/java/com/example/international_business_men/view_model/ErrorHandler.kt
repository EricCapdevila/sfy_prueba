package com.example.international_business_men.view_model

import retrofit2.HttpException

class ErrorHandler (val t : Throwable) {

    var errorCode : Int
    var message : String = "Unknown Error"

    init {
        t.message?.let{ message = it }
        errorCode = findErrorCode()
    }

    private fun findErrorCode() : Int {
        if(t is HttpException){ return t.code() }
        return 0
    }
}