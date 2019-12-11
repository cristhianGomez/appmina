package com.forward.appgestion.ui.alimentadores2500List

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.forward.appgestion.R

class Alimen25ListFragment : Fragment() {

    companion object {
        fun newInstance() = Alimen25ListFragment()
    }

    private lateinit var viewModel: Alimen25ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.alimen25_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(Alimen25ListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
