package com.example.laboratorio5


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil.inflate
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.laboratorio5.databinding.FragmentProductoBinding
import kotlinx.android.synthetic.main.fragment_producto.*

class ProductoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding : FragmentProductoBinding = inflate(inflater, R.layout.fragment_producto, container, false)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.newprod_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val codigo = editText1
        val nombre = editText2
        Inventario.add(Item(nombre.text.toString(), codigo.text.toString(),0))
        return NavigationUI.onNavDestinationSelected(item!!,
            view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }
}
