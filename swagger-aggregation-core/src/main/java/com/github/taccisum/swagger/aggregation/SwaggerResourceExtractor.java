package com.github.taccisum.swagger.aggregation;

import springfox.documentation.swagger.web.SwaggerResource;

/**
 * @author taccisum - liaojinfeng@deepexi.com
 * @since 2019-12-16
 */
public interface SwaggerResourceExtractor<T> {
    SwaggerResource extract(T source);
}
