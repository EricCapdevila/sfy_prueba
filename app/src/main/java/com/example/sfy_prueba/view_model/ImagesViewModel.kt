package com.example.sfy_prueba.view_model

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.sfy_prueba.repository.Repository
import com.example.sfy_prueba.repository.models.ErrorModel
import com.example.sfy_prueba.repository.models.Photo

class ImagesViewModel(val repository: Repository) : ViewModel() {

    var photos = MutableLiveData<List<Photo>>()
    var errorHandler = MutableLiveData<ErrorHandler>()

    private lateinit var photosObserver: Observer<List<Photo>>
    private lateinit var errorObserver: Observer<ErrorModel>

    fun getData(owner: LifecycleOwner) {
        repository.run {
            getPhotos()
            observeRestData(owner)
        }
    }

    private fun observeRestData(owner: LifecycleOwner) {
        photosObserver =  photosObserverLambda()
        repository.photos.observe(owner, photosObserver)
        errorObserver = errorObserverLambda()
        repository.error.observe(owner, errorObserver)
    }

    private val photosObserverLambda: () -> Observer<List<Photo>> = {
        Observer {
            repository.photos.removeObserver(photosObserver)
            photos.value = it
        }
    }

    private val errorObserverLambda: () -> Observer<ErrorModel> = {
        Observer {
            repository.error.removeObserver(errorObserver)
            errorHandler.value =
                ErrorHandler(it)
        }
    }


    override fun onCleared() {
        super.onCleared()
        clearObservers()
    }

    private fun clearObservers() {
        repository.run {
            photos.removeObserver(photosObserver)
            error.removeObserver(errorObserver)
        }
    }

}