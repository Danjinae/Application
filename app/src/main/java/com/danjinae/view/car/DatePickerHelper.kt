package com.danjinae.view.car

import android.app.DatePickerDialog
import android.content.Context
import java.util.*

class DatePickerHelper(context: Context, isSpinnerType: Boolean = false) {
    private var dialog: DatePickerDialog
    private var callback: Callback? = null
    private val listener =
        DatePickerDialog.OnDateSetListener { datePicker, year, monthOfYear, dayOfMonth ->
            callback?.onDateSelected(dayOfMonth, monthOfYear, year)
        }
    init {
        val style = if (isSpinnerType) android.R.style.Theme_Material_Light_Dialog else 0
        val cal = Calendar.getInstance()
        dialog = DatePickerDialog(context, style, listener,
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
    }
    fun showDialog(dayofMonth: Int, month: Int, year: Int, callback: Callback?) {
        this.callback = callback
        dialog.datePicker.init(year, month, dayofMonth, null)
        dialog.show()
    }
    fun setMinDate(minDate: Long) {
        dialog.datePicker.minDate = minDate
    }
    fun setMaxDate(maxDate: Long) {
        dialog.datePicker.maxDate = maxDate
    }
    interface Callback {
        fun onDateSelected(dayofMonth: Int, month: Int, year: Int)
    }
}