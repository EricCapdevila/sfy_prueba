package com.example.international_business_men.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.international_business_men.repository.Repository
import com.example.international_business_men.repository.models.ErrorModel
import com.example.international_business_men.repository.models.Rate
import com.example.international_business_men.repository.models.Transaction

class TransactionsViewModel(val repository: Repository) : ViewModel(){

    var dataHandler = MutableLiveData<DataHandler>()
    var errorHandler = MutableLiveData<ErrorHandler>()

    private lateinit var ratesObserver : Observer<List<Rate>>
    private lateinit var transactionsObserver : Observer<List<Transaction>>
    private lateinit var errorObserver : Observer<ErrorModel>


    fun getData(){
        repository.run{
            getRates()
            getTransactions()
            observeRestData()
        }
    }

    private fun observeRestData(){
        ratesObserver = ratesObserverLambda()
        repository.rates.observeForever(ratesObserver)

        transactionsObserver = transactionsObserverLambda()
        repository.transactions.observeForever(transactionsObserver)

        errorObserver = errorObserverLambda()
        repository.error.observeForever(errorObserver)
    }

    private val  ratesObserverLambda : () -> Observer<List<Rate>> = {
        Observer {
            repository.rates.removeObserver(ratesObserver)
            repository.rates.value = it
            checkAndSetDataHandler()
        }
    }

    private val  transactionsObserverLambda : () -> Observer<List<Transaction>> = {
        Observer {
            repository.transactions.removeObserver(transactionsObserver)
            repository.transactions.value = it
            checkAndSetDataHandler()
        }
    }

    private val  errorObserverLambda : () -> Observer<ErrorModel> = {
        Observer {
            repository.error.value = it
            errorHandler.value = ErrorHandler(it)
        }
    }

    private val checkAndSetDataHandler = {
        repository.run{
            transactions.value?.let{ transactions ->
                rates.value?.let{ rates ->
                    clearObservers()
                    dataHandler.value = DataHandler(transactions, rates)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        clearObservers()
    }

    private fun clearObservers(){
        repository.run{
            rates.removeObserver(ratesObserver)
            transactions.removeObserver(transactionsObserver)
            error.removeObserver(errorObserver)
        }
    }

}