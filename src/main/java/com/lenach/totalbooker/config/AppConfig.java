package com.lenach.totalbooker.config;

import org.springframework.context.annotation.*;


/**
 * Created by o.chubukina on 30/03/2017.
 */

@Configuration
@ComponentScan("com.lenach.totalbooker")
@Profile("local")
@Import({WebConfig.class, DataConfig.class, LiquibaseConfig.class, JPAConfig.class, SecurityConfig.class})
public class AppConfig {

}
