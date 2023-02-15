package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null
    private var tvAgeInHours : TextView? = null
    private var tvAgeInDays : TextView? = null
    private var tvAgeInMonths : TextView? = null
    private var tvAgeInYears : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        tvAgeInHours = findViewById(R.id.tvAgeInHours)
        tvAgeInDays = findViewById(R.id.tvAgeInDays)
        tvAgeInMonths = findViewById(R.id.tvAgeInMonths)
        tvAgeInYears = findViewById(R.id.tvAgeInYears)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }
    private fun clickDatePicker(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this, "year was $selectedYear, month was ${selectedMonth + 1}, day of month $selectedDayOfMonth", Toast.LENGTH_SHORT).show()
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000
                    val selectedDateInHours = selectedDateInMinutes / 60
                    val selectedDateInDays = selectedDateInHours / 24
                    val selectedDateInMonths = selectedDateInDays / 30
                    val selectedDateInYears = selectedDateInMonths / 12

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60000
                        val currentDateInHours = currentDateInMinutes / 60
                        val currentDateInDays = currentDateInHours / 24
                        val currentDateInMonths = currentDateInDays / 30
                        val currentDateInYears = currentDateInMonths / 12
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                        val differenceInHours = currentDateInHours - selectedDateInHours
                        val differenceInDays = currentDateInDays - selectedDateInDays
                        val differenceInMonths = currentDateInMonths - selectedDateInMonths
                        val differenceInYears = currentDateInYears - selectedDateInYears

                        tvAgeInMinutes?.text = differenceInMinutes.toString()
                        tvAgeInHours?.text = differenceInHours.toString()
                        tvAgeInDays?.text = differenceInDays.toString()
                        tvAgeInMonths?.text = differenceInMonths.toString()
                        tvAgeInYears?.text = differenceInYears.toString()
                    }
                }


            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}