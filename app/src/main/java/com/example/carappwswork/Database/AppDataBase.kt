package com.example.carappwswork.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.carappwswork.Database.dao.CarUsuarioDao
import com.example.carappwswork.Database.dao.UsuarioDao
import com.example.carappwswork.model.CarUsuario
import com.example.carappwswork.model.Usuario

@Database(
    entities = [
        CarUsuario::class,
        Usuario::class
    ],
    version = 1,
    exportSchema = false
)

abstract class AppDataBase : RoomDatabase(){

    abstract fun usuarioDao(): UsuarioDao

    abstract fun carUsuarioDao(): CarUsuarioDao

    companion object{
        @Volatile
        private var db: AppDataBase? = null
        fun instancia(context: Context): AppDataBase{
            return db ?: Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                "carApp.db"
            ).build()
        }
    }
}