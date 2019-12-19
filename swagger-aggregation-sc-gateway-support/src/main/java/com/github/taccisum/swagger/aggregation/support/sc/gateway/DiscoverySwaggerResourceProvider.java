package com.github.taccisum.swagger.aggregation.support.sc.gateway;

import com.github.taccisum.swagger.aggregation.ReactiveSwaggerResourceProvider;
import com.github.taccisum.swagger.aggregation.SwaggerResourceExtractor;
import com.github.taccisum.swagger.aggregation.support.sc.gateway.extractor.DiscoverySwaggerResourceExtractor;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import reactor.core.publisher.Flux;
import springfox.documentation.swagger.web.SwaggerResource;

import java.util.Optional;

/**
 * @author taccisum - liaojinfeng@deepexi.com
 * @since 2019-12-16
 */
public class DiscoverySwaggerResourceProvider implements ReactiveSwaggerResourceProvider {
    private RouteDefinitionLocator definitionLocator;
    private SwaggerResourceExtractor<RouteDefinition> extractor;

    public DiscoverySwaggerResourceProvider(RouteDefinitionLocator definitionLocator) {
        this.definitionLocator = definitionLocator;
        this.extractor = new DiscoverySwaggerResourceExtractor();
    }

    @Override
    public Flux<SwaggerResource> get() {
        if (definitionLocator == null) {
            return Flux.empty();
        } else {
//            return Flux.empty();
            return definitionLocator
                    .getRouteDefinitions()
                    .map(definition -> {
                        return Optional.ofNullable(this.extractor.extract(definition)).orElse(new Null());
                    })
                    .filter(resource -> {
                        return !(resource instanceof Null);
                    })
                    ;
        }
    }

    public void setExtractor(SwaggerResourceExtractor<RouteDefinition> extractor) {
        this.extractor = extractor;
    }

    private class Null extends SwaggerResource {
    }
}
