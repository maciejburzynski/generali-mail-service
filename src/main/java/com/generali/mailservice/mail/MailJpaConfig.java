package com.generali.mailservice.mail;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.generali.mailservice.mail",
        entityManagerFactoryRef = "mailEntityManagerFactory",
        transactionManagerRef = "mailTransactionManager"
)
public class MailJpaConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.mail")
    public DataSourceProperties mailDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.mail.configuration")
    public DataSource mailDataSource() {
        return mailDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "mailEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mailEntityManagerFactory(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(mailDataSource())
                .packages(Mail.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager mailTransactionManager(
            final @Qualifier("mailEntityManagerFactory") LocalContainerEntityManagerFactoryBean mailEntityManagerFactory){
        return new JpaTransactionManager(mailEntityManagerFactory.getObject());
    }
}
