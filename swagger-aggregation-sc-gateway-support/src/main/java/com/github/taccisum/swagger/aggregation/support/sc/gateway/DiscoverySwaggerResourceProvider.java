package com.github.taccisum.swagger.aggregation.support.sc.gateway;

import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taccisum - liaojinfeng@deepexi.com
 * @since 2019-12-16
 */
public class DiscoverySwaggerResourceProvider implements SwaggerResourcesProvider {
    private RouteDefinitionLocator definitionLocator;

    public DiscoverySwaggerResourceProvider(RouteDefinitionLocator definitionLocator) {
        this.definitionLocator = definitionLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> ls = new ArrayList<>();
        definitionLocator.getRouteDefinitions().subscribe(definition -> {
            PredicateDefinition predicate = definition.getPredicates().stream().filter(p -> p.getName().equals("Path")).findFirst().orElse(null);

            if (predicate != null) {
                SwaggerResource resource = new SwaggerResource();
                String appId = predicate.getArgs().get("pattern").replace("/**", "");
                resource.setName(appId);
                resource.setLocation(appId + "/v2/api-docs");
                resource.setSwaggerVersion("2.0");
                ls.add(resource);
            }
        });
        return ls;
    }
}
