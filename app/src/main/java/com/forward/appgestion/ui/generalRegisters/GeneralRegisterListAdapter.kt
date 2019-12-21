package com.forward.appgestion.ui.generalRegisters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.forward.appgestion.R
import com.forward.appgestion.data.model.DataGeneralModel
import com.forward.appgestion.data.model.GeneralRegistersListModel
import kotlinx.android.synthetic.main.general_register_item_list.view.*

@Suppress("UNCHECKED_CAST")
class GeneralRegisterListAdapter(context: Context, generalRegistersList: GeneralRegistersListModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private val TAG: String = "AppDebug"
    private val activity = context
    private var generalRegistersListAll: List<DataGeneralModel> = generalRegistersList.data!!
    private var generalRegistersListFilter: List<DataGeneralModel> = generalRegistersList.data!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ReceptionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.general_register_item_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ReceptionViewHolder -> {
                holder.bind(generalRegistersListFilter[position])
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
        return generalRegistersListFilter.size
    }

    fun setListData(specificRegisterList: List<DataGeneralModel>) {
        generalRegistersListFilter = specificRegisterList
        notifyDataSetChanged()
    }

//    fun submitList(receptionList: List<EndPointDriverListModel>){
//        driversListAll = receptionList
//    }

    inner class ReceptionViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        private val textUserName = itemView.text_user_name
        private val textMaintenance = itemView.text_maintenance_date
        private val textTypeWork = itemView.text_type_work
        private val textWorkArea = itemView.text_work_area

        fun bind(generalRegisterItem: DataGeneralModel){
            textUserName.text = generalRegisterItem.userName
            textMaintenance.text = generalRegisterItem.maintenanceDate
            textTypeWork.text = generalRegisterItem.typeArea
            textWorkArea.text = generalRegisterItem.workArea
        }
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(query: CharSequence?): FilterResults {
                //To change body of created functions use File | Settings | File Templates.
                val filteredList = ArrayList<DataGeneralModel>()
                if (query.toString().isEmpty()){
                    var aux= generalRegistersListAll
                    filteredList.addAll(generalRegistersListAll)
                }else{
                    for(generalRegister in generalRegistersListAll){
                        val nameAndDocument = generalRegister.orderNumber+
                                generalRegister.userName +
                                generalRegister.workArea
                        if (nameAndDocument.toLowerCase().contains(query.toString().toLowerCase())){
                            filteredList.add(generalRegister)
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(query: CharSequence?, filterResults: FilterResults?) {
                //To change body of created functions use File | Settings | File Templates.
                generalRegistersListFilter = filterResults!!.values as List<DataGeneralModel>
                setListData(generalRegistersListFilter)
            }
        }
    }



}