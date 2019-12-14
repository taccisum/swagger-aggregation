package com.github.taccisum.swagger.aggregation;

import org.junit.Test;
import springfox.documentation.swagger.web.SwaggerResource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ExternalSwaggerResourceProviderTest {
    @Test
    public void get() {
        ArrayList<ExternalSwaggerResource> resources = new ArrayList<>();
        resources.add(new ExternalSwaggerResource("app", "127.0.0.1:8080/v2/api-docs"));
        ExternalSwaggerResourceProvider provider = new ExternalSwaggerResourceProvider(resources) {
        };
        List<SwaggerResource> ls = provider.get();
        assertThat(ls.size()).isEqualTo(1);
        assertThat(ls.get(0).getName()).isEqualTo("app");
        assertThat(ls.get(0).getUrl()).isEqualTo("127.0.0.1:8080/v2/api-docs");
        assertThat(ls.get(0).getSwaggerVersion()).isEqualTo("2.0");
    }
}