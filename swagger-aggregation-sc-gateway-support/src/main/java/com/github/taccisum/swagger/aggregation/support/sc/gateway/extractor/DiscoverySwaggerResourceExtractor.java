package com.github.taccisum.swagger.aggregation.support.sc.gateway.extractor;

import com.github.taccisum.swagger.aggregation.SwaggerResourceExtractor;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import springfox.documentation.swagger.web.SwaggerResource;

/**
 * @author taccisum - liaojinfeng@deepexi.com
 * @since 2019-12-16
 */
public class DiscoverySwaggerResourceExtractor implements SwaggerResourceExtractor<RouteDefinition> {
    @Override
    public SwaggerResource extract(RouteDefinition source) {
        PredicateDefinition predicate = source.getPredicates().stream().filter(p -> p.getName().equals("Path")).findFirst().orElse(null);

        if (predicate != null) {
            SwaggerResource resource = new SwaggerResource();
            String appId = predicate.getArgs().get("pattern").replace("/**", "");
            resource.setName(appId);
            resource.setLocation(appId + "/v2/api-docs");
            resource.setSwaggerVersion("2.0");
            return resource;
        }
        return null;
    }
}
