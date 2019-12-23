package com.forward.appgestion.ui.specificRegisterList

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.forward.appgestion.data.model.SpecificRegister.SpecificRegisterDetail
import com.forward.appgestion.data.model.SpecificRegister.SpecificRegisterList
import com.forward.appgestion.data.repository.SpecificRegisterRepository
import com.forward.appgestion.ui.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SpecificRegisterViewModel(private val specificRegisterRepository: SpecificRegisterRepository,
                                private val application: Application
) : ViewModel() {

    //    private val viewModelJob = Job()
    private val specificRegisterList = MutableLiveData<SpecificRegisterList>()
    private val loadStatus = MutableLiveData(0)


    fun getData(status: Int,machine:Int) {
        if (status != Constants.STATUS_COMPLETE) loadStatus.value = Constants.STATUS_LOADING
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                try {
                    val data = specificRegisterRepository.getSpecificRegister(application,machine)

                    specificRegisterList.postValue(data)
                    loadStatus.postValue(Constants.STATUS_COMPLETE)
                } catch (e: Exception) {
                    e.printStackTrace()
                    loadStatus.postValue(Constants.STATUS_ERROR)
                }
            }
        }
    }


    fun getSpecifiRegisterListData(): LiveData<SpecificRegisterList> = specificRegisterList

    fun getLoadingStatus(): LiveData<Int> = loadStatus
}



