package service

import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.contrib.json.JsonFormatter
import ch.qos.logback.contrib.json.classic.JsonLayout
import ch.qos.logback.core.ConsoleAppender
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


class MyJsonLayout : JsonLayout() {
    val mapper = ObjectMapper()

    init {
        this.appendLineSeparator = true
        this.includeContextName = false
        this.includeThreadName = false
        this.jsonFormatter = JsonFormatter {
            m -> mapper.writeValueAsString(m)
        }
    }

    override fun toJsonMap(event: ILoggingEvent): Map<*, *> {
        val map = LinkedHashMap<String, Any>()

        val formattedTimestamp = ZonedDateTime.ofInstant(
            Instant.ofEpochMilli(event.timeStamp),
            ZoneId.systemDefault()
        ).format( DateTimeFormatter.ISO_INSTANT)

        add("time", this.includeTimestamp, formattedTimestamp, map)
        add("level", this.includeLevel, event.level.levelStr, map)
        add("thread", this.includeThreadName, event.threadName, map)
        addMap("properties", this.includeMDC, event.mdcPropertyMap, map)
        add("logger", this.includeLoggerName, event.loggerName, map)
        add("fmsg", this.includeFormattedMessage, event.formattedMessage, map)
        add("msg", this.includeMessage, event.message, map)
        add("context", this.includeContextName, event.loggerContextVO.name, map)
        addThrowableInfo("exception", this.includeException, event, map)
        addCustomDataToJsonMap(map, event)
        return map
    }
}

fun initLogger(config: Config) {
    val lc = LoggerFactory.getILoggerFactory()
    val layout = MyJsonLayout()

    layout.context = lc as LoggerContext
    layout.start()

    val appender = ConsoleAppender<ILoggingEvent>()
    appender.context = lc
    appender.setLayout(layout)
    appender.start()

    val root = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
    root.detachAndStopAllAppenders()
    root.addAppender(appender)
    root.level = config.LOG_LEVEL
}