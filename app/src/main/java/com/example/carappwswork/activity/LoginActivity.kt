package com.example.carappwswork.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.carappwswork.Database.AppDataBase
import com.example.carappwswork.R
import com.example.carappwswork.databinding.ActivityLoginBinding
import com.example.carappwswork.extensions.toast
import com.example.carappwswork.extensions.vaiPara
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val usuarioDao by lazy {
        AppDataBase.instancia(this).usuarioDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoCadastrar()
        configuraBotaoEntrar()
    }


    private fun configuraBotaoEntrar() = with(binding) {
        activityLoginBotaoEntrar.setOnClickListener {
            val email = activityLoginEmail.text.toString()
            val senha = activityLoginSenha.text.toString()
            autentica(email, senha)
        }
    }

    private fun autentica(email: String, senha: String) {
        lifecycleScope.launch() {
            usuarioDao.autentica(email, senha)?.let { usuario ->
                usuarioDao.update(usuario.copy(usuarioAutenticado = true))
                runOnUiThread {
                   vaiPara(MainActivity::class.java, true)
                    finish()
                }
            } ?: toast(getString(R.string.falha))
        }
    }

    private fun configuraBotaoCadastrar() {
        binding.activityLoginBotaoCadastrar.setOnClickListener {
            vaiPara(CadastroUsuarioActivity::class.java)
        }
    }
}