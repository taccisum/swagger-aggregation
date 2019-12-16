package com.github.taccisum.swagger.aggregation.autoconfigure;

import com.github.taccisum.swagger.aggregation.SwaggerResourceAggregator;
import com.github.taccisum.swagger.aggregation.support.sc.gateway.DiscoverySwaggerResourceProvider;
import com.github.taccisum.swagger.aggregation.support.sc.gateway.ScGatewaySwaggerSupportIndicator;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.List;

/**
 * @author taccisum - liaojinfeng@deepexi.com
 * @since 2019-12-16
 */
@ImportAutoConfiguration({
        SwaggerAggregationAutoConfiguration.ScGatewaySupportAutoConfiguration.class
})
public class SwaggerAggregationAutoConfiguration {
    @Bean
    public SwaggerResourceAggregator swaggerResourceAggregator(List<SwaggerResourcesProvider> providers) {
        return new SwaggerResourceAggregator(providers);
    }

    @ConditionalOnClass(ScGatewaySwaggerSupportIndicator.class)
    public static class ScGatewaySupportAutoConfiguration {
        @Bean
        public SwaggerResourcesProvider swaggerResourcesProvider(RouteDefinitionLocator definitionLocator) {
            return new DiscoverySwaggerResourceProvider(definitionLocator);
        }
    }
}
