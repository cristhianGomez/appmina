package com.forward.appgestion.ui.informacion

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer

import com.forward.appgestion.R
import com.forward.appgestion.ui.Constants
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.informacion_fragment.*
import kotlinx.android.synthetic.main.specific_register_fragment.*
import kotlinx.android.synthetic.main.specific_register_fragment.cl_container
import kotlinx.android.synthetic.main.specific_register_fragment.pb_progress

class InformacionFragment : Fragment() {

    private lateinit var viewModel: InformacionViewModel
    private var statusLoading: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel =
            ViewModelProviders.of(this, InformationViewModelFactory(activity!!.application))
                .get(InformacionViewModel::class.java)
        return inflater.inflate(R.layout.informacion_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getLoadingStatus()
        getData(statusLoading)
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
        viewModel.getData(status)
        viewModel.getInformationModel().observe(this, Observer {
            if (it != null) {
                main_container.removeAllViews()
                val arrayFolders = ArrayList<String>()
                for (directory in it){
                    val parts = directory.split("/")
                    for ((index,value) in parts.withIndex()){
                        if (value !in arrayFolders){
                            if (index == 0){
                                val cardView = createCardViewContainer(value)
                                main_container.addView(cardView)
                            }else if(!value.contains(".")){
                                val previousPart = parts[index-1]
                                val cardView = createCardViewContainer(value)

                                val linearLayoutParentExpandable = main_container
                                    .findViewWithTag<LinearLayout>(previousPart+"expandable")
                                linearLayoutParentExpandable.addView(cardView)

                            }else{
                                val previousPart = parts[index-1]
                                val cardViewFile = createCardViewFile(value)
                                val linearLayoutParentExpandable = main_container
                                    .findViewWithTag<LinearLayout>(previousPart+"expandable")
                                linearLayoutParentExpandable.addView(cardViewFile)
                            }
                            arrayFolders.add(value)
                        }
                    }
                }
            }
        })
    }
    private fun showSnackBar(message: String) {
        Snackbar
            .make(cl_container, message, Snackbar.LENGTH_INDEFINITE)
            .setTextColor(ContextCompat.getColor(requireContext(), R.color.white_trafic))
            .setAction("Volver intentarlo") {
                viewModel.getData(Constants.STATUS_ERROR)
            }
            .show()
    }

    private fun createCardViewFile(value: String): CardView {
        val cardView = createCardView(value)
        cardView.setPadding(15,15,15,15)
        val textView = createTexViewFile(value)
        cardView.addView(textView)
        return  cardView
    }

    private fun createCardViewContainer(value : String): CardView {
        val linearLayoutTitle =  createLinearLayoutTitle(value,value)
        val linearLayoutParent = createLinearLayoutParent(value)
        val linearLayoutParentExpandable = createParentExpandable(value)
        linearLayoutParent.addView(linearLayoutTitle)
        linearLayoutParent.addView(linearLayoutParentExpandable)
        val btn = linearLayoutTitle.findViewWithTag<Button>(value+"button")
        addFunSetOnclickListener(linearLayoutTitle,linearLayoutParentExpandable,btn)
        val cardView = createCardView(value)
        cardView.addView(linearLayoutParent)

        return cardView
    }

    private fun createCardView(tag: String): CardView {
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(10,10,10,10)

        val cardView = CardView(requireContext())
        cardView.id = CardView.generateViewId()
        cardView.tag = tag
        cardView.layoutParams = params

        return cardView
    }

    private fun createLinearLayoutParent(tag: String): LinearLayout {
        val linearLayoutParent = LinearLayout(requireContext())
        linearLayoutParent.id = LinearLayout.generateViewId()
        linearLayoutParent.tag = tag+"parent"
        linearLayoutParent.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        linearLayoutParent.orientation = LinearLayout.VERTICAL
        return  linearLayoutParent
    }

    private fun createParentExpandable(tag: String): LinearLayout {
        val linearLayoutParent = LinearLayout(requireContext())
        linearLayoutParent.id = LinearLayout.generateViewId()
        linearLayoutParent.tag = tag+"expandable"
        linearLayoutParent.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        linearLayoutParent.orientation = LinearLayout.VERTICAL
        linearLayoutParent.visibility = View.GONE
        return  linearLayoutParent
    }

    private fun createLinearLayoutTitle(title:String,tag: String): LinearLayout {

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val paramsTextView = LinearLayout.LayoutParams(
            0,
            convertFloatToDp(40f), 0.9f
        )

        val linearLayoutTitle = LinearLayout(requireContext())
        linearLayoutTitle.id = View.generateViewId()
        linearLayoutTitle.tag = tag+"title"
        linearLayoutTitle.layoutParams = params
        linearLayoutTitle.orientation = LinearLayout.HORIZONTAL
        linearLayoutTitle.gravity = Gravity.CENTER
        linearLayoutTitle.setPadding(15,15,15,15)

        val textView = TextView(requireContext())
        textView.layoutParams = paramsTextView
        textView.text = title
        textView.gravity = Gravity.CENTER


        val paramsButton = LinearLayout.LayoutParams(
            0,
            convertFloatToDp(30f), 0.1f
        )
        val button = Button(requireContext())
        button.id = Button.generateViewId()
        button.tag = tag+"button"
        button.layoutParams = paramsButton
        button.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp)

        linearLayoutTitle.addView(textView)
        linearLayoutTitle.addView(button)
        return linearLayoutTitle
    }

    private fun createTexViewFile(tag: String): TextView {
        val paramsTextView = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            convertFloatToDp(40f)
        )
        val textView = TextView(requireContext())
        textView.layoutParams = paramsTextView
        textView.text = tag
        textView.gravity = Gravity.CENTER

        return textView
    }

    private fun addFunSetOnclickListener(layoutTitle:View, layoutExpandable:View, button: Button){
        layoutTitle.setOnClickListener{
            if (layoutExpandable.visibility == View.GONE){
                layoutExpandable.visibility = View.VISIBLE
                button.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp)
            } else {
                layoutExpandable.visibility = View.GONE
                button.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp)
            }
        }
    }

    private fun convertFloatToDp(pixels:Float):Int{
        val dp = pixels
        val metrics: DisplayMetrics = this.resources.displayMetrics
        val fpixels = metrics.density * dp

        return (fpixels + 0.5f).toInt()
    }
}
