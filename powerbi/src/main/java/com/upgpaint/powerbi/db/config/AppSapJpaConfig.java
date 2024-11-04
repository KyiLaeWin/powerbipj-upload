package com.upgpaint.powerbi.db.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@PropertySources({ @PropertySource(value = "classpath:powerbi.properties") })
public class AppSapJpaConfig {

    @Autowired
    private Environment environment;

    // Declare a datasource that has pooling capabilities
    @Bean
    public DataSource dataSource() {
    	
    	
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass(environment.getRequiredProperty("jdbc.driverClassName"));
            ds.setJdbcUrl(environment.getRequiredProperty("jdbc.mysql.url"));
            ds.setUser(environment.getRequiredProperty("jdbc.mysql.username"));
            ds.setPassword(environment.getRequiredProperty("jdbc.mysql.password"));

            ds.setInitialPoolSize(10);
            ds.setMinPoolSize(10);
            ds.setMaxPoolSize(100);
            ds.setCheckoutTimeout(7200000);
            ds.setAcquireIncrement(5);
            ds.setMaxIdleTime(7200);
            ds.setPreferredTestQuery("SELECT 1");
            ds.setTestConnectionOnCheckout(true);
            ds.setDebugUnreturnedConnectionStackTraces(true);
            ds.setUnreturnedConnectionTimeout(7200); 
            ds.setIdleConnectionTestPeriod(7200);
            ds.setMaxIdleTimeExcessConnections(7200);
                
            return ds;
        } catch (Exception e) {
        	
            throw new RuntimeException("Failed to create data source", e);
        }
    }

    // Declare a JPA entityManagerFactory
    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setPackagesToScan("com.upgpaint.powerbi.db.entity");


        Properties jpaProperties = new Properties();

        // Configures the used database dialect. This allows Hibernate to create
        // SQL that is optimized for the used database.
        jpaProperties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));

        // Specifies the action that is invoked to the database when the
        // Hibernate SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));

        // Configures the naming strategy that is used when Hibernate creates
        // new database objects and schema elements
        jpaProperties.put("hibernate.ejb.naming_strategy",environment.getRequiredProperty("hibernate.ejb.naming_strategy"));
          // jpaProperties.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
        // If the value of this property is true, Hibernate writes all SQL
        // statements to the console.
        jpaProperties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));

        // If the value of this property is true, Hibernate will format the SQL
        // that is written to the console.
        jpaProperties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));

        jpaProperties.put("hibernate.enable_lazy_load_no_trans", true);

        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    // Declare a transaction manager
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    // Auditing
    @Bean
    public SecurityAuditor createAuditorProvider() {
        return new SecurityAuditor();
    }

    @Bean
    public AuditingEntityListener createAuditingListener() {
        return new AuditingEntityListener();
    }
}
