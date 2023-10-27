package com.generali.mailservice.multipledatasources.product;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.generali.mailservice.multipledatasources.product",
  entityManagerFactoryRef = "productEntityManagerFactory",
  transactionManagerRef = "productTransactionManager"
)
public class ProductDataSourceConfig {

  @Bean
  @ConfigurationProperties("spring.datasource.product")
  public DataSourceProperties productDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @ConfigurationProperties("spring.datasource.product.configuration")
  public DataSource productDataSource() {
    return productDataSourceProperties()
      .initializeDataSourceBuilder()
      .type(HikariDataSource.class)
      .build();
  }

  @Bean(name = "productEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean productEntityManagerFactory(EntityManagerFactoryBuilder builder){
    return builder
      .dataSource(productDataSource())
      .packages(Product.class)
      .build();
  }

  @Bean
  public PlatformTransactionManager productTransactionManager(
    final @Qualifier("productEntityManagerFactory") LocalContainerEntityManagerFactoryBean productEntityManagerFactory){
    return new JpaTransactionManager(productEntityManagerFactory.getObject());
  }
}
