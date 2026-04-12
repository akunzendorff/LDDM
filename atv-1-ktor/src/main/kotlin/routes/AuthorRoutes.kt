package routes

import models.Author
import repositories.AuthorRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authorRoutes(repository: AuthorRepository) {
    route("/authors") {
        get {
            call.respond(repository.getAll())
        }

        post {
            val author = call.receive<Author>()
            repository.create(author)
            call.respond(HttpStatusCode.Created)
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@put call.respond(HttpStatusCode.BadRequest)
            val author = call.receive<Author>()
            repository.update(id, author)
            call.respond(HttpStatusCode.OK)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respond(HttpStatusCode.BadRequest)
            repository.delete(id)
            call.respond(HttpStatusCode.OK)
        }
    }
}