package com.forward.appgestion.ui.generalRegisters

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.forward.appgestion.data.model.GeneralRegistersListModel
import com.forward.appgestion.data.repository.GeneralRegistersRepository
import com.forward.appgestion.ui.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GeneralRegistersViewModel(private val generalRegistersRepository : GeneralRegistersRepository,
                                private val application: Application
) : ViewModel() {
    private val generalRegisterListModel = MutableLiveData<GeneralRegistersListModel>()
    private val loadStatus = MutableLiveData(0)

    fun getData(status: Int,machine:Int) {
        if (status != Constants.STATUS_COMPLETE) loadStatus.value = Constants.STATUS_LOADING
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                try {
                    val data = generalRegistersRepository.getGeneralRegisters(application)
                    Log.d("cuack2","response: ${data!!}")

                    generalRegisterListModel.postValue(data)
                    loadStatus.postValue(Constants.STATUS_COMPLETE)
                } catch (e: Exception) {
                    e.printStackTrace()
                    loadStatus.postValue(Constants.STATUS_ERROR)
                }
            }
        }
    }

    fun getGeneralRegisterListData(): LiveData<GeneralRegistersListModel> = generalRegisterListModel

    fun getLoadingStatus(): LiveData<Int> = loadStatus
}
