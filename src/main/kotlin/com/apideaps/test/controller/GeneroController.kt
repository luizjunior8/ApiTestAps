package com.apideaps.test.controller

import com.apideaps.test.model.Genero
import com.apideaps.test.repository.GeneroRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("generos")
class GeneroController(val repository : GeneroRepository) {
    @PostMapping
    fun criar(@RequestBody @Valid request: GeneroRequest): ResponseEntity<Any>{
        val generoSalvo = repository.save(request.toModel())

        val uri = UriComponentsBuilder.newInstance()
            .path("/generos/{id}")
            .buildAndExpand(generoSalvo.id)
            .toUri()

        return ResponseEntity.created(uri).body(GeneroResponse(generoSalvo))
    }
}

class GeneroRequest(
    @field:NotBlank
    val nome: String,
    val descricao: String?
){
    fun toModel() = Genero(nome,descricao)
}

class GeneroResponse (
    val id: Long?,
    val nome: String,
    val decricao: String?
){
    constructor(genero: Genero) : this(
        genero.id,
        genero.nome,
        genero.descricao
    )
}