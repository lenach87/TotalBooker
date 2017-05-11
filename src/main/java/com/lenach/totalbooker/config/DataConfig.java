package com.lenach.totalbooker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by o.chubukina on 09/05/2017.
 */

@Order(1)
@Configuration
@PropertySource("classpath:local-db.properties")
public class DataConfig {
    @Value("${database.driver}")
    private String jdbcDriver;
    @Value("${database.url}")
    private String jdbcURL;
    @Value("${database.username}")
    private String jdbcUsername;
    @Value("${database.password}")
    private String jdbcPassword;


    @Bean(name = "jdbcDataSource")
    public DriverManagerDataSource jdbcDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(jdbcDriver);
        driverManagerDataSource.setUrl(jdbcURL);
        driverManagerDataSource.setUsername(jdbcUsername);
        driverManagerDataSource.setPassword(jdbcPassword);

        return driverManagerDataSource;
    }
}
