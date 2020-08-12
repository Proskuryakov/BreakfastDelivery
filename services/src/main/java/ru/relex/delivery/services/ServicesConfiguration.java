package ru.relex.delivery.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.relex.delivery.db.DatabaseConfig;

 @Configuration
@Import({DatabaseConfig.class, AWSConfiguration.class})
@ComponentScan(basePackageClasses = ServicesConfiguration.class, basePackages = "ru.relex.delivery.services.service")
public class ServicesConfiguration {
}
