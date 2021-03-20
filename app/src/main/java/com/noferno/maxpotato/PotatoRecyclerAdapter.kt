package com.noferno.maxpotato

import android.text.TextUtils
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView

class PotatoRecyclerAdapter : RecyclerView.Adapter<PotatoViewHolder>() {


    private val dataList: MutableList<PotatoEntity> = mutableListOf()
    private val displayableList: MutableList<PotatoEntity> = mutableListOf()


    private var lastSearchQuery: String? = null
    var onItemClickListener: ((PotatoEntity) -> Unit)? = null

    var filter: Filtraaasss = Filtraaasss()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PotatoViewHolder {
        return PotatoViewHolder.initPotatoViewHolder(parent)
    }

    override fun onBindViewHolder(holder: PotatoViewHolder, position: Int) {
        holder.bindView(displayableList[position]) {
            onItemClickListener?.invoke(it)
        }
    }

    override fun getItemCount(): Int {
        return displayableList.size
    }


    fun search(query: String?) {
        lastSearchQuery = query
        filter.filter(query)
    }

    fun setListNew(potatos: List<PotatoEntity>) {
        dataList.clear()
        dataList.addAll(potatos)
        search(lastSearchQuery)
    }


    inner class Filtraaasss : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterResult = FilterResults()
            val fiteredList = mutableListOf<PotatoEntity>()

            if (!TextUtils.isEmpty(constraint)) {

                dataList.forEach {
                    if (it.name.contains(constraint ?: "", true)) {
                        fiteredList.add(it)
                    }
                }
            } else {
                fiteredList.addAll(dataList)
            }
            filterResult.values = fiteredList
            filterResult.count = fiteredList.size
            return filterResult
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            displayableList.clear()
            displayableList.addAll(results.values as MutableList<PotatoEntity>)
            notifyDataSetChanged()
        }
    }
}