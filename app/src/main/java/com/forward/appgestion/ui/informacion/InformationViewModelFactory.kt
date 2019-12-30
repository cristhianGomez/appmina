package com.forward.appgestion.ui.informacion

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.forward.appgestion.data.repository.InformationRepository
import com.forward.appgestion.domain.InformationDataSource

class InformationViewModelFactory (private val application: Application) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InformacionViewModel::class.java)) {
            return InformacionViewModel(
                informationRepository = InformationRepository(
                    dataSource = InformationDataSource()
                ),
                application = application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}