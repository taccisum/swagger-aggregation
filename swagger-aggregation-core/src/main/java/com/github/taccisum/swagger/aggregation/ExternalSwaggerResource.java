package com.github.taccisum.swagger.aggregation;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author taccisum - liaojinfeng@deepexi.com
 * @since 2019-12-14
 */
@Data
@NoArgsConstructor
@ToString
public class ExternalSwaggerResource {
    private String name;
    private String location;

    public ExternalSwaggerResource(String name, String location) {
        this.name = name;
        this.location = location;
    }
}
