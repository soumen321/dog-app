package com.dogmvvm.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.dogmvvm.utility.Resource
import com.dogmvvm.domain.model.Dog

interface DogViewModelContract {
    fun onGetDogImage()
    fun doObserveGogImage(): LiveData<Resource<Dog>>
}