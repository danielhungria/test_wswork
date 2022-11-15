package com.example.carappwswork.extensions

import android.content.Context
import android.content.Intent
import android.widget.Toast

fun Context.vaiPara(clazz: Class<*>, flagLimpar:Boolean = false) {
    Intent(this, clazz)
        .apply {
            if (flagLimpar) flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(this)
        }
}

fun Context.toast(mensagem: String){
    Toast.makeText(
        this,
        mensagem,
        Toast.LENGTH_SHORT
    ).show()
}