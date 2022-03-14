package com.example.sfy_prueba.repository.models

import okhttp3.ResponseBody

class ErrorModel(
    var t: Throwable?,
    var errorResponse: ResponseBody?
)