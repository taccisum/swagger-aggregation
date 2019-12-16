package com.github.taccisum.swagger.aggregation;

import com.google.common.base.Supplier;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taccisum - liaojinfeng@deepexi.com
 * @since 2019-12-16
 */
@Slf4j
public class SwaggerResourceAggregator {
    private List<SwaggerResourcesProvider> providers;

    public SwaggerResourceAggregator(List<SwaggerResourcesProvider> providers) {
        this.providers = providers;
    }

    public void addProvider(SwaggerResourcesProvider provider) {
        providers.add(provider);
    }

    public List<SwaggerResource> get() {
        List<SwaggerResource> resources;
        if (providers == null) {
            resources = new ArrayList<>();
        } else {
            resources = providers.
                    stream()
                    .map(Supplier::get)
                    .reduce((a, b) -> {
                        List<SwaggerResource> ls = new ArrayList<>();
                        ls.addAll(a);
                        ls.addAll(b);
                        return ls;
                    })
                    .orElse(new ArrayList<>());
        }

        for (SwaggerResource resource : resources) {
            log.debug("Swagger resource found: {}", toS(resource));
        }

        return resources;
    }

    private String toS(SwaggerResource resource) {
        return String.format("name: %s, url: %s, version: %s", resource.getName(), resource.getUrl(), resource.getSwaggerVersion());
    }
}
