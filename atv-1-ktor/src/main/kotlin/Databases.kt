import models.*
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/library_db",
        driver = "org.postgresql.Driver",
        user = "user",
        password = "password"
    )

    transaction {
        SchemaUtils.create(Authors, Books)

        if (Authors.selectAll().empty()) {
            val machadoId = Authors.insert {
                it[name] = "Machado de Assis"
            } get Authors.id

            Books.insert {
                it[title] = "Dom Casmurro"
                it[authorId] = machadoId
            }

            println("Seed data inserido com sucesso!")
        }
    }
}