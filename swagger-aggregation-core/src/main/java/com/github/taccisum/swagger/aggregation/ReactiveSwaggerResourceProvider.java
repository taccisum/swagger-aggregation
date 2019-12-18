package com.github.taccisum.swagger.aggregation;

import reactor.core.publisher.Flux;
import springfox.documentation.swagger.web.SwaggerResource;

/**
 * @author taccisum - liaojinfeng@deepexi.com
 * @since 2019-12-18
 */
public interface ReactiveSwaggerResourceProvider {
    Flux<SwaggerResource> get();
}
