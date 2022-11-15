package com.example.carappwswork.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.carappwswork.Database.AppDataBase
import com.example.carappwswork.R
import com.example.carappwswork.adapter.ListCarAdapter
import com.example.carappwswork.databinding.ActivityMainBinding
import com.example.carappwswork.extensions.vaiPara
import com.example.carappwswork.model.AdapterItem
import com.example.carappwswork.model.CarListItem
import com.example.carappwswork.model.HeaderItem
import com.example.carappwswork.network.services.CarAPI
import com.example.carappwswork.worker.SincronizaDadosWorker
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var adapter = ListCarAdapter(this)

    private val BASE_URL = "https://wswork.com.br/"

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val usuarioDao by lazy {
        AppDataBase.instancia(this).usuarioDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        getMyData()
        verificaUsuarioLogado()
        configuraSwipeRefresh()
        SincronizaDadosWorker.sincroniza(this)
    }


    private fun verificaUsuarioLogado() {
        lifecycleScope.launch {
            if (!usuarioDao.usuarioAutenticado()) {
                vaiPara(LoginActivity::class.java)
                finish()
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_desloga -> {
                lifecycleScope.launch {
                    usuarioDao.desautenticaUsuario()
                    runOnUiThread {
                        vaiPara(LoginActivity::class.java,true)
                        finish()
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.activityMainRecyclerView
        recyclerView.adapter = adapter
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
                )
            )
            .build()
            .create(CarAPI::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<CarListItem>> {
            override fun onResponse(
                call: Call<List<CarListItem>>,
                response: Response<List<CarListItem>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val adapterList = mutableListOf<AdapterItem>().apply {
                            add(0, HeaderItem(getString(R.string.escolha_seu_carro)))
                            addAll(it)
                        }
                        adapter = ListCarAdapter(baseContext, adapterList)
                        adapter.notifyDataSetChanged()
                        binding.activityMainRecyclerView.adapter = adapter
                    }
                } else {
                    Log.i("APIConnect", "onResponse: error")
                }
                binding.activityMainSwipeRefresh.isRefreshing = false
            }

            override fun onFailure(call: Call<List<CarListItem>>, t: Throwable) {
                Log.d("APIConnect", "onFailure: " + t.message)
                binding.activityMainSwipeRefresh.isRefreshing = false
            }
        })
    }
    private fun configuraSwipeRefresh() {
        binding.activityMainSwipeRefresh.setOnRefreshListener {
            lifecycleScope.launch{
                getMyData()
            }
        }
    }
}