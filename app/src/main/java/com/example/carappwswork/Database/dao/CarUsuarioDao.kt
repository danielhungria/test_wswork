package com.example.carappwswork.Database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import com.example.carappwswork.model.CarUsuario

@Dao
interface CarUsuarioDao {

    @Insert
    suspend fun salva(carro: CarUsuario)

    @Update
    suspend fun atualiza(carro: CarUsuario)

}