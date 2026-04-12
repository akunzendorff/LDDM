import repositories.*
import routes.*
import io.ktor.server.application.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*
import routes.authorRoutes
import routes.bookRoutes

fun Application.configureRouting() {
    val bookRepository = BookRepository()
    val authorRepository = AuthorRepository()

    routing {
        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml") {
            version = "4.15.5"
        }
        bookRoutes(bookRepository)
        authorRoutes(authorRepository)
    }
}
