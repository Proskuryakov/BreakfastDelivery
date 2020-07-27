package ru.relex.delivery.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.relex.delivery.db.DatabaseConfig;

@Import(DatabaseConfig.class)
@Configuration
@ComponentScan(basePackageClasses = ServicesConfiguration.class)
public class ServicesConfiguration {
}
