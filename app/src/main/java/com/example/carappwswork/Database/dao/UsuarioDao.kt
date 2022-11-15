package com.example.carappwswork.Database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.carappwswork.model.CarUsuario
import com.example.carappwswork.model.Usuario

@Dao
interface UsuarioDao {

    @Query("SELECT EXISTS (SELECT 1 FROM Usuario WHERE email = :email)")
    suspend fun usuarioCadastrado(email: String): Boolean

    @Insert
     suspend fun salva(usuario: Usuario)

    @Query("SELECT * FROM Usuario WHERE email = :email AND senha = :senha")
    suspend fun autentica(email: String, senha: String): Usuario?

    @Query("UPDATE Usuario SET usuarioAutenticado = 0")
    suspend fun desautenticaUsuario()

    @Query("SELECT EXISTS (SELECT 1 FROM Usuario WHERE usuarioAutenticado = 1)")
    suspend fun usuarioAutenticado(): Boolean

    @Query("SELECT * FROM Usuario WHERE usuarioAutenticado = 1 LIMIT 1")
    suspend fun buscaPorUsuarioAutenticado(): Usuario?

    @Query("SELECT id FROM Usuario WHERE usuarioAutenticado = 1 LIMIT 1")
    suspend fun buscaPorUsuarioIdAutenticado(): Int?

    @Update
    suspend fun update(usuario: Usuario)

    @Update
    suspend fun atualizaUsuarios(usuarios: List<Usuario>)

    @Query("SELECT * FROM CarUsuario WHERE sincronizadoAPI = 0")
    suspend fun buscaCarros(): List<CarUsuario>

    @Query("SELECT * FROM Usuario WHERE id = :id LIMIT 1")
    suspend fun buscaUsuarioPorId(id: Int): Usuario?

}