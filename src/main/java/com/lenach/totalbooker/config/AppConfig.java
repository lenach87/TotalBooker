package com.lenach.totalbooker.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Properties;

/**
 * Created by o.chubukina on 30/03/2017.
 */

@Configuration
@ComponentScan("com.lenach.totalbooker")
@EnableWebMvc
@EnableJpaRepositories("com.lenach.totalbooker.repository")
@PropertySource("classpath:local-db.properties")
@Profile("local")
public class AppConfig extends WebMvcConfigurerAdapter {

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
    @Value("${database.dialect}")
    private String dialect;
    @Value("${connection.charset}")
    private String charset;
    @Value("${connection.release_mode}")
    private String release_mode;
    @Value("${javax.persistence.validation.mode}")
    private String validation_mode;
    @Value("${show_sql}")
    private String show_sql;


    @Bean(name = "myDataSource")
    public DriverManagerDataSource myDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(jdbcDriver);
        driverManagerDataSource.setUrl(jdbcURL);
        driverManagerDataSource.setUsername(jdbcUsername);
        driverManagerDataSource.setPassword(jdbcPassword);

        return driverManagerDataSource;
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
        factoryBean.setPersistenceUnitName("BookingJPA");
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform(dialect);
        factoryBean.setJpaVendorAdapter(adapter);
        factoryBean.setPackagesToScan("com.lenach.totalbooker");
        Properties jpaProp = new Properties();
        jpaProp.put("hibernate.dialect", dialect);
        jpaProp.put("hibernate.show_sql", show_sql);
        jpaProp.put("hibernate.connection.charset", charset);
        jpaProp.put("hibernate.connection.release_mode", release_mode);
        jpaProp.put("javax.persistence.validation.mode", validation_mode);
        factoryBean.setJpaProperties(jpaProp);

        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }

}
