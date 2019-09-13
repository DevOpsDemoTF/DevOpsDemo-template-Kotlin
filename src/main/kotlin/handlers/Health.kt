package service.handlers

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineContext
import io.prometheus.client.Counter
import service.Config

val healthCallCounter: Counter = io.prometheus.client.Counter.build("health_counter", "Number of times the health endpoint has been called").register()

@Suppress("UNUSED_PARAMETER")
suspend fun PipelineContext<Unit, ApplicationCall>.handleHealth(config: Config) {
    healthCallCounter.inc()
    return call.respond(HttpStatusCode.OK, "")
}
