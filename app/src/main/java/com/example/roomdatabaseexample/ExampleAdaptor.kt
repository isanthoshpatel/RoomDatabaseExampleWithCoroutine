package com.example.roomdatabaseexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExampleAdaptor(c: Context, var list:MutableList<Note>) : RecyclerView.Adapter<ExampleAdaptor.ExampleViewHolder>(){

    var interfaceObj:MyInterface? = null

    interface MyInterface{
        fun myWork(p:Int)
    }
    init {
        interfaceObj = c as MyInterface
    }

    fun deleteItem(p:Int){
        list.removeAt(p)
    }

    class ExampleViewHolder : RecyclerView.ViewHolder{

        var tvtitle:TextView? = null
        var tvdesc:TextView? =  null
        var tvp:TextView? = null

        constructor(v:View,i:MyInterface?):super(v){
            tvtitle = v.findViewById(R.id.tv_title)
            tvdesc = v.findViewById(R.id.tv_desc)
            tvp = v.findViewById(R.id.tv_priority)

            v.setOnClickListener{
                var p = adapterPosition

                if (i!=null && p != RecyclerView.NO_POSITION) {
                    i.run { myWork(p) }
                }
            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.cardview,parent,false)
        return ExampleViewHolder(v,interfaceObj)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        var item = list.get(position)
        holder.run {
            tvtitle!!.setText(item.title)
            tvdesc!!.setText(item.desc)
            tvp!!.setText(item.p.toString())
        }
    }


}