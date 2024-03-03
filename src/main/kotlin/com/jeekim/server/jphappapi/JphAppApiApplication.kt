package com.jeekim.server.jphappapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    exclude = [
        org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration::class,
        org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration::class,
        org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration::class,
    ]
)
class JphAppApiApplication

fun main(args: Array<String>) {
    runApplication<JphAppApiApplication>(*args)
}
