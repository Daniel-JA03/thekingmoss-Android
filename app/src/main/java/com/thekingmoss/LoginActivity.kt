package com.thekingmoss

import android.app.ProgressDialog
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
import com.thekingmoss.dto.auth.LoginRequestDto
import com.thekingmoss.dto.auth.LoginResponseDto
import com.thekingmoss.utils.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var edtCorreo: EditText
    private lateinit var edtContrasena: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvUsuarioNuevo: TextView
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.apply {
            title = "Login"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        edtCorreo = findViewById(R.id.CorreoLogin)
        edtContrasena = findViewById(R.id.PassLogin)
        btnLogin = findViewById(R.id.BtnLogeo)
        tvUsuarioNuevo = findViewById(R.id.UsuarioNuevoTXT)

        progressDialog = ProgressDialog(this).apply {
            setTitle("Espere por favor")
            setMessage("Iniciando sesión...")
            setCanceledOnTouchOutside(false)
        }

        btnLogin.setOnClickListener {
            validarDatos()
        }

        tvUsuarioNuevo.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
    }

    // se modifico para que permita ingresar con el username y password
    private fun validarDatos() {
        val username = edtCorreo.text.toString().trim()
        val password = edtContrasena.text.toString().trim()

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Ingrese usuario", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Ingrese contraseña", Toast.LENGTH_SHORT).show()
        } else {
            loginDelUsuario(username, password)
        }
    }


    private fun loginDelUsuario(username: String, password: String) {
        progressDialog.show()

        val loginRequest = LoginRequestDto(username = username, password = password)

        // ✅ Usamos ApiUtils.authService() → sin token
        val call = ApiUtils.authService().login(loginRequest)



        call.enqueue(object : Callback<LoginResponseDto> {
            override fun onResponse(call: Call<LoginResponseDto>, response: Response<LoginResponseDto>) {
                progressDialog.dismiss()
                if (response.isSuccessful && response.body() != null) {
                    val user = response.body()!!
                    saveUserSession(user.token, user.usuarioId, user.username, user.email)
                    Toast.makeText(this@LoginActivity, "Bienvenido(a) ${user.username}", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else {
                    val errorMsg = when (response.code()) {
                        401 -> "Credenciales incorrectas"
                        400 -> "Solicitud incorrecta"
                        else -> "Error (${response.code()})"
                    }
                    Toast.makeText(this@LoginActivity, errorMsg, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponseDto>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(this@LoginActivity, "Error de red: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun saveUserSession(token: String, userId: Long, username: String, email: String) {
        val prefs = getSharedPreferences("session", MODE_PRIVATE)
        with(prefs.edit()) {
            putString("auth_token", token)
            putLong("user_id", userId)
            putString("username", username)
            putString("email", email)
            apply()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}