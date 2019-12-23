package com.forward.appgestion.ui.specificRegisterDetailList

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.forward.appgestion.data.model.SpecificRegister.SpecificRegisterDetail
import com.forward.appgestion.data.repository.SpecificRegisterRepository
import com.forward.appgestion.ui.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SpecificRegisterDetailViewModel(private val specificRegisterRepository: SpecificRegisterRepository,
                                      private val application: Application
) : ViewModel() {
    private val loadStatus = MutableLiveData(0)
    private val specificRegisterDetail= MutableLiveData<SpecificRegisterDetail>()
    private val tagsData= MutableLiveData<Array<String>>()


    fun getTags(aux:String) {
        var tags: Array<String> = arrayOf("No Data")
        when (aux) {
            "1" -> {
                tags = arrayOf(
                    "2300-FE-005",
                    "2300-FE-006",
                    "2300-FE-007",
                    "2300-FE-008"
                )
            }
            "2" -> {
                tags = arrayOf(
                    "2300-CV-001", "2300-CV-003"
                )
            }
            "3" -> {
                tags = arrayOf(
                    "2400-FE-001", "2400-FE-002"
                )
            }
            "4" -> {
                tags = arrayOf(
                    "2400-CV-001",
                    "2400-CV-002",
                    "2400-CV-003",
                    "2400-CV-005"
                )
            }
            "5" -> {
                tags = arrayOf(
                    "2400-CR-001", "2400-CR-002"
                )
            }
            "6" -> {
                tags = arrayOf(

                    "2400-DC-001",
                    "2400-DC-002",
                    "2400-DC-003",
                    "2400-DC-004",
                    "2400-DC-005",
                    "2400-DC-006"
                )
            }
            "7" -> {
                tags = arrayOf(
                    "2400-SC-001", "2400-SC-002"
                )
            }
            "8" -> {
                tags = arrayOf(
                    "2500-BF-001",
                    "2500-BF-002",
                    "2500-BF-003",
                    "2500-BF-004"
                )
            }
            "9" -> {
                tags = arrayOf(

                    "2500-CV-001",
                    "2500-CV-002",
                    "2500-CV-003",
                    "2500-CV-004",
                    "2500-CV-005",
                    "2500-CV-006"
                )
            }
            "10" -> {
                tags = arrayOf(
                    "2500-CR-001", "2500-CR-002"
                )
            }
            "11" -> {
                tags = arrayOf(

                    "2500-DC-003",
                    "2500-DC-004",
                    "2500-DC-005",
                    "2500-DC-006"
                )
            }
        }
        tagsData.postValue(tags)
    }


    fun getData(status: Int,id:Int) {
        if (status != Constants.STATUS_COMPLETE) loadStatus.value = Constants.STATUS_LOADING
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                try {
                    val data = specificRegisterRepository.getSpecificRegisterDetail(application,id)

                    specificRegisterDetail.postValue(data)
                    loadStatus.postValue(Constants.STATUS_COMPLETE)
                } catch (e: Exception) {
                    e.printStackTrace()
                    loadStatus.postValue(Constants.STATUS_ERROR)
                }
            }
        }
    }
    fun getSpecifiRegisterDetailData(): LiveData<SpecificRegisterDetail> = specificRegisterDetail
    fun getTagsData(): LiveData<Array<String>> = tagsData

    fun getLoadingStatus(): LiveData<Int> = loadStatus

}
