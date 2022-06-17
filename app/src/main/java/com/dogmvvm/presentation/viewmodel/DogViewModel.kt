package com.dogmvvm.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogmvvm.commons.Resource
import com.dogmvvm.domain.model.Dog
import com.dogmvvm.domain.usecase.UseCaseDog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(private val useCaseDog: UseCaseDog) : ViewModel(),DogViewModelContract {

    private val dogLiveData = MutableLiveData<Resource<Dog>>()

    /* call for initial image load */
    init {
        onGetDogImage()
    }

    override fun onGetDogImage(){
        dogLiveData.postValue(Resource.Loading())

        viewModelScope.launch {
            dogLiveData.postValue(useCaseDog())
        }
    }

    /* Exposed to View*/
    override fun doObserveGogImage(): LiveData<Resource<Dog>> = dogLiveData

}

