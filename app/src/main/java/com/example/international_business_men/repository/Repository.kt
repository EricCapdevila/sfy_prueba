package com.example.international_business_men.repository

import androidx.lifecycle.MutableLiveData
import com.example.international_business_men.repository.api.TransactionsServices
import com.example.international_business_men.repository.models.ErrorModel
import com.example.international_business_men.repository.models.Rate
import com.example.international_business_men.repository.models.Transaction
import com.example.international_business_men.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class Repository() {

    private var transactionsServices : TransactionsServices
    private var retrofit : Retrofit

    lateinit var rates : MutableLiveData<List<Rate>>
    lateinit var transactions : MutableLiveData<List<Transaction>>
    lateinit var error : MutableLiveData<ErrorModel>

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().addInterceptor { chain ->
                    val request: Request =
                        chain.request().newBuilder().addHeader("Accept", "application/json ").build()
                    chain.proceed(request)
                }.build()).build()
        transactionsServices = retrofit.create(TransactionsServices::class.java)
    }

    fun getRates(){
        transactionsServices.getRates().enqueue(object : Callback<List<Rate>> {
            override fun onFailure(call: Call<List<Rate>?>, t: Throwable) {
                error.postValue(ErrorModel(t, null))
            }
            override fun onResponse(call: Call<List<Rate>>, response: Response<List<Rate>>) {
              response.body()?.let{ rates.postValue(it) }
                  ?: error.postValue(ErrorModel(null, response.errorBody()))
            }
        })
    }

    fun getTransactions(){
        transactionsServices.getTransactions().enqueue(object : Callback<List<Transaction>> {
            override fun onFailure(call: Call<List<Transaction>>, t: Throwable) {
                error.postValue(ErrorModel(t, null))
            }

            override fun onResponse(call: Call<List<Transaction>>, response: Response<List<Transaction>>) {
                response.body()?.let { transactions.postValue(it)}
                    ?: error.postValue(ErrorModel(null, response.errorBody()))
            }
        })
    }

}

