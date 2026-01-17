package com.thekingmoss

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.thekingmoss.services.ApiServiceCarrito
import com.thekingmoss.utils.ApiUtils
import com.thekingmoss.utils.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckoutActivity : AppCompatActivity() {
    private lateinit var tvTitulo: TextView
    private lateinit var tvResumen: TextView
    private lateinit var btnConfirmar: Button
    private lateinit var layoutExito: LinearLayout

    private val usuarioId = 3L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_checkout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.title = "Finalizar Compra"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tvTitulo = findViewById(R.id.tvTitulo)
        tvResumen = findViewById(R.id.tvResumen)
        btnConfirmar = findViewById(R.id.btnConfirmar)
        layoutExito = findViewById(R.id.layoutExito)

        val total = intent.getDoubleExtra("total", 0.0)

        tvResumen.text = """
            Resumen de compra
            
            Total a pagar:
            S/ %.2f
        """.trimIndent().format(total)

        btnConfirmar.setOnClickListener {
            confirmarCompra()
        }
    }

    private fun confirmarCompra() {
        btnConfirmar.isEnabled = false

        // Simula procesamiento
        Handler(Looper.getMainLooper()).postDelayed({
            layoutExito.visibility = View.VISIBLE
            btnConfirmar.visibility = View.GONE

            vaciarCarrito()
        }, 1500)
    }

    private fun vaciarCarrito() {
        val retrofit = RetrofitClient.getClient(
            ApiUtils.BASE_URL,
            getToken()
        )

        val service = retrofit.create(ApiServiceCarrito::class.java)

        service.fetchVaciarCarrito(usuarioId).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {}
            override fun onFailure(call: Call<Void>, t: Throwable) {}
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getToken(): String {
        return "PEGA_AQUI_TU_TOKEN"
    }
}