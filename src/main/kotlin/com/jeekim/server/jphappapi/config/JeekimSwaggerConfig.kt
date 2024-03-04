package com.jeekim.server.jphappapi.config

import io.swagger.v3.oas.annotations.servers.Server
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JeekimSwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI {
        val securitySchemeName = "hospitalKeyAuth"
        return OpenAPI()
            .info(apiInfo())
            .addSecurityItem(SecurityRequirement().addList(securitySchemeName))
            .components(
                io.swagger.v3.oas.models.Components()
                    .addSecuritySchemes(securitySchemeName,
                        SecurityScheme()
                            .name("hospital-key")
                            .type(SecurityScheme.Type.APIKEY)
                            .`in`(SecurityScheme.In.HEADER)
                            .description("Hospital Key Authentication")
                    )
            )
            .servers(listOf(io.swagger.v3.oas.models.servers.Server().url("https://dev-jph.server-jeekim.com")))
    }

    private fun apiInfo(): Info = Info()
        .title("지킴 내가 먹는 약 API")
        .description("지킴 내가 먹는 약 API 목록")
        .version("1.0.0")
}