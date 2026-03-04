package routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.courseRoutes(
    courseRepository: CourseRepository,
    lessonRepository: LessonRepository
){
    // GET /courses = Lista de um curso
    get("/courses"){
        val courses = courseRepository.getAll()
        call.respond(courses)
    }

    // GET /courses/{id}/lessons = Lições de um curso
    get("/courses/{id}/lessons"){
        val id = call.parameters["id"]?.toIntOrNull()
        if (id == null) {
            call.respond(HttpStatusCode.BasRequest, mapOf("error" to "ID inválido"))
            return@get
        }
        val lessons = lessonRepository.getByCourseId(id)
        call.respond(lessons)
    }
}