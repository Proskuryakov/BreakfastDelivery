package ru.relex.delivery.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.relex.delivery.db.DatabaseConfig;
import ru.relex.delivery.services.ServicesConfiguration;

@SpringBootApplication
@Import(
  ServicesConfiguration.class
  )
public class Delivery {

  public static void main(String[] args) {

    SpringApplication.run(Delivery.class, args);
  }
}