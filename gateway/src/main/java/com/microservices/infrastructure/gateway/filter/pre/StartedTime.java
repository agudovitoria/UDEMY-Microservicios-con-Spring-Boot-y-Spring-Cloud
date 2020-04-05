package com.microservices.infrastructure.gateway.filter.pre;

import com.microservices.infrastructure.gateway.filter.constant.HeaderNames;
import com.microservices.infrastructure.gateway.filter.pre.StartedTime.Config;
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
public class StartedTime extends AbstractGatewayFilterFactory<Config> {
    public StartedTime() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return this::filter;
    }

    public static class Config {
        String name = "StartedTime";
    }

    private Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String requestStartTime = Instant.now().toString();

        log.debug(String.format("Request has started at %s", requestStartTime));

        ServerWebExchange modifiedExchange = exchange
            .mutate()
            .request(originalrequest -> {
                originalrequest.header(HeaderNames.BEGIN_TIME, requestStartTime);
            })
            .build();

        return chain.filter(modifiedExchange);
    }
}
