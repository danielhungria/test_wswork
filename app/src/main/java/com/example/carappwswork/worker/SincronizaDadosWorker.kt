package com.example.carappwswork.worker

import android.content.Context
import android.util.Log
import androidx.work.*
import com.example.carappwswork.Database.AppDataBase
import com.example.carappwswork.model.CarUsuario
import com.example.carappwswork.network.services.CarAPI
import com.google.gson.GsonBuilder
import com.example.carappwswork.network.interceptor.RemoteLogInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SincronizaDadosWorker(
    private val context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(
    context,
    workerParameters
) {
    private val BASE_URL = "https://wswork.com.br/"


    override suspend fun doWork(): Result {
        return try {

            val okHttpClient = OkHttpClient().newBuilder().apply {
                interceptors().add(RemoteLogInterceptor())
            }.build()

            val retrofitBuilder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
                .client(okHttpClient)
                .build()
                .create(CarAPI::class.java)
            val usuarioDao = AppDataBase.instancia(context).usuarioDao()
            val carUsuarioDao = AppDataBase.instancia(context).carUsuarioDao()

            val leadCarros = mutableListOf<CarUsuario>()

            usuarioDao.buscaCarros().forEach {
                leadCarros.add(it)
                carUsuarioDao.atualiza(it.copy(sincronizadoAPI = true))
                if (leadCarros.isNotEmpty()){
                    retrofitBuilder.salva(leadCarros)
                    Log.i("SincronizaDadosWorker", "doWork: $leadCarros")
                }
            }
            Log.i("SincronizaDadosWorker", "doWork: success")
            Result.success()
        }catch(e: Exception){
            Log.e("SincronizaDadosWorker", "doWork: failure", e)
            Result.failure()
        }
    }

    companion object{

        private const val TAG_WORK = "UniqueWork"

        fun sincroniza(context: Context){

            val workManager: WorkManager = WorkManager.getInstance(context)
                val job = PeriodicWorkRequest.Builder(
                    SincronizaDadosWorker::class.java,
                    15,
                    TimeUnit.MINUTES
                ).build()
                workManager.enqueueUniquePeriodicWork(
                    TAG_WORK,
                    ExistingPeriodicWorkPolicy.KEEP,
                    job
                )
        }
    }

}