package com.apideaps.test.controller

import com.apideaps.test.model.Genero
import com.apideaps.test.repository.GeneroRepository
import org.springframework.http.ResponseEntity
import org.springframework.util.ClassUtils.isPresent
import org.springframework.web.bind.annotation.*
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

    @GetMapping("{id}")
    fun buscar(@PathVariable id: Long): ResponseEntity<Any>{
        val possivelGenero = repository.findById(id)
        if (possivelGenero.isPresent){
            return ResponseEntity.ok(
                GeneroResponse(possivelGenero.get())
            )
        }
        return ResponseEntity.unprocessableEntity().body(mapOf("Erro" to "Gênero não encontrado"))
    }

    @GetMapping("/any/{nome}")
    fun buscarPeloNome(@PathVariable nome: String): ResponseEntity<Any>{
        val possivelGenero = repository.buscarPeloNome(nome)
        if (possivelGenero.isPresent){
            return ResponseEntity.ok(
                possivelGenero.get()
            )
        }
        return ResponseEntity.unprocessableEntity().body(mapOf("Erro" to "Gênero não encontrado"))
    }

    @GetMapping
    fun buscarTodos(): ResponseEntity<Any>{
        val todosGeneros = repository.findAll()
        return ResponseEntity.ok(todosGeneros)
    }

    @PutMapping("{id}")
    fun atualizarGenero(
        @RequestBody @Valid request: AtualizaGeneroRequest,
        @PathVariable id: Long
    ): ResponseEntity<Any>{
        val possivelGenero = repository.findById(id)
        if (possivelGenero.isPresent){
            val genero = possivelGenero.get()
            request.atualiza(genero)
            repository.save(genero)
            return ResponseEntity.ok(possivelGenero)
        }
        return ResponseEntity.unprocessableEntity().body(mapOf("Erro" to "Gênero não encontrado pelo Id: $id"))
    }

    @DeleteMapping("{id}")
    fun deletarGenero(@PathVariable id: Long): ResponseEntity<Any> {
        val possivelGenero = repository.findById(id)
        if (possivelGenero.isPresent){
            repository.delete(possivelGenero.get())
        }
        return ResponseEntity.ok().build()
    }
}

class GeneroRequest(
    @field:NotBlank
    val nome: String,
    val descricao: String?
){
    fun toModel() = Genero(nome,descricao)
}

class GeneroResponse(
    val id: Long?,
    val nome: String,
    val descricao: String?
){
    constructor(genero: Genero) : this(
        genero.id,
        genero.nome,
        genero.descricao
    )
}

class AtualizaGeneroRequest(
    val nome: String?,
    val descricao: String?
){
    fun atualiza(genero: Genero){
        genero.nome = nome ?: genero.nome
        genero.descricao = descricao ?: genero.descricao
    }
}