package com.forward.appgestion.ui.specificRegisterDetailList
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.appcompat.widget.SearchView
import com.google.android.material.textfield.TextInputEditText


import java.util.*

class DatePickerFragment( date: TextInputEditText) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private val fecha=date

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        val datePickerFragment = DatePickerDialog(activity!!, this, year, month, day)
//        datePickerFragment.datePicker.spinnersShown = true
        return activity?.let { datePickerFragment }!!

    }
    override fun onDateSet(view: DatePicker, yy: Int, mm: Int, dd: Int) {
        populateSetDate(yy, mm + 1, dd)

    }

    fun populateSetDate(year: Int, month: Int, day: Int) {
        fecha.setText("$day/$month/$year")
    }
}