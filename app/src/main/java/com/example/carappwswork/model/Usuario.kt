package com.example.carappwswork.model
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Usuario(
    @Expose
    @ColumnInfo(name="id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @Expose
    val email: String,
    @Expose
    val nome: String,
    @Expose
    val telefone: String,
    val senha: String,
    val usuarioAutenticado: Boolean = false,

) : Parcelable
