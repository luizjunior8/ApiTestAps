package com.apideaps.test.model

import javax.persistence.*

@Entity
class Genero(
    @Column(nullable = false)
    val nome: String,
    @Column(nullable = true)
    val descricao: String?
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}