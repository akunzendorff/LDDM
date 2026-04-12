package routes

import models.Book
import repositories.BookRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.bookRoutes(repository: BookRepository) {
    route("/books") {
        get {
            val books = repository.getAll()
            call.respond(books)
        }

        post {
            val book = call.receive<Book>()
            repository.create(book)
            call.respond(HttpStatusCode.Created, "Livro criado com sucesso!")
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@put call.respond(HttpStatusCode.BadRequest)
            val book = call.receive<Book>()
            repository.update(id, book)
            call.respond(HttpStatusCode.OK, "Livro atualizado!")
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respond(HttpStatusCode.BadRequest)
            repository.delete(id)
            call.respond(HttpStatusCode.OK, "Livro removido!")
        }
    }
}