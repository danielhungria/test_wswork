package com.example.carappwswork.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.carappwswork.Database.AppDataBase
import com.example.carappwswork.R
import com.example.carappwswork.databinding.ActivityCadastroPessoaBinding
import com.example.carappwswork.extensions.textString
import com.example.carappwswork.extensions.toast
import com.example.carappwswork.model.Usuario
import kotlinx.coroutines.launch

class CadastroUsuarioActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCadastroPessoaBinding.inflate(layoutInflater)
    }

    private val dao by lazy {
        AppDataBase.instancia(this).usuarioDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoCadastrar()
    }

    private fun configuraBotaoCadastrar() {
        binding.buttonSignUp.setOnClickListener {
            val novoUsuario = criaUsuario()
            cadastra(novoUsuario)
        }
    }

    private fun cadastra(usuario: Usuario) {
        lifecycleScope.launch {
            dao.salva(usuario)
            toast(getString(R.string.cadastrado_com_sucesso))
            finish()
        }
    }

    private fun criaUsuario(): Usuario {
        with(binding){
            return Usuario(
                email = inputTextCadastraEmail.textString(),
                nome = inputTextCadastraNome.textString(),
                telefone = inputTextCadastraTelefone.textString(),
                senha = inputTextCadastraSenha.textString()
            )
        }
    }

}