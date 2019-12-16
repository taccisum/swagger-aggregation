package com.github.taccisum.swagger.aggregation.provider;

import com.github.taccisum.swagger.aggregation.SwaggerResourceExtractor;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author taccisum - liaojinfeng@deepexi.com
 * @since 2019-12-16
 */
public class PropertiesSwaggerResourceProvider implements SwaggerResourcesProvider {
    private SwaggerResourceProperties properties;
    private SwaggerResourceExtractor<SwaggerResourceProperties.SwaggerResourceDefinition> extractor;

    public PropertiesSwaggerResourceProvider(SwaggerResourceProperties properties) {
        this.properties = properties;
        this.extractor = new PropertiesSwaggerResourceExtractor();
    }

    @Override
    public List<SwaggerResource> get() {
        return properties.getResources()
                .stream()
                .map(resource -> extractor.extract(resource))
                .collect(Collectors.toList());
    }
}
