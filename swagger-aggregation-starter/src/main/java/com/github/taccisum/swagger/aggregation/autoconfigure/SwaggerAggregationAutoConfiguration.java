package com.github.taccisum.swagger.aggregation.autoconfigure;

import com.github.taccisum.swagger.aggregation.SwaggerResourceAggregator;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.List;

/**
 * @author taccisum - liaojinfeng@deepexi.com
 * @since 2019-12-16
 */
public class SwaggerAggregationAutoConfiguration {
    @Bean
    public SwaggerResourceAggregator swaggerResourceAggregator(List<SwaggerResourcesProvider> providers) {
        return new SwaggerResourceAggregator(providers);
    }
}
