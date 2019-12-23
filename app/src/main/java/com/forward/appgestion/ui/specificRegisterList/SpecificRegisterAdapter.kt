package com.forward.appgestion.ui.specificRegisterList

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast

import android.app.Application
import android.os.Bundle
import android.provider.SyncStateContract
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com .forward.appgestion.R
import com.forward.appgestion.data.model.SpecificRegister.Data
import com.forward.appgestion.data.model.SpecificRegister.SpecificRegisterList
import com.forward.appgestion.ui.Constants
import com.forward.appgestion.ui.specificRegisterDetailList.SpecificRegisterDetailFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.specific_item_list.view.*
import kotlinx.coroutines.withContext


@Suppress("UNCHECKED_CAST")
class SpecificRegisterAdapter(context: Context, specificRegisterList: SpecificRegisterList,navController: NavController,machine:Int) :
RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private val TAG: String = "AppDebug"
    private val activity = context
    private val navController   = navController
    private val machine   = machine
    private var srListAll: List<Data> = specificRegisterList.data!!
    private var srListFilter: List<Data> = specificRegisterList.data!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ReceptionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.specific_item_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ReceptionViewHolder -> {
                holder.bind(srListFilter[position])
//                holder.itemView.setOnClickListener{
//                    Toast.makeText(activity,"clicked " + holder.itemId, Toast.LENGTH_SHORT).show()
////                    Log.d(TAG,"ITEM: "+ " : "+holder.reception_shipment.text)
////
////                    val textBundle = Bundle()
////                    textBundle.putString("nroReception",holder.reception_shipment.text.toString())
////                    findViewController.navigate(R.id.see_reception_order, textBundle)
////
//                    val bundle= Bundle()
//                    bundle.putInt("machine",machine)
//                    bundle.putInt("FRAGMENT_STATUS", Constants.FRAGMENT_VIEW)
//                    navController.navigate(R.id.specificRegisterDetailFragment,bundle)
//                }
            }
        }
    }

    override fun getItemCount(): Int {
        return srListFilter.size
    }

    fun setListData(specificRegisterList: List<Data>) {
        srListFilter = specificRegisterList
        notifyDataSetChanged()
    }

//    fun submitList(receptionList: List<EndPointDriverListModel>){
//        driversListAll = receptionList
//    }

    inner class ReceptionViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        private val tVDesc = itemView.lbl_title
        private val tVMant = itemView.lbl_mant_date
        private val tVNMant = itemView.lbl_next_mant_date
        private val tVAuthor = itemView.lbl_author
        private val imgEdit = itemView.img_edit
        private val imgDelete = itemView.img_delete
        private val item = itemView
        val sharedPref = activity.applicationContext.getSharedPreferences("credentials", Context.MODE_PRIVATE)
        val auth_id = sharedPref.getString("user_id", null)
        val bundle =Bundle()


        fun bind(specificRegisterItem: Data){
            bundle.putInt("machine",machine)
            item.setOnClickListener{
                bundle.putInt("FRAGMENT_STATUS", Constants.FRAGMENT_VIEW)
                bundle.putInt("id", specificRegisterItem.id!!)
                navController.navigate(R.id.specificRegisterDetailFragment,bundle)
            }
            tVDesc.text = specificRegisterItem.description
            tVMant.text = specificRegisterItem.maintenanceDate
            tVNMant.text = specificRegisterItem.nextMaintenanceDate
            tVAuthor.text = specificRegisterItem.userName
            bundle.putInt("id",specificRegisterItem.id!!)
            bundle.putInt("FRAGMENT_STATUS", Constants.FRAGMENT_EDIT)
//            if(auth_id==specificRegisterItem.userId!!.toString()) {
//                imgDelete.visibility= View.VISIBLE
//                imgEdit.visibility= View.VISIBLE
                imgEdit.setOnClickListener {
                    SpecificRegisterDetailFragment()
                    navController.navigate(R.id.specificRegisterDetailFragment,bundle)
                }
                imgDelete.setOnClickListener {
                    MaterialAlertDialogBuilder(activity)
                        .setMessage(activity.getString(R.string.alert_dialog_message_delete))
                        .setNegativeButton(activity.getString(R.string.alert_dialog_cancel),null)
                        .setPositiveButton(activity.getString(R.string.alert_dialog_delete)) { _, _ ->
//                            val result: Result<String> = LoginRepository(LoginDataSource())
//                                .logout(application)
//                            if (result is Result.Success){
//                                startLoginActivity()
//                            }else{
//                                Toast.makeText(this,
//                                    getString(R.string.message_error_resource),
//                                    Toast.LENGTH_SHORT)
//                                    .show()
//                            }
                        }
                        .show()
                }

        }
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(query: CharSequence?): FilterResults {
                //To change body of created functions use File | Settings | File Templates.
                val filteredList = ArrayList<Data>()
                if (query.toString().isEmpty()){
                    var aux= srListAll
                    filteredList.addAll(srListAll)
                }else{
                    for(driver in srListAll){
                        val nameAndDocument = driver.description+
                                driver.nextMaintenanceDate+
                                driver.userName
                        if (nameAndDocument.toLowerCase().contains(query.toString().toLowerCase())){
                            filteredList.add(driver)
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(query: CharSequence?, filterResults: FilterResults?) {
                //To change body of created functions use File | Settings | File Templates.
                srListFilter = filterResults!!.values as List<Data>
                setListData(srListFilter)
            }
        }
    }



}