package com.lenach.totalbooker.config;

import com.lenach.totalbooker.repository.RoomRepository;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created by o.chubukina on 09/05/2017.
 */

@Order(2)
@Configuration
@PropertySource("classpath:local-db.properties")
public class LiquibaseConfig {

    @Value("${changeLogFile}")
    private String changeLogFile;

    private final DriverManagerDataSource jdbcDataSource;

    @Autowired
    public LiquibaseConfig (DriverManagerDataSource jdbcDataSource) {
        this.jdbcDataSource = jdbcDataSource;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(changeLogFile);
        liquibase.setDataSource(jdbcDataSource);
        return liquibase;
    }
}
