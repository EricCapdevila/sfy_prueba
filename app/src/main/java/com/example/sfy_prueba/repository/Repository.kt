package com.example.sfy_prueba.repository

import androidx.lifecycle.MutableLiveData
import com.example.sfy_prueba.repository.api.ImageServices
import com.example.sfy_prueba.repository.models.ErrorModel
import com.example.sfy_prueba.repository.models.Photo
import com.example.sfy_prueba.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Repository() {

    private lateinit var imageServices: ImageServices
    private lateinit var retrofit: Retrofit

    var photos = MutableLiveData<List<Photo>>()
    var error = MutableLiveData<ErrorModel>()

    init {
        setUpRetrofit()
    }

    private fun setUpRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor { chain ->

                val original: Request = chain.request()
                val originalHttpUrl = original.url()
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("client_id", "90ivsTBMw0anornf04Ru8zY4Ky57Iif_bpKyIDPjYZI")
                    .build()
                // Request customization: add request headers
                val requestBuilder = original.newBuilder().url(url)
                 chain.proceed(requestBuilder.build())

            }.build()).build()
        imageServices = retrofit.create(ImageServices::class.java)

    }

    fun getPhotos() {
        imageServices.getPhotos().enqueue(object : Callback<List<Photo>> {
            override fun onFailure(call: Call<List<Photo>?>, t: Throwable) {
                error.postValue(ErrorModel(t, null))
            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                response.body()?.let { photos.value = it }
                    ?: error.postValue(ErrorModel(null, response.errorBody()))
            }
        })
    }


}

