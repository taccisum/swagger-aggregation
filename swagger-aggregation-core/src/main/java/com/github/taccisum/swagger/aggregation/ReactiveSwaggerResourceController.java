package com.github.taccisum.swagger.aggregation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.*;

import java.util.Optional;

/**
 * TODO:: provide non-reactive swagger resource controller
 *
 * @author taccisum - liaojinfeng@deepexi.com
 * @since 2019-12-16
 */
@RestController
@RequestMapping("/swagger-resources")
public class ReactiveSwaggerResourceController {
    @Autowired(required = false)
    private SecurityConfiguration securityConfiguration;
    @Autowired(required = false)
    private UiConfiguration uiConfiguration;
    @Autowired(required = false)
    private SwaggerResourceAggregator swaggerResourceAggregator;
    @Autowired(required = false)
    private ReactiveSwaggerResourceAggregator reactiveSwaggerResourceAggregator;

    @GetMapping("/configuration/security")
    public Mono<SecurityConfiguration> securityConfiguration() {
        return Mono.just(Optional.ofNullable(securityConfiguration).orElse(SecurityConfigurationBuilder.builder().build()));
    }

    @GetMapping("/configuration/ui")
    public Mono<UiConfiguration> uiConfiguration() {
        return Mono.just(Optional.ofNullable(uiConfiguration).orElse(UiConfigurationBuilder.builder().build()));
    }

    @GetMapping
    public Flux<SwaggerResource> swaggerResources() {
        Flux<SwaggerResource> resources = Flux.just();
        if (swaggerResourceAggregator != null) {
            resources = resources.concatWith(Flux.just(swaggerResourceAggregator.get().toArray(new SwaggerResource[]{})));
        }
        if (reactiveSwaggerResourceAggregator != null) {
            resources = resources.concatWith(reactiveSwaggerResourceAggregator.get());
        }
        return resources;
    }
}
