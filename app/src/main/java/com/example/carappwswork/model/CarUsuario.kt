package com.example.carappwswork.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(foreignKeys = [ForeignKey(
        entity = Usuario::class,
        childColumns = ["usuarioId"],
        parentColumns = ["id"]
    )])
data class CarUsuario (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @Expose
    var usuarioId: Int,
    @Expose
    val usuarioNome: String,
    @Expose
    val usuarioEmail: String,
    @Expose
    val carId: Int,
    val sincronizadoAPI: Boolean = false
)
