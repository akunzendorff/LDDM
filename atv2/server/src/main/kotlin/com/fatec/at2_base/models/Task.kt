package com.fatec.at2_base.models

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: Int,
    val title: String,
    val description: String
)

// Banco de dados em memória local
val taskStorage = mutableListOf<Task>(
    Task(1, "Estudar para LDDM", "Concluir o projeto prático de Ktor e Android"),
    Task(2, "Gravar o vídeo", "Gravar a tela demonstrando o app e o backend")
)