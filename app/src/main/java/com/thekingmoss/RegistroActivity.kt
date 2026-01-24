package com.thekingmoss

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.thekingmoss.controlador.RegistroViewModel
import com.thekingmoss.controlador.RegistroViewModelFactory
import com.thekingmoss.repository.RegistroRepository
import com.thekingmoss.utils.ApiUtils

class RegistroActivity : AppCompatActivity() {

    private lateinit var edtUsername: EditText
    private lateinit var edtNombre: EditText
    private lateinit var edtApellido: EditText
    private lateinit var edtTelefono: EditText
    private lateinit var edtCorreo: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtConfirmPassword: EditText
    private lateinit var btnRegistrar: Button
    private lateinit var tvTengoCuenta: TextView

    private lateinit var viewModel: RegistroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.apply {
            title = "Registro"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // Inicializar vistas
        edtUsername = findViewById(R.id.UsernameEt)
        edtNombre = findViewById(R.id.NombreEt)
        edtApellido = findViewById(R.id.ApellidoEt)
        edtTelefono = findViewById(R.id.TelefonoEt)
        edtCorreo = findViewById(R.id.CorreoEt)
        edtPassword = findViewById(R.id.ContraseñaEt)
        edtConfirmPassword = findViewById(R.id.etConfirmarContraseña)
        btnRegistrar = findViewById(R.id.RegistrarUsuario)
        tvTengoCuenta = findViewById(R.id.TengounacuentaTXT)

        // Inicializar ViewModel
        val service = ApiUtils.authService()
        val repository = RegistroRepository(service)
        val factory = RegistroViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[RegistroViewModel::class.java]

        // Observar resultado
        viewModel.registerResult.observe(this) { result ->
            if (result.isSuccess) {
                Toast.makeText(this, "¡Registro exitoso!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                val msg = result.exceptionOrNull()?.message ?: "Error desconocido"
                Toast.makeText(this, "Error: $msg", Toast.LENGTH_LONG).show()

            }
        }

        // Eventos
        btnRegistrar.setOnClickListener { validarYRegistrar() }
        tvTengoCuenta.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun validarYRegistrar() {
        val username = edtUsername.text.toString().trim()
        val nombre = edtNombre.text.toString().trim()
        val apellido = edtApellido.text.toString().trim()
        val telefono = edtTelefono.text.toString().trim()
        val email = edtCorreo.text.toString().trim()
        val password = edtPassword.text.toString().trim()
        val confirmPassword = edtConfirmPassword.text.toString().trim()

        if (username.isEmpty()) {
            Toast.makeText(this, "Ingrese nombre de usuario", Toast.LENGTH_SHORT).show()
            return
        }
        if (nombre.isEmpty()) {
            Toast.makeText(this, "Ingrese su nombre", Toast.LENGTH_SHORT).show()
            return
        }
        if (apellido.isEmpty()) {
            Toast.makeText(this, "Ingrese su apellido", Toast.LENGTH_SHORT).show()
            return
        }
        if (telefono.isEmpty()) {
            Toast.makeText(this, "Ingrese su teléfono", Toast.LENGTH_SHORT).show()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Correo inválido", Toast.LENGTH_SHORT).show()
            return
        }
        if (password.length < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            return
        }
        if (password != confirmPassword) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.register(username, password, nombre, apellido, telefono, email)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}