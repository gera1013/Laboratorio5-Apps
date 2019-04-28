package com.example.laboratorio5

class Item (name : String, code : String, cant : Int){

    private var nombre : String = name
    private var cantidad : Int = cant
    private var codigo : String = code


    fun getCantidad(): Int {
        return cantidad
    }

    fun getNombre(): String{
        return nombre
    }

    fun getCode(): String{
        return codigo
    }

    fun setCantidad(numero: Int){
        cantidad = numero
    }
}
