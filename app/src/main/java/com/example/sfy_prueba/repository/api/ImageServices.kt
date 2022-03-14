package com.example.sfy_prueba.repository.api

import com.example.sfy_prueba.repository.models.Photo
import retrofit2.Call;
import retrofit2.http.GET;


interface ImageServices {

    @GET("/photos")
    fun getPhotos(): Call<List<Photo>>

}