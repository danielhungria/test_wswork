package com.example.carappwswork.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.carappwswork.Database.AppDataBase
import com.example.carappwswork.R
import com.example.carappwswork.core.Constants
import com.example.carappwswork.databinding.ActivityDetailCarBinding
import com.example.carappwswork.extensions.configuraImagemCarro
import com.example.carappwswork.extensions.toast
import com.example.carappwswork.model.CarListItem
import com.example.carappwswork.model.CarUsuario
import kotlinx.coroutines.launch

class CarDetailActivity : AppCompatActivity() {

    private var car: CarListItem? = null

    private val binding by lazy {
        ActivityDetailCarBinding.inflate(layoutInflater)
    }

    private val usuarioDao by lazy {
        AppDataBase.instancia(this).usuarioDao()
    }

    private val carUsuarioDao by lazy {
        AppDataBase.instancia(this).carUsuarioDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        intent.getParcelableExtra<CarListItem>(Constants.CAR_ADAPTER_ITEM)?.let {
            car = it
        }
        setCar()
        configuraBotao()
    }
    private fun configuraBotao() {
        val botaoEuQuero = binding.buttonEuqueroDetail
        botaoEuQuero.setOnClickListener {
            openDialog()
        }
    }
    private fun openDialog() {
        val builder = AlertDialog.Builder(this, R.style.AlertDialogCustom)

        with(builder) {
            setTitle(getString(R.string.confirme_sua_compra_dialog))
            setPositiveButton(getString(R.string.confirmar)) { _, _ ->
                lifecycleScope.launch {
                    usuarioDao.buscaPorUsuarioAutenticado()?.let {
                        car?.run {
                            val carUsuario = CarUsuario(
                                usuarioId = it.id,
                                carId = id,
                                usuarioNome = it.nome,
                                usuarioEmail = it.email
                            )
                            carUsuarioDao.salva(carUsuario)
                        }
                    }
                }
                toast(getString(R.string.compra_confirma_dialog))
                finish()
            }
            setNegativeButton(getString(R.string.cancel)) { _, _ ->
            }
            show()
        }
    }
    private fun setCar() = with(binding){
        car?.run {
            carMarcaEModeloDetail.text = getString(R.string.marca_modelo,marca_nome,nome_modelo)
            combustivelDetail.text = "Combustível: " + combustivel
            portasDetail.text = num_portas.toString() + " Portas"
            carImageDetail.configuraImagemCarro(nome_modelo)
        }
    }
}