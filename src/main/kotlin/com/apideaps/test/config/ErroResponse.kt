package com.apideaps.test.config

import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError

class ErroResponse(
    val classeCausaErro: String
) {
    var erros: MutableList<ErroItem> = mutableListOf()

    fun adicionaErro(o:ObjectError){
        val campo: String = (o as FieldError).field
        val novoErro: ErroItem = ErroItem(campo, o.defaultMessage)
        erros.add(novoErro)
    }
}

class ErroItem(
    val campo: String,
    val mensagem: String?
)