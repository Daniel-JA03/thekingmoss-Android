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
import com.thekingmoss.ui.pedido.HistorialPedidosActivity

class MainActivity : AppCompatActivity() {


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