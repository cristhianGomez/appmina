package com.forward.appgestion.ui.alimentadores2300List

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.forward.appgestion.data.repository.SpecificRegisterRepository
import com.forward.appgestion.domain.SpecificRegisterDataSource


class SpecificRegisterViewModelFactory (private val application: Application) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Alimen23ListViewModel::class.java)) {
            return Alimen23ListViewModel(
                specificRegisterRepository = SpecificRegisterRepository(
                    dataSource = SpecificRegisterDataSource()
                ),
                application = application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}