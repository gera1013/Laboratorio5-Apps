package com.example.laboratorio5

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.laboratorio5.databinding.ActivityMainBinding
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.floatingActionButton.setOnClickListener{
            val scanner = IntentIntegrator(this)
            scanner.initiateScan()
        }

        val fragment = InicioFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction  = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.placeHolder, fragment)

        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK){
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if(result != null){
                if(result.contents == null){
                    Toast.makeText(this, "Escaneo cancelado", Toast.LENGTH_LONG).show()
                } else{
                    var agregado = false
                    for(a : Item in Inventario.productosInventario){
                        val codigo = a.getCode()
                        if(codigo == result.contents){
                            agregado = true
                            InventarioDesplegado.productosInventario.add(a)
                        }
                    }
                    if(agregado) Toast.makeText(this, "Escaneo correcto", Toast.LENGTH_LONG).show()
                    else{
                        Toast.makeText(this, "El elemento escaneado no está en el inventario", Toast.LENGTH_LONG).show()
                    }
                }
            } else{
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
}
