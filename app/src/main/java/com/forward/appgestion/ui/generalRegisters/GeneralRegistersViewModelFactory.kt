package com.forward.appgestion.ui.generalRegisters

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.forward.appgestion.data.repository.GeneralRegistersRepository
import com.forward.appgestion.domain.GeneralRegistersDataSource

class GeneralRegistersViewModelFactory(private val application: Application): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GeneralRegistersViewModel::class.java)) {
            return GeneralRegistersViewModel(
                generalRegistersRepository = GeneralRegistersRepository(
                    dataSource = GeneralRegistersDataSource()
                ),
                application = application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}