package com.github.taccisum.swagger.aggregation;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author taccisum - liaojinfeng@deepexi.com
 * @since 2019-12-14
 */
public abstract class ExternalSwaggerResourceProvider implements SwaggerResourcesProvider {
    private List<ExternalSwaggerResource> resources;

    public ExternalSwaggerResourceProvider(List<ExternalSwaggerResource> resources) {
        this.resources = resources;
    }

    @Override
    public List<SwaggerResource> get() {
        return resources.stream()
                .map(this::toSwaggerResource)
                .collect(Collectors.toList());
    }

    private SwaggerResource toSwaggerResource(ExternalSwaggerResource resource) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(resource.getName());
        swaggerResource.setLocation(resource.getLocation());
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}
