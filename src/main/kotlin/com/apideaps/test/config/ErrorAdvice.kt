package com.apideaps.test.config

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorAdvice {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun controladorDeExcecao(e: MethodArgumentNotValidException): ResponseEntity<Any>{
        val response: ErroResponse = ErroResponse(e.objectName)
        e.allErrors.forEach {
            response.adicionaErro(it)
        }
        return ResponseEntity.badRequest().body(response)
    }
}