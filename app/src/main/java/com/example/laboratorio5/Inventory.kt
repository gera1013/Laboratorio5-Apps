package com.example.laboratorio5

interface Inventory {
    val productosInventario: ArrayList<Item>
    fun clear()
    fun add(element: Item)
    fun del(elementIndex: Int)
    fun display(): String
}
