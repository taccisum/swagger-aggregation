package com.github.taccisum.swagger.aggregation.support.sc.gateway;

import com.github.taccisum.swagger.aggregation.SwaggerResourceExtractor;
import com.github.taccisum.swagger.aggregation.support.sc.gateway.extractor.DiscoverySwaggerResourceExtractor;
import org.springframework.cloud.gateway.route.RouteDefinition;
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
    private SwaggerResourceExtractor<RouteDefinition> extractor;

    public DiscoverySwaggerResourceProvider(RouteDefinitionLocator definitionLocator) {
        this.definitionLocator = definitionLocator;
        this.extractor = new DiscoverySwaggerResourceExtractor();
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> ls = new ArrayList<>();
        definitionLocator.getRouteDefinitions().subscribe(definition -> {
            SwaggerResource resource = this.extractor.extract(definition);
            if (resource != null) {
                ls.add(resource);
            }
        });
        return ls;
    }

    public void setExtractor(SwaggerResourceExtractor<RouteDefinition> extractor) {
        this.extractor = extractor;
    }
}
