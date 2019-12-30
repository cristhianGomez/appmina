package com.forward.appgestion.ui.informacion

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.forward.appgestion.data.model.InformationModel
import com.forward.appgestion.data.repository.InformationRepository
import com.forward.appgestion.ui.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InformacionViewModel (private val informationRepository: InformationRepository,
                            private val application: Application
) : ViewModel() {

    private val informationModelList = MutableLiveData<ArrayList<String>>()
    private val loadStatus = MutableLiveData(0)

//    val str = "informacion\/ÁREA 2400\/REPORTE DE CHANCADORAS MP1250 DEL 2 DE MARZO DEL 2019-1.pdf"
    fun getData(status: Int) {
        if (status != Constants.STATUS_COMPLETE) loadStatus.value = Constants.STATUS_LOADING
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                try {
                    val data = informationRepository.getInformation(application)
                    informationModelList.postValue(data)
                    loadStatus.postValue(Constants.STATUS_COMPLETE)
                } catch (e: Exception) {
                    e.printStackTrace()
                    loadStatus.postValue(Constants.STATUS_ERROR)
                }
            }
        }
    }


    fun getInformationModel(): LiveData<ArrayList<String>> = informationModelList

    fun getLoadingStatus(): LiveData<Int> = loadStatus
}