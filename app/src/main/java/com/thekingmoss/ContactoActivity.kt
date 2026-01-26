package com.thekingmoss

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.thekingmoss.controlador.ContactoViewModel
import com.thekingmoss.controlador.ContactoViewModelFactory
import com.thekingmoss.repository.ContactoRepository
import com.thekingmoss.utils.ApiUtils

class ContactoActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etEmail: EditText
    private lateinit var etAsunto: EditText
    private lateinit var etMensaje: EditText
    private lateinit var btnEnviar: Button

    private lateinit var viewModel: ContactoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contacto)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.apply {
            title = "Contáctanos"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // Inicializar vistas
        etNombre = findViewById(R.id.etNombreContacto)
        etEmail = findViewById(R.id.etEmailContacto)
        etAsunto = findViewById(R.id.etAsuntoContacto)
        etMensaje = findViewById(R.id.etMensajeContacto)
        btnEnviar = findViewById(R.id.btnEnviarContacto)

        // Inicializar ViewModel
        val service = ApiUtils.contactoService()
        val repository = ContactoRepository(service)
        val factory = ContactoViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[ContactoViewModel::class.java]

        // Observar resultado
        viewModel.envioResult.observe(this) { result ->
            if (result.isSuccess) {
                Toast.makeText(this, "¡Mensaje enviado exitosamente!", Toast.LENGTH_SHORT).show()
                finish() // O limpia los campos
            } else {
                val msg = result.exceptionOrNull()?.message ?: "Error al enviar el mensaje"
                Toast.makeText(this, "Error: $msg", Toast.LENGTH_LONG).show()
            }
        }

        // Evento del botón
        btnEnviar.setOnClickListener { validarYEnviar() }
    }

    private fun validarYEnviar() {
        val nombre = etNombre.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val asunto = etAsunto.text.toString().trim().ifEmpty { null }
        val mensaje = etMensaje.text.toString().trim()

        if (nombre.isEmpty()) {
            Toast.makeText(this, "Ingrese su nombre", Toast.LENGTH_SHORT).show()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Correo inválido", Toast.LENGTH_SHORT).show()
            return
        }
        if (mensaje.isEmpty()) {
            Toast.makeText(this, "Ingrese un mensaje", Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.enviarMensaje(nombre, email, asunto, mensaje)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}