package com.example.Preferences_21142.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.Preferences_21142.R
import com.example.Preferences_21142.models.Videojuego

class VideojuegoAdapter(
    private val inner_videojuegos: ArrayList<Videojuego>,
    private val inner_context: Context
) :
    RecyclerView.Adapter<VideojuegoAdapter.ContenedorDeVista>() {
    private val layoutType = 0

    inner class ContenedorDeVista(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        var tvNombre: TextView
        var tvPrecio: TextView
        var tvConsola: TextView
        var tvClasificacion: TextView
        var ivFoto: ImageView
        var bnCompra: Button

        init {

            tvNombre = view.findViewById(R.id.tvkirby)
            tvPrecio = view.findViewById(R.id.tvPrecio)
            tvConsola = view.findViewById(R.id.tvConsola)
            tvClasificacion = view.findViewById(R.id.tvClasificacion)
            ivFoto = view.findViewById(R.id.ivkirby)
            bnCompra = view.findViewById(R.id.bnComprar)
            bnCompra.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if (adapterPosition >= 0) {
                val sharedPref =
                    inner_context.getSharedPreferences("PERSISTENCIA", Context.MODE_PRIVATE)
                val edad = sharedPref.getInt("edad", 0)
                val (_, nombre, _, _, _, rate) = inner_videojuegos[adapterPosition]
                if (rate.equals("Mature") && edad <= 16 || rate.equals("Teen") && edad <= 11) {
                    Toast.makeText(
                        inner_context,
                        "No puedes comprar el juego $nombre.",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        inner_context,
                        "Gracias por comprar el juego $nombre",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContenedorDeVista {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_segunda_vista, parent, false)
        return ContenedorDeVista(view)
    }

    override fun onBindViewHolder(holder: ContenedorDeVista, position: Int) {
        holder.tvNombre.text = inner_videojuegos[position].nombre
        holder.tvPrecio.text = "$" + inner_videojuegos[position].precio
        holder.tvConsola.text = inner_videojuegos[position].consola
        holder.tvClasificacion.text = inner_videojuegos[position].rate
        holder.ivFoto.setImageResource(inner_videojuegos[position].imagen)
    }

    override fun getItemCount(): Int {
        return inner_videojuegos.size
    }
}