package com.apideaps.test.repository

import com.apideaps.test.model.Genero
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GeneroRepository : JpaRepository <Genero, Long>{
    @Query("select * from Genero where lower(nome) like lower(concat('%', :nome, '%'))", nativeQuery = true)
    fun buscarPeloNome(nome: String): Optional<List<Genero>>
}