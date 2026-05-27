package com.fatec.at2_base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

data class Task(val id: Int = 0, val title: String, val description: String)

object NetworkClient {
    private const val BASE_URL = "http://192.168.18.118:8080/tasks"

    suspend fun getTasks(): List<Task> = withContext(Dispatchers.IO) {
        return@withContext try {
            val responseText = URL(BASE_URL).readText()

            if (responseText.trim() == "[]" || responseText.isBlank()) return@withContext emptyList<Task>()

            val cleanText = responseText.replace("[", "").replace("]", "")
            val items = cleanText.split("},{")

            items.map { item ->
                val titleRegex = "\"title\"\\s*:\\s*\"([^\"]+)\"".toRegex()
                val descRegex = "\"description\"\\s*:\\s*\"([^\"]+)\"".toRegex()
                val idRegex = "\"id\"\\s*:\\s*(\\d+)".toRegex()

                val title = titleRegex.find(item)?.groups?.get(1)?.value ?: ""
                val desc = descRegex.find(item)?.groups?.get(1)?.value ?: ""
                val id = idRegex.find(item)?.groups?.get(1)?.value?.toInt() ?: 0

                Task(id, title, desc)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun postTask(title: String, description: String): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            val url = URL(BASE_URL)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.doOutput = true
            conn.setRequestProperty("Content-Type", "application/json")

            val jsonBody = "{\"id\":0,\"title\":\"$title\",\"description\":\"$description\"}"
            conn.outputStream.use { os ->
                os.write(jsonBody.toByteArray(Charsets.UTF_8))
            }

            conn.responseCode == HttpURLConnection.HTTP_CREATED
        } catch (e: Exception) {
            false
        }
    }
}