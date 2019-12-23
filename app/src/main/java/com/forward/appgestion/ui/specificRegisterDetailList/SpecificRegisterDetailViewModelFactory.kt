package com.forward.appgestion.ui.specificRegisterDetailList

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.forward.appgestion.data.model.SpecificRegister.SpecificRegisterDetail
import com.forward.appgestion.data.repository.SpecificRegisterRepository
import com.forward.appgestion.domain.SpecificRegisterDataSource


class SpecificRegisterDetailViewModelFactory (private val application: Application) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SpecificRegisterDetailViewModel::class.java)) {
            return SpecificRegisterDetailViewModel(
                specificRegisterRepository = SpecificRegisterRepository(
                    dataSource = SpecificRegisterDataSource()
                ),
                application = application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}