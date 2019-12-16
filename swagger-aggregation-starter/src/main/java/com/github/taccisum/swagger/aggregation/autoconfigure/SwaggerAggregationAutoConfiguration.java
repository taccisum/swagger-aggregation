package com.github.taccisum.swagger.aggregation.autoconfigure;

import com.github.taccisum.swagger.aggregation.SwaggerResourceAggregator;
import com.github.taccisum.swagger.aggregation.SwaggerResourceController;
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
        SwaggerAggregationAutoConfiguration.PropertiesSwaggerResourceAutoConfiguration.class
})
public class SwaggerAggregationAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public SwaggerResourceAggregator swaggerResourceAggregator(List<SwaggerResourcesProvider> providers) {
        return new SwaggerResourceAggregator(providers);
    }

    @Bean
    @ConditionalOnClass(Mono.class)
    @ConditionalOnMissingBean
    public SwaggerResourceController swaggerResourceController() {
        return new SwaggerResourceController();
    }

    @EnableConfigurationProperties(SwaggerAggregationProperties.class)
    public static class PropertiesSwaggerResourceAutoConfiguration {
        @Bean
        public SwaggerResourcesProvider propertiesSwaggerResourceProvider(SwaggerAggregationProperties properties) {
            return new PropertiesSwaggerResourceProvider(properties);
        }
    }

    @ConditionalOnClass(ScGatewaySwaggerSupportIndicator.class)
    @ConditionalOnProperty(value = "swagger.aggregation.discovery.enabled", havingValue = "true", matchIfMissing = true)
    public static class ScGatewaySupportAutoConfiguration {
        @Bean
        public SwaggerResourcesProvider discoverySwaggerResourceProvider(RouteDefinitionLocator definitionLocator) {
            return new DiscoverySwaggerResourceProvider(definitionLocator);
        }
    }
}
