package com.springcloud.ssadhuhanv2.apigateway;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder) {

        Function<PredicateSpec, Buildable<Route>> routeFunctionHttpbin =
                //http://localhost:8765/get
                p -> p.path("/get")
                        .filters(f -> f.addRequestHeader("CustomHeader", "My Custom Header")
                                .addRequestParameter("CustomParameter", "CustomParameterValue"))
                        .uri("http://httpbin.org:80");


        //URL Shortening
        // This will enable calling the urls of "human-records" directly without using "human-records" domain name
        // Example below urls:
        //http://localhost:8765/human-records/human/1/age
        //http://localhost:8765/human-records/human/1
        // can now be accessed using
        //http://localhost:8765/human/1/age
        //http://localhost:8765/human/1
        Function<PredicateSpec, Buildable<Route>> routeFunctionForHumanRecordsApp =
                p -> p.path("/human/**")
                        .uri("lb://human-records");


        // URL Rewriting
        // Incase a user mistypes "superhuman" instead of "human"
        // They will be redirected to the human url
        //http://localhost:8765/superhuman/1/age
        //http://localhost:8765/superhuman/1
        Function<PredicateSpec, Buildable<Route>> routeFunctionForHumanRecordsAppTypo =
                //http://localhost:8765/get
                p -> p.path("/superhuman/**")
                        .filters(f -> f.rewritePath("/superhuman/(?<segment>.*)", "/human/${segment}"))
                        .uri("lb://human-records");

        //Path Rewriting
        return routeLocatorBuilder.routes()
                .route(routeFunctionHttpbin)
                .route(routeFunctionForHumanRecordsApp)
                .route(routeFunctionForHumanRecordsAppTypo)
                .build();
    }
}
