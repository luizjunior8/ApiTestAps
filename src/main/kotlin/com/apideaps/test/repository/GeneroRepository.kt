package com.apideaps.test.repository

import com.apideaps.test.model.Genero
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GeneroRepository : JpaRepository <Genero, Long>{
}