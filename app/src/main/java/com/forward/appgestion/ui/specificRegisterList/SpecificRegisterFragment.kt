package com.forward.appgestion.ui.specificRegisterList

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.forward.appgestion.R
import com.forward.appgestion.data.model.SpecificRegister.SpecificRegisterList
import com.forward.appgestion.ui.Constants
import com.forward.appgestion.ui.TopSpacingItemDecoration
import com.forward.appgestion.ui.specificRegisterDetailList.SpecificRegisterDetailFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.specific_register_fragment.*

class SpecificRegisterFragment : Fragment() {
    lateinit var navController: NavController
    private lateinit var specificRegisterListAdapter: SpecificRegisterAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var searchView: SearchView
    private lateinit var viewModel: SpecificRegisterViewModel
    private var statusLoading: Int = 0
    private var machine = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = this.arguments
        if (bundle != null) {
            machine= bundle.getInt("id")
        }


        viewModel =
            ViewModelProviders.of(this, SpecificRegisterViewModelFactory(activity!!.application))
                .get(SpecificRegisterViewModel::class.java)
        return inflater.inflate(R.layout.specific_register_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val topSpacingDecorator = TopSpacingItemDecoration(5)
        val bundle=Bundle()
        navController= Navigation.findNavController(view)
        rec_list_fragment.addItemDecoration(topSpacingDecorator)
        getLoadingStatus()
        println("status"+machine)
        getData(statusLoading,machine)
        create_new_item.setOnClickListener {

            bundle!!.putInt("FRAGMENT_STATUS",Constants.FRAGMENT_CREATE)
            bundle!!.putInt("machine",machine)
            navController.navigate(R.id.specificRegisterDetailFragment,bundle)
        }
        // refresh your list contents somehow
        swLayout.setOnRefreshListener {
            getData(statusLoading,machine)
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


    private fun getData(status:Int,machine:Int) {
        viewModel.getData(status,machine)
        viewModel.getSpecifiRegisterListData().observe(this, Observer {
            if (it != null) {
                Log.d("cuack2","getSpecifiRegisterListData: $it")
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
            SpecificRegisterAdapter(requireContext(), specificRegisterList,navController,machine)
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
