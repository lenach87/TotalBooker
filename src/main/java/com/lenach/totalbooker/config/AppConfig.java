package com.lenach.totalbooker.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Properties;

/**
 * Created by o.chubukina on 30/03/2017.
 */

@Configuration
@ComponentScan({"com.lenach.totalbooker.repository", "com.lenach.totalbooker.service", "com.lenach.totalbooker.controllers"})
@EnableWebMvc
@EnableJpaRepositories
public class AppConfig extends WebMvcConfigurerAdapter {

    @PropertySource("classpath:local-db.properties")
    @Profile("local")
    public static class LocalConfig {

        @Value("${database.driver}")
        private String jdbcDriver;
        @Value("${database.url}")
        private String jdbcURL;
        @Value("${database.username}")
        private String jdbcUsername;
        @Value("${database.password}")
        private String jdbcPassword;
        @Value("${changeLogFile}")
        private String changeLogFile;


        @Bean(name = "myDataSource")
        public DriverManagerDataSource myDataSource() {
            DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
            driverManagerDataSource.setDriverClassName(jdbcDriver);
            driverManagerDataSource.setUrl(jdbcURL);
            driverManagerDataSource.setUsername(jdbcUsername);
            driverManagerDataSource.setPassword(jdbcPassword);

            return driverManagerDataSource;
        }

        public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
            configurer.favorPathExtension(false).
                    favorParameter(true).
                    parameterName("mediaType").
                    ignoreAcceptHeader(true).
                    useJaf(false).
                    defaultContentType(MediaType.APPLICATION_JSON).
                    mediaType("xml", MediaType.APPLICATION_XML).
                    mediaType("json", MediaType.APPLICATION_JSON);
        }

        @Bean
        public SpringLiquibase liquibase() {
            SpringLiquibase liquibase = new SpringLiquibase();
            liquibase.setChangeLog(changeLogFile);
            liquibase.setDataSource(myDataSource());
            return liquibase;
        }

        @Bean
        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
            LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
            factoryBean.setDataSource(myDataSource());
            factoryBean.setPersistenceUnitName("RoomJPA");
            HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
            adapter.setShowSql(true);
            adapter.setGenerateDdl(false);
            adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
            factoryBean.setJpaVendorAdapter(adapter);
            factoryBean.setPackagesToScan("com.lenach.totalbooker");
            Properties jpaProp = new Properties();
            jpaProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
            jpaProp.put("hibernate.show_sql", Boolean.parseBoolean("true"));
            jpaProp.put("hibernate.connection.charset", "UTF-8");
            jpaProp.put("hibernate.connection.release_mode", "auto");
            jpaProp.put("javax.persistence.validation.mode", "callback");
            factoryBean.setJpaProperties(jpaProp);
//        factoryBean.afterPropertiesSet();
//        factoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());

            return factoryBean;
        }

        @Bean
        public PlatformTransactionManager transactionManager() {
            JpaTransactionManager txManager = new JpaTransactionManager();
            txManager.setEntityManagerFactory(entityManagerFactory().getObject());
            return txManager;
        }
    }

}
