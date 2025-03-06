package com.tudorandronic.spring_boot_library.config;

import com.tudorandronic.spring_boot_library.entity.Book;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfiguration implements RepositoryRestConfigurer {

    private String theAllowedOrigin = "http://localhost:3000";
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors){

        config.exposeIdsFor(Book.class);

        config.getExposureConfiguration()
                .forDomainType(Book.class)
                .withItemExposure((metdata, httpMethods) -> {
                    return httpMethods.disable(HttpMethod.DELETE, HttpMethod.PATCH, HttpMethod.POST, HttpMethod.PUT);
                })
                .withCollectionExposure((metdata, httpMethods) -> {
                    return httpMethods.disable(HttpMethod.DELETE, HttpMethod.PATCH, HttpMethod.POST, HttpMethod.PUT);
                });

        System.out.println(config.getBasePath());

        cors.addMapping(config.getBasePath()+"/**")
                .allowedOrigins(theAllowedOrigin);
    }

}
