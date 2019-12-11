package com.forward.appgestion.ui.fajas2300List

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.forward.appgestion.R

class Fajas23ListFragment : Fragment() {

    companion object {
        fun newInstance() = Fajas23ListFragment()
    }

    private lateinit var viewModel: Fajas23ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fajas23_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(Fajas23ListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
