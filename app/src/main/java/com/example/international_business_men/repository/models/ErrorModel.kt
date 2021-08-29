package com.example.international_business_men.repository.models

import okhttp3.ResponseBody

class ErrorModel(
    var t : Throwable?,
    var errorResponse : ResponseBody?
)

