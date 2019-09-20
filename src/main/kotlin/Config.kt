package service

import ch.qos.logback.classic.Level

class Config {
    val LOG_LEVEL = Level.toLevel(System.getenv("LOG_LEVEL"), Level.WARN) as Level
}