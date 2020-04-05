package com.microservices.infrastructure.gateway.filter.pre;

import com.microservices.infrastructure.gateway.filter.constant.HeaderNames;
import com.microservices.infrastructure.gateway.filter.pre.CorrelationId.Config;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CorrelationId extends AbstractGatewayFilterFactory<Config> {

    public CorrelationId() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return this::filter;
    }

    public static class Config {
        String name = "CorrelationId";
    }

    private Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String correlationId = UUID.randomUUID().toString();

        log.debug(String.format("Request with correlation id (%s)", correlationId));

        ServerWebExchange modifiedExchange = exchange
            .mutate()
            .request(originalrequest -> {
                originalrequest.header(HeaderNames.CORRELATION_ID, correlationId);
            })
            .build();

        return chain.filter(modifiedExchange);
    }
}
