package com.forward.appgestion.ui.specificRegisterList

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.forward.appgestion.data.repository.SpecificRegisterRepository
import com.forward.appgestion.domain.SpecificRegisterDataSource


class SpecificRegisterViewModelFactory (private val application: Application) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SpecificRegisterViewModel::class.java)) {
            return SpecificRegisterViewModel(
                specificRegisterRepository = SpecificRegisterRepository(
                    dataSource = SpecificRegisterDataSource()
                ),
                application = application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}