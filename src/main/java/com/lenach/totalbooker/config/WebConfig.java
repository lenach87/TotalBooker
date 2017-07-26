package com.lenach.totalbooker.config;


import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;




@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .favorPathExtension(true)
                .favorParameter(false)
                .parameterName("mediaType")
                .ignoreAcceptHeader(true)
                .useJaf(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("json", MediaType.APPLICATION_JSON);
    }

}
