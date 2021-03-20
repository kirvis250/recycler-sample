package com.noferno.maxpotato

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity: AppCompatActivity() {

    val potatoRecyclerAdapter = PotatoRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actiivity_main)

        potatoRecyclerAdapter.onItemClickListener = {
            Toast.makeText(this, "potato: ${it.name} was clicked", Toast.LENGTH_SHORT).show()
        }

        val recycler = findViewById<RecyclerView>(R.id.recyclerView)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = potatoRecyclerAdapter

        loadData() // or subscribe to some viewmodel
    }


    fun loadData() {
        val list = mutableListOf<PotatoEntity>()
        list.add(PotatoEntity("11", 1555.0, 0))
        list.add(PotatoEntity("12", 1155.0, 20))
        list.add(PotatoEntity("13", 14.0, 80))
        list.add(PotatoEntity("14", 150.0, 60))
        list.add(PotatoEntity("15", 154.0, 100))
        list.add(PotatoEntity("16", 151.0, 100))
        list.add(PotatoEntity("17", 1550.0, 100))
        list.add(PotatoEntity("18", 99.0, 100))

        potatoRecyclerAdapter.setListNew(list)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchView = menu!!.findItem(R.id.search_view).actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                potatoRecyclerAdapter.search(newText)
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
}