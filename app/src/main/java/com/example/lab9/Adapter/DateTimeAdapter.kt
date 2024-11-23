package com.example.lab9.Adapter


import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab9.R

class DateTimeAdapter(private val dateTimeList: MutableList<Pair<String, String>>) : RecyclerView.Adapter<DateTimeAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDateTime: TextView = itemView.findViewById(R.id.tvDateTime)
        val edtContent: EditText = itemView.findViewById(R.id.edtContent) // Sử dụng EditText thay vì MaterialTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_date_time, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (dateTime, content) = dateTimeList[position]
        holder.tvDateTime.text = dateTime
        holder.edtContent.setText(content)

        // Lắng nghe sự thay đổi nội dung nhập vào trong EditText
        holder.edtContent.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                // Thay vì sử dụng position, sử dụng holder.adapterPosition
                val currentPosition = holder.adapterPosition
                if (currentPosition != RecyclerView.NO_POSITION) {
                    dateTimeList[currentPosition] = Pair(dateTime, charSequence.toString()) // Cập nhật nội dung
                }
            }

            override fun afterTextChanged(editable: Editable?) {}
        })
    }

    override fun getItemCount(): Int = dateTimeList.size

    // Thêm phương thức để thêm ngày giờ và nội dung vào danh sách
    fun addDateTime(dateTime: String, content: String) {
        dateTimeList.add(Pair(dateTime, content))
        notifyItemInserted(dateTimeList.size - 1)
    }
}



