package com.noferno.maxpotato

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class PotatoViewHolder(view: View): RecyclerView.ViewHolder(view) {


    private val name: AppCompatTextView =view.findViewById(R.id.name)
    private val calories: AppCompatTextView =view.findViewById(R.id.calories)
    private val coolness: AppCompatTextView =view.findViewById(R.id.coolness)


    fun bindView(potatoEntity: PotatoEntity, onClick: ((PotatoEntity) -> Unit)?) {

        itemView.setOnClickListener {
            onClick?.invoke(potatoEntity)
        }

        name.text = potatoEntity.name
        calories.text = ("Maximum calories: ${potatoEntity.calories}")
        coolness.text = ("Overall coolness is: ${potatoEntity.coolness}")
    }



    companion object {

        fun initPotatoViewHolder(parent: ViewGroup): PotatoViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_potato, parent, false)
            return PotatoViewHolder(view)
        }

    }






}