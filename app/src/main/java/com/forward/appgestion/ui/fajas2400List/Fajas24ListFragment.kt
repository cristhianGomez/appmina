package com.forward.appgestion.ui.fajas2400List

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.forward.appgestion.R

class Fajas24ListFragment : Fragment() {

    companion object {
        fun newInstance() = Fajas24ListFragment()
    }

    private lateinit var viewModel: Fajas24ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fajas24_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(Fajas24ListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
