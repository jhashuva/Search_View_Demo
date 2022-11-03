package com.example.searchviewdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class StudentAdaptor(): RecyclerView.Adapter<StudentAdaptor.StudentViewHolder>() {
    var studentsData: ArrayList<Student> = ArrayList()
    fun addList(items: ArrayList<Student>){
        this.studentsData = items
        notifyDataSetChanged()
    }
    class StudentViewHolder(myView: View): RecyclerView.ViewHolder(myView) {
        var rollNumber = myView.findViewById<TextView>(R.id.rollNumber)
        var studentName = myView.findViewById<TextView>(R.id.namee)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.student_list, parent, false)
        return StudentViewHolder(layout)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.rollNumber.text = studentsData!![position].roll_no.toString()
        holder.studentName.text = studentsData!![position].name.toString()
    }

    override fun getItemCount(): Int {
        return studentsData!!.size
    }
}
