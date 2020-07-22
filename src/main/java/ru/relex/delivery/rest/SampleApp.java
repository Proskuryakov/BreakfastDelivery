package ru.relex.delivery.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.relex.delivery.services.ServicesConfiguration;
import ru.relex.delivery.services.model.order.PositionInOrder;

@SpringBootApplication
@Import(ServicesConfiguration.class)
 public class SampleApp {

  public static void main(String[] args) {

    SpringApplication.run(SampleApp.class, args);
  }
}
