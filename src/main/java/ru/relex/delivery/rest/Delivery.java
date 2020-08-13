package ru.relex.delivery.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.relex.delivery.security.SecurityConfig;
import ru.relex.delivery.services.ServicesConfiguration;
import ru.relex.delivery.web.WebConfiguration;

@SpringBootApplication
@Import(
  {ServicesConfiguration.class, WebConfiguration.class, WebConfig.class, SecurityConfig.class}
  )
@ComponentScan(basePackages = {"ru.relex.delivery.web.handler", "ru.relex.delivery.rest.api"})
public class Delivery {

  public static void main(String[] args) {

    SpringApplication.run(Delivery.class, args);
  }
}
