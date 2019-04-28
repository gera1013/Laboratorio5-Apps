package com.example.laboratorio5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(private val productsLista: ArrayList<Item>) : RecyclerView.Adapter<RVAdapter.RViewHolder>(){

    class RViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var nombreProducto : TextView = itemView.findViewById(R.id.nombreProducto)
        var cantidadProducto : TextView = itemView.findViewById(R.id.cantidadProducto)
        var botonMas : Button = itemView.findViewById(R.id.botonMas)
        var botonMenos : Button = itemView.findViewById(R.id.botonMenos)

        override fun onClick(v: View?) {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RViewHolder {
        val contentView = LayoutInflater.from(parent.context).inflate(R.layout.inventario_item, null)
        return RViewHolder(contentView)
    }

    override fun getItemCount() = productsLista.size

    override fun onBindViewHolder(holder: RViewHolder, position: Int) {
        val itemProducto = productsLista[position]
        holder.nombreProducto.text = itemProducto.getNombre()
        holder.cantidadProducto.text = itemProducto.getCantidad().toString()
        holder.botonMas.setOnClickListener{
            var numero = itemProducto.getCantidad() + 1
            itemProducto.setCantidad(numero)
            holder.cantidadProducto.text = itemProducto.getCantidad().toString()
        }
        holder.botonMenos.setOnClickListener{
            var numero = itemProducto.getCantidad() - 1
            if(numero >= 0) itemProducto.setCantidad(numero)
            else{
                numero = 0
                itemProducto.setCantidad(numero)
            }
            holder.cantidadProducto.text = itemProducto.getCantidad().toString()
        }
    }

    fun removeItem(viewHolder: RecyclerView.ViewHolder) {
        productsLista.removeAt(viewHolder.adapterPosition)
        notifyItemRemoved(viewHolder.adapterPosition)
        notifyDataSetChanged()
    }

    fun removeAll(){
        productsLista.clear()
    }
}