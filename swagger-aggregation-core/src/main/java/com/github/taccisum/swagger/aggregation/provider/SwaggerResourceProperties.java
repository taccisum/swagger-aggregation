package com.github.taccisum.swagger.aggregation.provider;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taccisum - liaojinfeng@deepexi.com
 * @since 2019-12-16
 */
@Data
public class SwaggerResourceProperties {
    private List<SwaggerResourceDefinition> resources = new ArrayList<>();

    @Data
    public static class SwaggerResourceDefinition {
        private String name;
        private String url;
        private String swaggerVersion = "2.0";
    }
}
