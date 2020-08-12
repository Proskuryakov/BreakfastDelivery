package ru.relex.delivery.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.relex.delivery.security.SecurityConfig;
import ru.relex.delivery.services.ServicesConfiguration;

@SpringBootApplication
@Import({
        ServicesConfiguration.class,
        SecurityConfig.class
})
public class SampleApp {

  public static void main(String[] args) {

    SpringApplication.run(SampleApp.class, args);
  }

  /*@Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurerAdapter() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:4200");
      }
    };
  }*/
}
