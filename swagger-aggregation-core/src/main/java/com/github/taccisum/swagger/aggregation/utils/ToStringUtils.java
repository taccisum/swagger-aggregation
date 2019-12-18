package com.github.taccisum.swagger.aggregation.utils;

import springfox.documentation.swagger.web.SwaggerResource;

/**
 * @author taccisum - liaojinfeng@deepexi.com
 * @since 2019-12-18
 */
public abstract class ToStringUtils {
    public static String toS(SwaggerResource resource) {
        return String.format("name: %s, url: %s, version: %s", resource.getName(), resource.getUrl(), resource.getSwaggerVersion());
    }
}
