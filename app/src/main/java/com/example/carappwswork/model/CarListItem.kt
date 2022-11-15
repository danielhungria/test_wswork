package com.example.carappwswork.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.carappwswork.core.Constants
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class CarListItem(
    @PrimaryKey(autoGenerate = true)
    @Expose
    val id: Int,
    @Expose
    val ano: Int,
    @Expose
    val combustivel: String,
    @Expose
    val cor: String,
    @Expose
    val marca_id: Int,
    @Expose
    val marca_nome: String,
    @Expose
    val nome_modelo: String,
    @Expose
    val num_portas: Int,
    @Expose
    val timestamp_cadastro: Int,
    @Expose
    val valor_fipe: Double,
    val sincronizado: Boolean = false
) : Parcelable,AdapterItem {
    override fun itemViewType() = Constants.ADAPTER_ITEM_CAR_TYPE
}