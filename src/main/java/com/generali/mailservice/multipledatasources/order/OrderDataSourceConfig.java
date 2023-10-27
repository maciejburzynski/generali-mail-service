package com.generali.mailservice.multipledatasources.order;

import javax.sql.DataSource;

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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.generali.mailservice.multipledatasources.order",
entityManagerFactoryRef = "orderEntityManagerFactory",
  transactionManagerRef = "orderTransactionManager"
)
public class OrderDataSourceConfig {

  @Bean
  @Primary
  @ConfigurationProperties("spring.datasource.order")
  public DataSourceProperties orderDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  @ConfigurationProperties("spring.datasource.order.configuration")
  public DataSource orderDataSource() {
    return orderDataSourceProperties()
      .initializeDataSourceBuilder()
      .type(HikariDataSource.class)
      .build();
  }

  @Primary
  @Bean(name = "orderEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean orderEntityManagerFactory(EntityManagerFactoryBuilder builder){
    return builder
      .dataSource(orderDataSource())
      .packages(Order.class)
      .build();
  }

  @Bean
  @Primary
  public PlatformTransactionManager orderTransactionManager(
    final @Qualifier("orderEntityManagerFactory") LocalContainerEntityManagerFactoryBean orderEntityManagerFactory){
    return new JpaTransactionManager(orderEntityManagerFactory.getObject());
  }
}
