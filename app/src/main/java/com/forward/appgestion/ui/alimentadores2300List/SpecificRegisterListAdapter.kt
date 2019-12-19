package com.forward.appgestion.ui.alimentadores2300List

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com .forward.appgestion.R
import com.forward.appgestion.data.model.SpecificRegister.Data
import com.forward.appgestion.data.model.SpecificRegister.SpecificRegisterList
import kotlinx.android.synthetic.main.specific_item_list.view.*


@Suppress("UNCHECKED_CAST")
class SpecificRegisterListAdapter(context: Context, private var specificRegisterList: SpecificRegisterList) :
RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private val TAG: String = "AppDebug"
    private val activity = context
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
//                    Toast.makeText(activity,"clicked " + position, Toast.LENGTH_SHORT).show()
//                    Log.d(TAG,"ITEM: "+ " : "+holder.reception_shipment.text)
//
//                    val textBundle = Bundle()
//                    textBundle.putString("nroReception",holder.reception_shipment.text.toString())
//                    findViewController.navigate(R.id.see_reception_order, textBundle)
//
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

        fun bind(specificRegisterItem: Data){
            tVDesc.text = specificRegisterItem.descripcion
            tVMant.text = specificRegisterItem.fecha_mantenimiento
            tVNMant.text = specificRegisterItem.fecha_prox_mantenimiento
            tVAuthor.text = specificRegisterItem.userName
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
                        val nameAndDocument = driver.descripcion+
                                driver.fecha_prox_mantenimiento+
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