package com.github.taccisum.swagger.aggregation.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author taccisum - liaojinfeng@deepexi.com
 * @since 2019-12-16
 */
@ConfigurationProperties("swagger.aggregation")
@Data
public class SwaggerAggregationProperties extends com.github.taccisum.swagger.aggregation.provider.SwaggerResourceProperties {
    private DiscoveryProperties discovery = new DiscoveryProperties();

    @Data
    public static class DiscoveryProperties {
        private Boolean enabled = true;
    }
}
