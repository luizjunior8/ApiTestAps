package com.apideaps.test.model

import javax.persistence.*

@Entity
class Genero(
    @Column(nullable = false)
    var nome: String,
    @Column(nullable = true)
    var descricao: String?
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}