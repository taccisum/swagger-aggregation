package com.github.taccisum.swagger.aggregation.support.sc.gateway;

import com.github.taccisum.swagger.aggregation.support.sc.gateway.extractor.DiscoverySwaggerResourceExtractor;
import org.junit.Test;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import springfox.documentation.swagger.web.SwaggerResource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DiscoverySwaggerResourceProviderTest {
    private RouteDefinitionLocator locator = mock(RouteDefinitionLocator.class);
    private DiscoverySwaggerResourceExtractor extractor = mock(DiscoverySwaggerResourceExtractor.class);

    @Test
    public void get() {
        RouteDefinition def1 = mock(RouteDefinition.class);
        RouteDefinition def2 = mock(RouteDefinition.class);

        when(locator.getRouteDefinitions()).thenReturn(
                Flux.just(def1, def2)
                        .subscribeOn(Schedulers.parallel())
        );
        when(extractor.extract(any())).thenReturn(new SwaggerResource());

        DiscoverySwaggerResourceProvider provider = new DiscoverySwaggerResourceProvider(locator);
        provider.setExtractor(extractor);

        List<SwaggerResource> resources = provider.get();
        assertThat(resources.size()).isEqualTo(2);
    }
}