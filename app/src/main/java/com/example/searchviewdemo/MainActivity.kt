package com.example.searchviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var sl: ArrayList<Student>
    lateinit var adapter: StudentAdaptor
    //lateinit var db:DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var db = DBHelper(this, null)
        var rv = findViewById<RecyclerView>(R.id.rv)
        var rno = findViewById<EditText>(R.id.rollNo)
        var name = findViewById<EditText>(R.id.name)
        var button = findViewById<Button>(R.id.btn)
        adapter = StudentAdaptor()


        button.setOnClickListener(){
            db.insert(rno.text.toString(), name.text.toString())
            sl = db.get()!!
            adapter.addList(sl)
        }
        sl = db.get()!!
        adapter.addList(sl)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item,menu)
        val menuItem: MenuItem = menu!!.findItem(R.id.search_action)
        val searchView: SearchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if(p0!=null){
                    filter(p0)
                }
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun filter(p0: String) {
        var db = DBHelper(this, null)
        val filteredList:ArrayList<Student> = ArrayList()
        for(item in db.get()!!){
            if(item.name.lowercase()
                    .contains(p0.lowercase())|| item.roll_no.lowercase()
                    .contains(p0.lowercase())){
                filteredList.add(item)
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(this,"No Data Found..", Toast.LENGTH_SHORT).show()
        }else{
            adapter.addList(filteredList)
            adapter.notifyDataSetChanged()
        }

    }
}