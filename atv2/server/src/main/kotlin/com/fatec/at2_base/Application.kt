package com.fatec.at2_base

import com.fatec.at2_base.models.taskStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    routing {
        get("/tasks") {
            val jsonItems = taskStorage.map { task ->
                "{\"id\":${task.id},\"title\":\"${task.title}\",\"description\":\"${task.description}\"}"
            }
            val jsonResponse = jsonItems.joinToString(separator = ",", prefix = "[", postfix = "]")
            call.respondText(jsonResponse, ContentType.Application.Json, HttpStatusCode.OK)
        }

        post("/tasks") {
            try {
                val bodyText = call.receiveText()

                val titleRegex = "\"title\"\\s*:\\s*\"([^\"]+)\"".toRegex()
                val descRegex = "\"description\"\\s*:\\s*\"([^\"]+)\"".toRegex()

                val titleMatch = titleRegex.find(bodyText)?.groups?.get(1)?.value ?: "Sem Título"
                val descMatch = descRegex.find(bodyText)?.groups?.get(1)?.value ?: "Sem Descrição"

                val newTask = com.fatec.at2_base.models.Task(
                    id = taskStorage.size + 1,
                    title = titleMatch,
                    description = descMatch
                )
                taskStorage.add(newTask)

                val responseJson =
                    "{\"id\":${newTask.id},\"title\":\"${newTask.title}\",\"description\":\"${newTask.description}\"}"
                call.respondText(responseJson, ContentType.Application.Json, HttpStatusCode.Created)
            } catch (e: Exception) {
                call.respondText("Erro: ${e.localizedMessage}", ContentType.Text.Plain, HttpStatusCode.BadRequest)
            }
        }
    }
}