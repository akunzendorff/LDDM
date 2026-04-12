import io.ktor.server.application.*
import models.*
import repositories.*
import routes.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureHTTP()
    configureDatabases()
    configureMonitoring()
    configureRouting()
}