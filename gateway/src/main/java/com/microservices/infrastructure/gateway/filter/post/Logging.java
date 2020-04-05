package com.microservices.infrastructure.gateway.filter.post;

import com.microservices.infrastructure.gateway.filter.constant.HeaderNames;
import com.microservices.infrastructure.gateway.filter.post.Logging.Config;
import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class Logging extends AbstractGatewayFilterFactory<Config> {
    public Logging() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return this::filter;
    }

    public static class Config {
        String name = "Logging";
    }

    private Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String correlationId = exchange.getRequest().getHeaders().getFirst(HeaderNames.CORRELATION_ID);
        String beginTime = exchange.getRequest().getHeaders().getFirst(HeaderNames.BEGIN_TIME);

        assert beginTime != null;
        int responseElapsedTime = Instant.now().compareTo(Instant.parse(beginTime));

        log.debug(String.format("Response with correlation id (%s) has used %d milliseconds", correlationId, responseElapsedTime));

        return chain.filter(exchange);
    }
}
