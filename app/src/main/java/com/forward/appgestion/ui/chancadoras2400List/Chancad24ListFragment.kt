package com.forward.appgestion.ui.chancadoras2400List

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.forward.appgestion.R

class Chancad24ListFragment : Fragment() {

    companion object {
        fun newInstance() = Chancad24ListFragment()
    }

    private lateinit var viewModel: Chancad24ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chancad24_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(Chancad24ListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
