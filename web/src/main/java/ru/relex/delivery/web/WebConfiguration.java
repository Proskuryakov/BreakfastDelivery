package ru.relex.delivery.web;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.relex.delivery.services.ServicesConfiguration;

@Configuration
@ComponentScan(basePackages = "ru.relex.delivery.web.controller")
@Import({
        ServicesConfiguration.class,
        SecurityConfig.class
})
public class WebConfiguration {
}
