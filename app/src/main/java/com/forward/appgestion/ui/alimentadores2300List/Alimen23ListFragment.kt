package com.forward.appgestion.ui.alimentadores2300List

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.forward.appgestion.R
import com.forward.appgestion.data.model.SpecificRegister.SpecificRegisterList
import com.forward.appgestion.ui.Constants
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.alimen23_list_fragment.*

class Alimen23ListFragment : Fragment() {

    private lateinit var specificRegisterListAdapter: SpecificRegisterListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var searchView: SearchView
    private lateinit var viewModel: Alimen23ListViewModel
    private var statusLoading: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProviders.of(this, SpecificRegisterViewModelFactory(activity!!.application)).get(Alimen23ListViewModel::class.java)
        return inflater.inflate(R.layout.alimen23_list_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getLoadingStatus()
        getData(statusLoading)

        // refresh your list contents somehow
        swLayout.setOnRefreshListener {
            getData(statusLoading)
            swLayout.isRefreshing = false
        }
    }

    private fun getLoadingStatus() {
        viewModel.getLoadingStatus().observe(this, Observer {
            when (it) {
                Constants.STATUS_COMPLETE -> {
                    pb_progress.visibility = View.GONE
                    statusLoading = Constants.STATUS_COMPLETE
                }
                Constants.STATUS_LOADING -> {
                    pb_progress.visibility = View.VISIBLE
                    statusLoading = Constants.STATUS_LOADING
                }
                Constants.STATUS_ERROR -> {
                    pb_progress.visibility = View.GONE
                    statusLoading = Constants.STATUS_ERROR
                    showSnackBar("¡Al parecer algo salió mal!")
                }
            }
        })
    }


    private fun getData(status:Int) {
        viewModel.getData(status,1)
        viewModel.getSpecifiRegisterListData().observe(this, Observer {
            if (it != null) {
                setRecyclerView(it)
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
//        menu.findItem(R.id.action_settings).isVisible = false
        menu.findItem(R.id.action_filter).isVisible = true
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
//        searchView.setQuery("aeaea",true)
        searchView.setOnClickListener{
            searchView.isIconified = false
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (statusLoading == 1)
                    specificRegisterListAdapter.filter.filter(query)
                return false
            }
        })
    }
    private fun setRecyclerView(specificRegisterList: SpecificRegisterList) {
        val listAdapter =
            SpecificRegisterListAdapter(requireContext(), specificRegisterList)
        viewManager = LinearLayoutManager(requireContext())
        rec_list_fragment.apply {
            layoutManager = viewManager
            specificRegisterListAdapter = listAdapter
            adapter = specificRegisterListAdapter
        }
        rec_list_fragment.setHasFixedSize(true)
    }

    private fun showSnackBar(message: String) {
        Snackbar
            .make(cl_container, message, Snackbar.LENGTH_INDEFINITE)
            .setTextColor(ContextCompat.getColor(requireContext(), R.color.white_trafic))
            .setAction("Volver intentarlo") {
                viewModel.getData(Constants.STATUS_ERROR,1)
            }
            .show()
    }
}
