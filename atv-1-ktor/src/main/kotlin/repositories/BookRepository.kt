package repositories

import models.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class BookRepository {
    fun getAll(): List<Book> = transaction {
        Books.selectAll().map { Book(it[Books.id], it[Books.title], it[Books.authorId]) }
    }

    fun create(book: Book) = transaction {
        Books.insert {
            it[title] = book.title
            it[authorId] = book.authorId
        }
    }

    fun update(id: Int, book: Book) = transaction {
        Books.update({ Books.id eq id }) {
            it[title] = book.title
            it[authorId] = book.authorId
        }
    }

    fun delete(id: Int) = transaction {
        Books.deleteWhere { Books.id eq id }
    }
}