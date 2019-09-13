package service

import io.ktor.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    val config = Config()
    initMetrics()
    initLogger(config)

    embeddedServer(Netty, 8080, module = appModule(config)).start(wait = true)
}