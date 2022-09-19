package com.example.exhibitappcrop2cash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exhibitappcrop2cash.model.ExhibitModelResponse
import com.example.exhibitappcrop2cash.repository.ExhibitRepository
import com.example.exhibitappcrop2cash.repository.ExhibitRepositoryInterface
import com.example.exhibitappcrop2cash.util.ApiCallNetworkResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ExhibitViewModel @Inject constructor(private val exhibitRepository: ExhibitRepositoryInterface): ViewModel() {

    /** exhibit db LiveData **/
    private val _exhibitResponseLiveData: MutableLiveData<ApiCallNetworkResource<ExhibitModelResponse>> = MutableLiveData()
    val exhibitResponseLiveData: LiveData<ApiCallNetworkResource<ExhibitModelResponse>> = _exhibitResponseLiveData


    /**Handling Network Error for cocktail details ans save to livedata*/
    fun getExhibitList() {
        viewModelScope.launch {
            _exhibitResponseLiveData.postValue(ApiCallNetworkResource.Loading())
            try {
                delay(1)
                val response = exhibitRepository.getExhibit()
                if (response.isSuccessful) {
                    _exhibitResponseLiveData.postValue(
                        ApiCallNetworkResource.Success(
                            response.body()!!.toString(), response.body()
                        )
                    )
                } else {

                    _exhibitResponseLiveData.postValue(ApiCallNetworkResource.Error(response!!.message()))
                }

            } catch (e: Throwable) {
                e.printStackTrace()

            }
        }
    }

}