package repositories

import models.Author
import models.Authors
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class AuthorRepository {
    fun getAll(): List<Author> = transaction {
        Authors.selectAll().map {
            Author(it[Authors.id], it[Authors.name])
        }
    }

    fun getById(id: Int): Author? = transaction {
        Authors.selectAll().where { Authors.id eq id }
            .map { Author(it[Authors.id], it[Authors.name]) }
            .singleOrNull()
    }

    fun create(author: Author) = transaction {
        Authors.insert {
            it[name] = author.name
        }
    }

    fun update(id: Int, author: Author) = transaction {
        Authors.update({ Authors.id eq id }) {
            it[name] = author.name
        }
    }

    fun delete(id: Int) = transaction {
        Authors.deleteWhere { Authors.id eq id }
    }
}