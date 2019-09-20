package service.app

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.routing.Routing
import io.ktor.routing.get
import service.Config
import service.app.handlers.handleHealth

fun appModule(config: Config): Application.() -> Unit {
    var state = State(config)

    return fun Application.() {
        install(DefaultHeaders)
        install(CallLogging)
        install(Routing) {
            get("/health") { handleHealth(state) }
        }
    }
}
