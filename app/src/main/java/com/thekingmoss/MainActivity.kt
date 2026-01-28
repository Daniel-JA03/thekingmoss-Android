package com.thekingmoss

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thekingmoss.adaptador.ProductoAdapter
import com.thekingmoss.controlador.ProductoViewModel
import com.thekingmoss.repository.ProductoRepository
import com.thekingmoss.ui.pedido.HistorialPedidosActivity
import com.thekingmoss.utils.ApiUtils

class MainActivity : AppCompatActivity() {
    private lateinit var rvProductos: RecyclerView
    private lateinit var adapter: ProductoAdapter
    private lateinit var viewModel: ProductoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 🔥 ESTO FALTABA
        rvProductos = findViewById(R.id.rvProductos)
        rvProductos.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.btnExplorar).setOnClickListener {
            startActivity(Intent(this, ProductoActivity::class.java))
        }

        adapter = ProductoAdapter(
            arrayListOf(),
            onVer = { producto ->
                val intent = Intent(this, DetalleProductoActivity::class.java)
                intent.putExtra("producto", producto)
                startActivity(intent)
            },
            onAgregar = {
                Toast.makeText(
                    this,
                    "Usa la vista de productos para comprar",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )

        rvProductos.adapter = adapter



        val repository = ProductoRepository(
            ApiUtils.productoService(),
            ApiUtils.productoImagenService()
        )

        viewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ProductoViewModel(repository) as T
                }
            }
        )[ProductoViewModel::class.java]


        viewModel.productos.observe(this) { lista ->
            val destacados = lista.take(4) // 👈 solo 4 productos
            adapter.actualizarLista(ArrayList(destacados))
        }

        viewModel.cargarProductos()


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        menuInflater.inflate(R.menu.menu_actions, menu) // 🔥 ESTE FALTABA
        actualizarMenuLogin(menu)
        return true
    }


    private fun estaLogueado(): Boolean {
        val prefs = getSharedPreferences("session", MODE_PRIVATE)
        return prefs.contains("token") // o "user_id"
    }

    private fun actualizarMenuLogin(menu: Menu) {
        val menuLogin = menu.findItem(R.id.menu_login) ?: return

        if (estaLogueado()) {
            menuLogin.title = "Logout"
            menuLogin.setIcon(R.drawable.logout)
        } else {
            menuLogin.title = "Login"
            menuLogin.setIcon(R.drawable.baseline_person_24)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.menu_inicio -> true

            R.id.menu_carrito -> {
                startActivity(Intent(this, CarritoActivity::class.java))
                true
            }

            R.id.menu_login -> {
                if (estaLogueado()) {
                    cerrarSesion()
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                true
            }

            R.id.menu_historial -> {
                if (estaLogueado()) {
                    startActivity(Intent(this, HistorialPedidosActivity::class.java))
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                true
            }

            R.id.menu_sobre -> {
                startActivity(Intent(this, SobreNosotrosActivity::class.java))
                true
            }

            R.id.menu_productos -> {
                startActivity(Intent(this, ProductoActivity::class.java))
                true
            }


            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun cerrarSesion() {
        val prefs = getSharedPreferences("session", MODE_PRIVATE)
        prefs.edit().clear().apply()

        Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()

        invalidateOptionsMenu() // refresca el menú
    }

    override fun onResume() {
        super.onResume()
        invalidateOptionsMenu()
    }





}