package com.example.international_business_men.repository.api

import com.example.international_business_men.repository.models.Rate
import com.example.international_business_men.repository.models.Transaction
import retrofit2.Call;
import retrofit2.http.GET;


interface TransactionsServices {

    @GET("/rates")
    fun getRates(): Call<List<Rate>>

    @GET("/transactions")
    fun getTransactions(): Call<List<Transaction>>
}