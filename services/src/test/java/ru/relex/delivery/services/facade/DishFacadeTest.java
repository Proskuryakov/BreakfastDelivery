package ru.relex.delivery.services.facade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.relex.delivery.services.internal.DishService;
import ru.relex.delivery.services.internal.impl.DishServiceImpl;
import ru.relex.delivery.services.mapper.DishMapper;
import ru.relex.delivery.services.model.dish.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
  classes = {
    DishFacade.class
  }
)
@MockBean(
  classes = DishService.class
)
@Import(
  {
    ValidationAutoConfiguration.class
  }
)
@ComponentScan(basePackages = "ru.relex.delivery.services.facade.impl.dish")
@TestConfiguration
class DishFacadeTest {

  @Autowired
  DishFacade facade;

  private ImmutableNewDish getImmutableNewDish() {
    return ImmutableNewDish
      .builder()
      .mainDishInfo(ImmutableMainDishInfo.builder()
        .dishName("")
        .dishPrice("250")
        .build())
      .dishType(DishType.MAIN)
      .dishCalories(175)
      .dishCookingTimeMinutes(30)
      .build();
  }

  @Test
  void testThrows() {
    Assertions.assertThrows(ConstraintViolationException.class, () -> facade.createDish(getImmutableNewDish()));
  }

}
