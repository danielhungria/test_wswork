package com.example.carappwswork.extensions

import android.widget.ImageView
import com.example.carappwswork.R

fun ImageView.configuraImagemCarro(modelo: String){
    when(modelo.uppercase()){
        "COROLLA XEI" -> setImageResource(R.drawable.corollaxei2)
        "MAVERICK" -> setImageResource(R.drawable.maverick)
        "GOL" -> setImageResource(R.drawable.vwgol)
        "FUSCA" -> setImageResource(R.drawable.fusca2)
        "KA" -> setImageResource(R.drawable.ka)
        "COROLLA" -> setImageResource(R.drawable.corolla2004)
        "UNO MILLE" -> setImageResource(R.drawable.unomille)
        "VOYAGE" -> setImageResource(R.drawable.voyage)
    }
}