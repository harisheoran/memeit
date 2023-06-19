package com.example.memeit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memeit.network.Api
import com.example.memeit.network.Meme
import kotlinx.coroutines.launch

enum class ApiStatus {
    LOADING,
    SUCCESS,
    ERROR
}

class MainViewModel : ViewModel() {
    // internal mutable LiveData which store the status value
    private val _status = MutableLiveData<ApiStatus>()

    // external mutable LiveData for request status  || exposing public immutable
    val status: LiveData<ApiStatus> = _status

    private val _photos = MutableLiveData<List<Meme>>()
    val photos: LiveData<List<Meme>> = _photos

    private val _memeDetails = MutableLiveData<Meme>()
    val memeDetails: LiveData<Meme> = _memeDetails

    fun setMemeDetails(meme: Meme) {
        _memeDetails.value = meme
    }


    init {
        getMemesFromApi()
    }

    private fun getMemesFromApi() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                // call the method from retrofit service
                val response = Api.retrofitService.getMemes()
                if (response.success) {
                    _photos.value = response.data.memes
                    _status.value = ApiStatus.SUCCESS
                } else {
                    _status.value = ApiStatus.ERROR
                    _photos.value = emptyList()
                }
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }
}