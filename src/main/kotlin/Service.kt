package service

import io.ktor.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080, watchPaths = listOf("ServiceKt"), module = Application::module).start()
}