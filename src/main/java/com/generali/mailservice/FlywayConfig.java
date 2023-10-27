package com.generali.mailservice;

import com.generali.mailservice.mail.MailJpaConfig;
import com.generali.mailservice.multipledatasources.order.OrderDataSourceConfig;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.*;
import org.flywaydb.core.api.callback.Callback;
import org.flywaydb.core.api.migration.JavaMigration;
import org.flywaydb.core.api.pattern.ValidatePattern;
import org.flywaydb.core.api.resolver.MigrationResolver;
import org.flywaydb.core.internal.configuration.models.ConfigurationModel;
import org.flywaydb.core.internal.plugin.PluginRegister;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class FlywayConfig {
    private final MailJpaConfig mailJpaConfig;
    private final OrderDataSourceConfig orderDataSourceConfig;

    @Bean(initMethod = "migrate")
    Flyway configureMailFlyway() {
        return Flyway.configure()
                .dataSource(mailJpaConfig.mailDataSource())
//                .table("flyway_mail")
                .locations("db/migration/mails")
                .load();
    }

    @Bean(initMethod = "migrate")
    Flyway configureOrderFlyway() {
        return Flyway.configure()
                .dataSource(orderDataSourceConfig.orderDataSource())
                .locations("db/migration/orders")
                .load();
    }
}
