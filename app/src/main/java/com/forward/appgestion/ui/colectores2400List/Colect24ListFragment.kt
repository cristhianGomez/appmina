package com.forward.appgestion.ui.colectores2400List

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.forward.appgestion.R

class Colect24ListFragment : Fragment() {

    companion object {
        fun newInstance() = Colect24ListFragment()
    }

    private lateinit var viewModel: Colect24ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.colect24_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(Colect24ListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
