package com.github.taccisum.swagger.aggregation.provider;

import com.github.taccisum.swagger.aggregation.SwaggerResourceExtractor;
import springfox.documentation.swagger.web.SwaggerResource;

/**
 * @author taccisum - liaojinfeng@deepexi.com
 * @since 2019-12-16
 */
public class PropertiesSwaggerResourceExtractor implements SwaggerResourceExtractor<SwaggerResourceProperties.SwaggerResourceDefinition> {
    @Override
    public SwaggerResource extract(SwaggerResourceProperties.SwaggerResourceDefinition source) {
        SwaggerResource resource = new SwaggerResource();
        resource.setName(source.getName());
        resource.setLocation(source.getUrl());
        resource.setSwaggerVersion(source.getSwaggerVersion());
        return resource;
    }
}
