package com.example.laboratorio5

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratorio5.databinding.FragmentInicioBinding
import com.google.android.material.snackbar.Snackbar

class InicioFragment : Fragment() {
    private lateinit var swipeBackground: ColorDrawable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentInicioBinding = inflate(inflater, R.layout.fragment_inicio, container, false)

        swipeBackground = ColorDrawable(Color.parseColor("#ff0000"))

        val listRecyclerView = binding.listRecyclerView
        val listAdapter = RVAdapter(InventarioDesplegado.productosInventario)

        listRecyclerView.adapter = listAdapter
        listRecyclerView.layoutManager = LinearLayoutManager(activity)

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                listAdapter.removeItem(viewHolder)
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                val itemView = viewHolder.itemView

                if(dX > 0){
                    swipeBackground.setBounds(itemView.left, itemView.top, dX.toInt(), itemView.bottom)
                }

                swipeBackground.draw(c)
                c.save()

                if(dX > 0)
                    c.clipRect(itemView.left, itemView.top, dX.toInt(), itemView.bottom)

                c.restore()
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(listRecyclerView)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == R.id.showInventario){
            Snackbar.make(view!!, Inventario.display(), Snackbar.LENGTH_LONG).show()
            return super.onOptionsItemSelected(item)
        }
        else if(id == R.id.inicioFragment){
            InventarioDesplegado.clear()
            for(a : Item in Inventario.productosInventario){
                a.setCantidad(0)
            }
            return NavigationUI.onNavDestinationSelected(
                item, view!!.findNavController())
                    || super.onOptionsItemSelected(item)
        }
        else {
            return NavigationUI.onNavDestinationSelected(
                item, view!!.findNavController())
                        || super.onOptionsItemSelected(item)
        }
    }
}
