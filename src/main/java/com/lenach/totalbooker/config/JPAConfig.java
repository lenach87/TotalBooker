package com.lenach.totalbooker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Properties;

/**
 * Created by o.chubukina on 09/05/2017.
 */


@EnableJpaRepositories("com.lenach.totalbooker.repository")
@PropertySource("classpath:local-db.properties")
@Order(2)
@Configuration
public class JPAConfig {

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

    private final DriverManagerDataSource jdbcDataSource;

    @Autowired
    public JPAConfig (DriverManagerDataSource jdbcDataSource) {
        this.jdbcDataSource = jdbcDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(jdbcDataSource);
        factoryBean.setPersistenceUnitName("BookingJPA");
        factoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter());
        factoryBean.setPackagesToScan("com.lenach.totalbooker");
        factoryBean.setJpaProperties(getProperties());

        return factoryBean;
    }


    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }

    @Bean
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform(dialect);

        return adapter;
    }

    private Properties getProperties() {
        Properties jpaProp = new Properties();

        jpaProp.put("hibernate.dialect", dialect);
        jpaProp.put("hibernate.show_sql", show_sql);
        jpaProp.put("hibernate.connection.charset", charset);
        jpaProp.put("hibernate.connection.release_mode", release_mode);
        jpaProp.put("javax.persistence.validation.mode", validation_mode);

        return jpaProp;
    }
}
