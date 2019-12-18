package com.github.taccisum.swagger.aggregation;

import org.junit.Test;
import springfox.documentation.swagger.web.SwaggerResource;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class SwaggerResourceAggregatorTest {
    @Test
    public void get() {
        SwaggerResourceAggregator aggregator = new SwaggerResourceAggregator(new ArrayList<>());
        aggregator.addProvider(() -> {
            ArrayList<SwaggerResource> ls = new ArrayList<>();
            ls.add(new SwaggerResource());
            return ls;
        });
        aggregator.addProvider(() -> {
            ArrayList<SwaggerResource> ls = new ArrayList<>();
            ls.add(new SwaggerResource());
            return ls;
        });
        aggregator.addProvider(() -> {
            ArrayList<SwaggerResource> ls = new ArrayList<>();
            ls.add(new SwaggerResource());
            return ls;
        });
        assertThat(aggregator.get().size()).isEqualTo(3);
    }

    @Test
    public void getOnNoneProvider() {
        SwaggerResourceAggregator aggregator = new SwaggerResourceAggregator(new ArrayList<>());
        assertThat(aggregator.get().size()).isEqualTo(0);
    }

    @Test
    public void getOnOnlyOneProvider() {
        SwaggerResourceAggregator aggregator = new SwaggerResourceAggregator(new ArrayList<>());
        aggregator.addProvider(() -> {
            ArrayList<SwaggerResource> ls = new ArrayList<>();
            ls.add(new SwaggerResource());
            return ls;
        });
        assertThat(aggregator.get().size()).isEqualTo(1);
    }
}