package com.github.taccisum.swagger.aggregation.support.sc.gateway;

import com.github.taccisum.swagger.aggregation.ReactiveSwaggerResourceProvider;
import com.github.taccisum.swagger.aggregation.SwaggerResourceExtractor;
import com.github.taccisum.swagger.aggregation.support.sc.gateway.extractor.DiscoverySwaggerResourceExtractor;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import reactor.core.publisher.Flux;
import springfox.documentation.swagger.web.SwaggerResource;

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
            return definitionLocator
                    .getRouteDefinitions()
                    .map(definition -> this.extractor.extract(definition));
        }
    }

    public void setExtractor(SwaggerResourceExtractor<RouteDefinition> extractor) {
        this.extractor = extractor;
    }
}
