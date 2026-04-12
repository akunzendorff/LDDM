package models

import org.jetbrains.exposed.sql.Table
import kotlinx.serialization.Serializable

object Authors : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 100)
    override val primaryKey = PrimaryKey(id)
}

object Books : Table() {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 255)
    val authorId = integer("author_id") references Authors.id
    override val primaryKey = PrimaryKey(id)
}

@Serializable
data class Author(val id: Int? = null, val name: String)

@Serializable
data class Book(val id: Int? = null, val title: String, val authorId: Int)