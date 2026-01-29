package com.thekingmoss

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.thekingmoss.dto.detallePedido.DetallePedidoRequestDto
import com.thekingmoss.dto.pedido.PedidoRequestDto
import com.thekingmoss.dto.pedido.PedidoResponseDto
import com.thekingmoss.entity.CarritoItem
import com.thekingmoss.entity.SessionManager
import com.thekingmoss.services.ApiServiceCarrito
import com.thekingmoss.services.ApiServicePedido
import com.thekingmoss.utils.ApiUtils
import com.thekingmoss.utils.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CheckoutActivity : AppCompatActivity() {
    private lateinit var tvTitulo: TextView
    private lateinit var tvResumen: TextView
    private lateinit var btnConfirmar: Button
    private lateinit var layoutExito: LinearLayout

    private lateinit var session: SessionManager

    private val usuarioId = 3L

    private lateinit var carritoItems: List<CarritoItem>


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

        carritoItems = intent.getSerializableExtra("carrito") as? List<CarritoItem>
            ?: emptyList()


        val total = intent.getDoubleExtra("total", 0.0)

        tvResumen.text = """
            Resumen de compra
            
            Total a pagar:
            S/ %.2f
        """.trimIndent().format(total)

        btnConfirmar.setOnClickListener {
            confirmarCompra()
        }

        session = SessionManager(this)

    }

    // val carritoItems = intent.getSerializableExtra("carrito") as List<CarritoItem>


    private fun confirmarCompra() {
        btnConfirmar.isEnabled = false

        val usuarioId = session.getUserId()
        val token = session.getToken()

        if (usuarioId == -1L || token.isEmpty()) {
            Toast.makeText(this, "Sesión no válida", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val retrofit = RetrofitClient.getClient(
            ApiUtils.BASE_URL,
            token
        )

        val service = retrofit.create(ApiServicePedido::class.java)

        // 🔹 Ejemplo de detalles (normalmente vienen del carrito)
        val detalles = carritoItems.map {
            DetallePedidoRequestDto(
                productoId = it.productoId,
                cantidad = it.cantidad
            )
        }

        val fechaPedido = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss",
            Locale.getDefault()
        ).format(Date())


        val pedido = PedidoRequestDto(
            fechaPedido = fechaPedido,
            tipoEntrega = "DELIVERY",
            informacionPedido = "Compra desde app móvil",
            instruccionEntrega = "Entregar en portería",
            tipoEstadoPedido = "PAGADO",
            usuarioId = usuarioId,
            detalles = detalles
        )



        service.registrarPedido(pedido).enqueue(object : Callback<PedidoResponseDto> {
            override fun onResponse(
                call: Call<PedidoResponseDto>,
                response: Response<PedidoResponseDto>
            ) {
                if (response.isSuccessful) {
                    mostrarExito()
                    vaciarCarrito()
                } else {
                    Toast.makeText(
                        this@CheckoutActivity,
                        "Error al registrar pedido",
                        Toast.LENGTH_SHORT
                    ).show()
                    btnConfirmar.isEnabled = true
                }
            }

            override fun onFailure(call: Call<PedidoResponseDto>, t: Throwable) {
                Toast.makeText(
                    this@CheckoutActivity,
                    "Error de conexión",
                    Toast.LENGTH_SHORT
                ).show()
                btnConfirmar.isEnabled = true
            }
        })
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

    private fun mostrarExito() {
        layoutExito.visibility = View.VISIBLE
        btnConfirmar.visibility = View.GONE
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getToken(): String {
        return session.getToken()
    }
}