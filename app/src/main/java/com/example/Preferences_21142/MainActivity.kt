package com.example.Preferences_21142

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class MainActivity : AppCompatActivity() {
    private lateinit var tvBienvenido: TextView
    private lateinit var etNombre: EditText
    private lateinit var etnEdad: EditText
    private lateinit var etnAltura: EditText
    private lateinit var etnMonedero: EditText
    private lateinit var bnGuardar: Button
    private lateinit var switchPreferencias: Switch
    private lateinit var recyclerView: RecyclerView
    private val NOMBRE_KEY = "nombre"
    private val EDAD_KEY = "edad"
    private val ALTURA_KEY = "altura"
    private val MONEDERO_KEY = "monedero"
    private val SWITCH_KEY = "switch_estado"
    private val NOMBRE_INSTANCIA = "nombre_instancia"
    private var nombre: String = ""
    private var edad: Int = 0
    private var altura: Float = 0.0f
    private var monedero: Float = 0f

// Se cierra el app , no entiendo el porque
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_vertical -> {
                recyclerView.layoutManager = LinearLayoutManager(this)
                true
            }
            R.id.menu_horizontal -> {
                recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                true
            }
            R.id.menu_grid_2 -> {
                recyclerView.layoutManager = GridLayoutManager(this, 2)
                true
            }
            R.id.menu_grid_3 -> {
                recyclerView.layoutManager = GridLayoutManager(this, 3)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_videojuegos, menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("PREFERENCIAS", "onCreate")
        setContentView(R.layout.activity_main)

        var actionBar: ActionBar?
        actionBar = supportActionBar
        var colorDrawable: ColorDrawable
        colorDrawable = ColorDrawable(Color.parseColor("#FF018786"))
        actionBar!!.setBackgroundDrawable(colorDrawable)

        inicializarVistas()

        Log.d("PREFERENCIAS", savedInstanceState?.getString(NOMBRE_KEY).toString())

        if (savedInstanceState != null) {
            nombre = savedInstanceState.getString(NOMBRE_KEY).toString()
            edad = savedInstanceState.getInt(EDAD_KEY)
            altura = savedInstanceState.getFloat(ALTURA_KEY)
            monedero = savedInstanceState.getFloat(MONEDERO_KEY)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("PREFERENCIAS", "onSaveInstanceState")
        outState.putString(NOMBRE_KEY, nombre )
        outState.putInt(EDAD_KEY, edad )
        outState.putFloat(ALTURA_KEY, altura )
        outState.putFloat(MONEDERO_KEY, monedero )
        super.onSaveInstanceState(outState)
    }

    override fun onResume() {
        Log.d("PREFERENCIAS", "onResume")
        if(TextUtils.isEmpty(nombre)){
            val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
            nombre = miSharedPreferences.getString(NOMBRE_KEY, "").toString()
            edad = miSharedPreferences.getInt(EDAD_KEY, 0)
            altura = miSharedPreferences.getFloat(ALTURA_KEY, 0.0f)
            monedero = miSharedPreferences.getFloat(MONEDERO_KEY, 0.0f)

        }

        tvBienvenido.text = nombre
        super.onResume()
    }

    override fun onStart() {
        Log.d("PREFERENCIAS", "onStart")
        super.onStart()
    }

    override fun onPause() {
        Log.d("PREFERENCIAS", "onPause")
        super.onPause()
    }

    override fun onDestroy() {
        Log.d("PREFERENCIAS", "onDestroy")
        super.onDestroy()
    }

    private fun cambiarTextoBienvenida(nombre: String) {
        if (!TextUtils.isEmpty(nombre)) {
            tvBienvenido.text = "Bienvenido $nombre"
        }


    }

    private fun inicializarVistas() {
        tvBienvenido = findViewById(R.id.tvSaludo)
        etNombre = findViewById(R.id.etNombre)
        bnGuardar = findViewById(R.id.bnGuardar)
        switchPreferencias = findViewById(R.id.swPref)
        etnEdad = findViewById(R.id.etnEdad)
        etnAltura = findViewById(R.id.etnAltura)
        etnMonedero = findViewById(R.id.etnMonedero)

        bnGuardar.setOnClickListener {
            nombre = etNombre.text.toString()
            edad = etnEdad.text.toString().toInt()
            altura = etnAltura.text.toString().toFloat()
            monedero = etnMonedero.text.toString().toFloat()
            cambiarTextoBienvenida(nombre)
            val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
            val editor = miSharedPreferences.edit()
            editor.putString(NOMBRE_KEY, nombre)
            editor.putInt(EDAD_KEY, edad)
            editor.putFloat(ALTURA_KEY, altura)
            editor.putFloat(MONEDERO_KEY, monedero)
            editor.apply()
            val s = Intent(this, ListaGamesActivity::class.java)
            startActivity(s)
            finish()
        }

    }
}