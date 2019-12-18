package com.github.taccisum.swagger.aggregation;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import springfox.documentation.swagger.web.SwaggerResource;

import java.util.List;

/**
 * @author taccisum - liaojinfeng@deepexi.com
 * @since 2019-12-16
 */
@Slf4j
public class ReactiveSwaggerResourceAggregator {
    private List<ReactiveSwaggerResourceProvider> providers;

    public ReactiveSwaggerResourceAggregator(List<ReactiveSwaggerResourceProvider> providers) {
        this.providers = providers;
    }

    public void addProvider(ReactiveSwaggerResourceProvider provider) {
        providers.add(provider);
    }

    public Flux<SwaggerResource> get() {
        Flux<SwaggerResource> resources = Flux.empty();
        if (providers == null) {
            return resources;
        } else {
            for (ReactiveSwaggerResourceProvider provider : providers) {
                resources = resources.concatWith(provider.get());
            }
        }

        return resources;
    }
}
