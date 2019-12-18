package com.github.taccisum.swagger.aggregation.support.sc.gateway.extractor;

import com.github.taccisum.swagger.aggregation.SwaggerResourceExtractor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.util.StringUtils;
import springfox.documentation.swagger.web.SwaggerResource;

/**
 * @author taccisum - liaojinfeng@deepexi.com
 * @since 2019-12-16
 */
@Slf4j
public class DiscoverySwaggerResourceExtractor implements SwaggerResourceExtractor<RouteDefinition> {
    @Override
    public SwaggerResource extract(RouteDefinition source) {
        log.debug("extract swagger resource from route definition.");
        PredicateDefinition predicate = source.getPredicates().stream().filter(p -> p.getName().equals("Path")).findFirst().orElse(null);
        log.debug("predicate: {}", predicate);

        if (predicate != null) {
            SwaggerResource resource = new SwaggerResource();
            String pattern = predicate.getArgs().get("pattern");
            String appId = null;
            if (!StringUtils.isEmpty(pattern)) {
                appId = pattern.replace("/**", "");
            } else {
                log.debug("can not found swagger resource.");
                return null;
            }
            resource.setName(appId);
            resource.setLocation(appId + "/v2/api-docs");
            resource.setSwaggerVersion("2.0");
            return resource;
        } else {
            log.debug("can not found swagger resource.");
            return null;
        }
    }
}
