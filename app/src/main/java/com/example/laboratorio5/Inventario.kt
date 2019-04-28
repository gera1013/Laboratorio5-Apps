package com.example.laboratorio5

import android.app.Application

class Inventario : Application(){

    companion object : Inventory{
        override val productosInventario: ArrayList<Item> = ArrayList()

        override fun clear() {
            productosInventario.clear()
        }

        override fun add(element: Item) {
            productosInventario.add(element)
        }

        override fun del(elementIndex: Int) {
            productosInventario.removeAt(elementIndex)
        }

        override fun display(): String{
            var total = "Productos en inventario: "
            for(a: Item in productosInventario)
                total = total + a.getNombre() + ", "
            return total
        }
    }
}