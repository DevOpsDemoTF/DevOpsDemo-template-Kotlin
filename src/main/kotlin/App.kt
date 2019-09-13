package service

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.routing.Routing
import io.ktor.routing.get
import service.handlers.handleHealth

fun appModule(config: Config): Application.() -> Unit {
    return fun Application.() {
        install(DefaultHeaders)
        install(CallLogging)
        install(Routing) {
            get("/health") { handleHealth(config) }
        }
    }
}