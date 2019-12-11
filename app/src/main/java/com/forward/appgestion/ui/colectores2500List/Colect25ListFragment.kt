package com.forward.appgestion.ui.colectores2500List

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.forward.appgestion.R

class Colect25ListFragment : Fragment() {

    companion object {
        fun newInstance() = Colect25ListFragment()
    }

    private lateinit var viewModel: Colect25ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.colect25_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(Colect25ListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
