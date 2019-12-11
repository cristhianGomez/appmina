package com.forward.appgestion.ui.generalRegisters

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.forward.appgestion.R

class GeneralRegisterFragment : Fragment() {

    companion object {
        fun newInstance() = GeneralRegisterFragment()
    }

    private lateinit var viewModel: GeneralViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.general_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GeneralViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
