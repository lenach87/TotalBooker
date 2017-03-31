package com.lenach.totalbooker.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Properties;

/**
 * Created by o.chubukina on 30/03/2017.
 */

@Configuration
@PropertySource("classpath:liquibase.properties")
public class AppConfig {

    @Value("${driver}")
    private String jdbcDriver;
    @Value("${url}")
    private String jdbcURL;
    @Value("${username}")
    private String jdbcUsername;
    @Value("${password}")
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

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(changeLogFile);
        liquibase.setDataSource(myDataSource());
        return liquibase;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(myDataSource());
        factoryBean.setPersistenceUnitName("RoomJPA");
        HibernateJpaVendorAdapter adapter =  new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        factoryBean.setJpaVendorAdapter(adapter);
        factoryBean.setPackagesToScan("com.lenach.totalbooker");
        Properties jpaProp = new Properties();
        jpaProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        jpaProp.put("hibernate.hbm2ddl.auto", "update");
        jpaProp.put("hibernate.show_sql", Boolean.parseBoolean("true"));
        jpaProp.put("hibernate.connection.charset", "UTF-8");
        jpaProp.put("hibernate.connection.release_mode", "auto");
        jpaProp.put("javax.persistence.validation.mode", "callback");
        factoryBean.setJpaProperties(jpaProp);
        factoryBean.afterPropertiesSet();
        factoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());

        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }

}
