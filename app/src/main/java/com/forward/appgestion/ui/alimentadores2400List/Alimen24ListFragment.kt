package com.forward.appgestion.ui.alimentadores2400List

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.forward.appgestion.R

class Alimen24ListFragment : Fragment() {

    companion object {
        fun newInstance() = Alimen24ListFragment()
    }

    private lateinit var viewModel: Alimen24ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.alimen24_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(Alimen24ListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
