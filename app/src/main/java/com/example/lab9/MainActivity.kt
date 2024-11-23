package com.example.lab9

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab9.Adapter.DateTimeAdapter
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAddDateTime: ImageButton
    private lateinit var dateTimeAdapter: DateTimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        btnAddDateTime = findViewById(R.id.btnAddDateTime)

        // Thiết lập RecyclerView
        dateTimeAdapter = DateTimeAdapter(mutableListOf())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = dateTimeAdapter

        // Xử lý khi nhấn nút thêm ngày giờ
        btnAddDateTime.setOnClickListener {
            showDateTimePicker()
        }
    }

    private fun showDateTimePicker() {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        DatePickerDialog(this, { _, year, month, dayOfMonth ->
            TimePickerDialog(this, { _, hourOfDay, minute ->
                val selectedDateTime = String.format(
                    "%02d/%02d/%d %02d:%02d",
                    dayOfMonth, month + 1, year, hourOfDay, minute
                )
                // Thêm thời gian và nội dung vào RecyclerView
                dateTimeAdapter.addDateTime(selectedDateTime, "")
            }, currentHour, currentMinute, true).show()
        }, currentYear, currentMonth, currentDay).show()
    }
}



