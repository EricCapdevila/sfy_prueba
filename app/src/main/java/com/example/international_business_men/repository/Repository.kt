package com.example.international_business_men.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.international_business_men.repository.api.TransactionsServices
import com.example.international_business_men.repository.models.Rate
import com.example.international_business_men.repository.models.Transaction
import com.example.international_business_men.utils.Constants.BASE_URL
import com.example.international_business_men.utils.Constants.SERVICE_LOGS
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Repository() {

    private var transactionsServices : TransactionsServices

    lateinit var rates : MutableLiveData<List<Rate>>
    lateinit var transactions : MutableLiveData<List<Transaction>>

    init {

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        transactionsServices = retrofit.create(TransactionsServices::class.java)

    }

    fun getRates(){
        transactionsServices.getRates()?.enqueue(object : Callback<List<Rate>?> {
            override fun onFailure(call: Call<List<Rate>?>, t: Throwable) {
                showErrorInLog(t.message)
            }
            override fun onResponse(call: Call<List<Rate>?>, response: Response<List<Rate>?>) {
              response.body()?.let{ rates.postValue(it)} ?: Log.d(SERVICE_LOGS, "onResponse:  body is " + response.body())
            }
        })
    }

    fun getTransactions(){
        transactionsServices.getTransactions()?.enqueue(object : Callback<List<Transaction>?> {
            override fun onFailure(call: Call<List<Transaction>?>, t: Throwable) {
                showErrorInLog(t.message)
            }

            override fun onResponse(call: Call<List<Transaction>?>, response: Response<List<Transaction>?>) {
                response.body()?.let { transactions.postValue(it)}
                        ?: Log.d(SERVICE_LOGS, "onResponse:  body is " + response.body())
            }
        })
    }

    fun showErrorInLog(message : String?){
        var error = "No message to display"
        message?.let{ error = it}
        Log.d(SERVICE_LOGS, "onFailure: $error")
    }
}

