package com.forward.appgestion.ui.specificRegisterDetailList

import android.app.DatePickerDialog
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import coil.api.load

import com.forward.appgestion.R
import com.forward.appgestion.data.model.SpecificRegister.SpecificRegisterDetail
import com.forward.appgestion.data.repository.LoginRepository
import com.forward.appgestion.domain.LoginDataSource
import com.forward.appgestion.domain.Result
import com.forward.appgestion.ui.Constants
import com.forward.appgestion.ui.TopSpacingItemDecoration
import com.forward.appgestion.ui.specificRegisterList.SpecificRegisterViewModel
import com.forward.appgestion.ui.specificRegisterList.SpecificRegisterViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.specific_register__detail_fragment.*
import kotlinx.android.synthetic.main.specific_register__detail_fragment.button
import kotlinx.android.synthetic.main.specific_register__detail_fragment.conclusiones_input
import kotlinx.android.synthetic.main.specific_register__detail_fragment.descripcion_input
import kotlinx.android.synthetic.main.specific_register__detail_fragment.f_mant_input
import kotlinx.android.synthetic.main.specific_register__detail_fragment.f_prox_mant_input
import kotlinx.android.synthetic.main.specific_register__detail_fragment.img_antes_input
import kotlinx.android.synthetic.main.specific_register__detail_fragment.img_despues_input
import kotlinx.android.synthetic.main.specific_register__detail_fragment.listView
import kotlinx.android.synthetic.main.specific_register__detail_fragment.observaciones_input
import kotlinx.android.synthetic.main.specific_register__detail_fragment.pb_progress
import kotlinx.android.synthetic.main.specific_register__detail_fragment.sr_container
import kotlinx.android.synthetic.main.specific_register__detail_fragment.swLayout
import kotlinx.android.synthetic.main.specific_register__detail_fragment.tag_input
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class SpecificRegisterDetailFragment : Fragment() {


    companion object {
        fun newInstance() = SpecificRegisterDetailFragment()
    }
    private  lateinit var specificRegisterDetail: SpecificRegisterDetail
    private lateinit var viewModel: SpecificRegisterDetailViewModel
    private lateinit var tags: Array<String>
    private var statusLoading: Int = 0
    private var detail_id: Int = 0
    private var detail_status: Int = 0
    private var machine: Int = 0
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
            detail_status= bundle.getInt("FRAGMENT_STATUS")
            machine= bundle.getInt("machine")
            detail_id= bundle.getInt("id")
        }

        viewModel =
            ViewModelProviders.of(this, SpecificRegisterDetailViewModelFactory(activity!!.application))
                .get(SpecificRegisterDetailViewModel::class.java)

        return inflater.inflate(R.layout.specific_register__detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        getLoadingStatus()



        println("status"+detail_status+"|"+detail_id+"|"+statusLoading)
        when (detail_status){
            Constants.FRAGMENT_CREATE->{
                button.text = "CREAR REGISTRO"
            }
            Constants.FRAGMENT_EDIT->{
                button.text = "EDITAR REGISTRO"
                getData(statusLoading,detail_id)
                // refresh your list contents somehow

                swLayout.setOnRefreshListener {
                    getData(statusLoading,detail_id)
                    swLayout.isRefreshing = false
                }

            }
            Constants.FRAGMENT_VIEW->{
                button.visibility= View.GONE
                getData(statusLoading,detail_id)
                swLayout.setOnRefreshListener {
                    getData(statusLoading,detail_id)
                    swLayout.isRefreshing = false
                }
                tag_input.isClickable= false
                tag_input.isEnabled= false

                listView.isFocusable=false
                observaciones_input.isFocusable=false
                observaciones_input.isActivated=false
                conclusiones_input.isFocusable=false
                descripcion_input.isFocusable=false
                f_prox_mant_input.isEnabled=false
                f_mant_input.isEnabled=false
                editText.visibility= View.GONE
                add_list_item.visibility= View.GONE

            }
        }
        configureView()
    }
    private  fun configureView(){
        f_mant_input.setOnClickListener {
            val newFragment = DatePickerFragment(f_mant_input)
            newFragment.show(childFragmentManager,"datePicker")

        }
        f_prox_mant_input.setOnClickListener {
            val newFragment = DatePickerFragment(f_prox_mant_input)
            newFragment.show(childFragmentManager,"datePicker")

        }
        viewModel.getTags(machine.toString())
        viewModel.getTagsData().observe(this, Observer {
            if (it != null) {
                tags = it

            }
            var dropAdapter= ArrayAdapter(activity!!.applicationContext!!,R.layout.spinnerresource,
                tags)
            tag_input.adapter=dropAdapter
//            var a=tag_input.adapter
//            a.ge
            tag_input.onItemSelectedListener= object:
                AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }


                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                }

            }
            viewModel.getSpecifiRegisterDetailData().observe(this, Observer {
                if (specificRegisterDetail.register!!.tag!= null) {
                    val spinnerPosition = dropAdapter.getPosition(specificRegisterDetail.register!!.tag)

                   tag_input.setSelection(spinnerPosition)

                }
            })
        })


    }

    private fun getData(status:Int,id:Int) {
        viewModel.getData(status,detail_id)

        viewModel.getSpecifiRegisterDetailData().observe(this, Observer {
            if (it != null) {
                Log.d("cuack3","response: ${it}")
                specificRegisterDetail= it
                f_mant_input.setText(specificRegisterDetail!!.register!!.fechaMantenimiento)
                f_prox_mant_input.setText(specificRegisterDetail!!.register!!.fechaProxMantenimiento)
                descripcion_input.setText(specificRegisterDetail!!.register!!.descripcion)
//                tag_input.setSelection(tag_input.adapter .getItem(specificRegisterDetail!!.register!!.tag!!))
                var arr = ArrayList<String>()
                var arrAd : ArrayAdapter<String>

                var a= specificRegisterDetail!!.register!!.trabajosRealizados!!.split('|')
                for(i in a){
                    arr.add(i)

                }
                arrAd= ArrayAdapter(context!!,R.layout.spinnerresource,arr)
                listView.adapter= arrAd
                if(detail_status==Constants.FRAGMENT_EDIT) {
                    listView.onItemLongClickListener =
                        AdapterView.OnItemLongClickListener { parent, view, position, id ->
                            MaterialAlertDialogBuilder(requireContext())
                                .setMessage("Seguro que desea eliminar")
                                .setNegativeButton(getString(R.string.alert_dialog_cancel), null)
                                .setPositiveButton("ACEPTAR") { _, _ ->
                                    arr.removeAt(position)
                                    arrAd.notifyDataSetChanged()

                                }
                                .show()
                            false
                        }

                }
                add_list_item.setOnClickListener{
                    val txt=editText.text.toString()
                    if(txt!="" && arr.size < 10 ) {
                        arr.add(txt)
                        arrAd.notifyDataSetChanged()
                        editText.setText("")
                    }

                }

                conclusiones_input.setText(specificRegisterDetail!!.register!!.sugerencias)
                observaciones_input.setText(specificRegisterDetail!!.register!!.observaciones)
                img_antes_input.load("https://chanchado-files.s3-sa-east-1.amazonaws.com/"+specificRegisterDetail!!.register!!.imagenAntes)
                img_despues_input.load("https://chanchado-files.s3-sa-east-1.amazonaws.com/"+specificRegisterDetail!!.register!!.imagenDespues)
        //        trabajos_realizados_input.setText(specificRegisterDetail!!.register!!.trabajosRealizados)
            }
        })

    }
    private fun getLoadingStatus() {
        viewModel.getLoadingStatus().observe(this, Observer {
            when (it) {
                Constants.STATUS_COMPLETE -> {
                    pb_progress.visibility = View.GONE
                    statusLoading = Constants.STATUS_COMPLETE
                    main_content.visibility = View.VISIBLE
                }
                Constants.STATUS_LOADING -> {
                    main_content.visibility = View.GONE
                    pb_progress.visibility = View.VISIBLE
                    statusLoading = Constants.STATUS_LOADING
                }
                Constants.STATUS_ERROR -> {
                    pb_progress.visibility = View.GONE
                    main_content.visibility = View.GONE
                    statusLoading = Constants.STATUS_ERROR
                    showSnackBar("¡Al parecer algo salió mal!")
                }
            }
        })
    }


    private fun showSnackBar(message: String) {
        Snackbar
            .make(sr_container, message, Snackbar.LENGTH_INDEFINITE)
            .setTextColor(ContextCompat.getColor(requireContext(), R.color.white_trafic))
            .setAction("Volver intentarlo") {
                viewModel.getData(Constants.STATUS_ERROR,1)
            }
            .show()
    }

}
