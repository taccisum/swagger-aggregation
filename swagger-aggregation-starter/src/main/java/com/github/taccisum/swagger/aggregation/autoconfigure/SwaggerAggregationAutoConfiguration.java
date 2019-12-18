package com.github.taccisum.swagger.aggregation.autoconfigure;

import com.github.taccisum.swagger.aggregation.ReactiveSwaggerResourceAggregator;
import com.github.taccisum.swagger.aggregation.ReactiveSwaggerResourceController;
import com.github.taccisum.swagger.aggregation.ReactiveSwaggerResourceProvider;
import com.github.taccisum.swagger.aggregation.SwaggerResourceAggregator;
import com.github.taccisum.swagger.aggregation.provider.PropertiesSwaggerResourceProvider;
import com.github.taccisum.swagger.aggregation.support.sc.gateway.DiscoverySwaggerResourceProvider;
import com.github.taccisum.swagger.aggregation.support.sc.gateway.ScGatewaySwaggerSupportIndicator;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.List;

/**
 * @author taccisum - liaojinfeng@deepexi.com
 * @since 2019-12-16
 */
@ImportAutoConfiguration({
        SwaggerAggregationAutoConfiguration.ScGatewaySupportAutoConfiguration.class,
        SwaggerAggregationAutoConfiguration.PropertiesSwaggerResourceAutoConfiguration.class,
        SwaggerAggregationAutoConfiguration.SupportReactiveAutoConfiguration.class
})
public class SwaggerAggregationAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public SwaggerResourceAggregator swaggerResourceAggregator(List<SwaggerResourcesProvider> providers) {
        return new SwaggerResourceAggregator(providers);
    }

    @EnableConfigurationProperties(SwaggerAggregationProperties.class)
    public static class PropertiesSwaggerResourceAutoConfiguration {
        @Bean
        public SwaggerResourcesProvider propertiesSwaggerResourceProvider(SwaggerAggregationProperties properties) {
            return new PropertiesSwaggerResourceProvider(properties);
        }
    }

    @ConditionalOnClass(Mono.class)
    public static class SupportReactiveAutoConfiguration {
        @Bean
        @ConditionalOnMissingBean
        public ReactiveSwaggerResourceAggregator reactiveSwaggerResourceAggregator(List<ReactiveSwaggerResourceProvider> providers) {
            return new ReactiveSwaggerResourceAggregator(providers);
        }

        @Bean
        @ConditionalOnMissingBean
        public ReactiveSwaggerResourceController swaggerResourceController() {
            return new ReactiveSwaggerResourceController();
        }
    }

    @ConditionalOnClass(ScGatewaySwaggerSupportIndicator.class)
    @ConditionalOnProperty(value = "swagger.aggregation.discovery.enabled", havingValue = "true", matchIfMissing = true)
    public static class ScGatewaySupportAutoConfiguration {
        @Bean
        public ReactiveSwaggerResourceProvider discoverySwaggerResourceProvider(RouteDefinitionLocator definitionLocator) {
            return new DiscoverySwaggerResourceProvider(definitionLocator);
        }
    }
}
