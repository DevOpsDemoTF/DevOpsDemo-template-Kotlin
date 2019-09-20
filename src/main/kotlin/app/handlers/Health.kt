package service.app.handlers

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineContext
import io.prometheus.client.Counter
import service.app.State

val healthCallCounter: Counter =
    Counter.build("health_counter", "Number of times the health endpoint has been called").register()

suspend fun PipelineContext<Unit, ApplicationCall>.handleHealth(state: State) {
    healthCallCounter.inc()
    return call.respond(
        if (state.healthy)
            HttpStatusCode.OK
        else
            HttpStatusCode.InternalServerError,
        ""
    )
}
