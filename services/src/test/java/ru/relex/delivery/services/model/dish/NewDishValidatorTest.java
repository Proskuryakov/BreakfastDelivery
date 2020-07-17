package ru.relex.delivery.services.model.dish;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.relex.delivery.services.facade.DishFacade;
import ru.relex.delivery.services.internal.DishService;
import ru.relex.delivery.services.validation.ValidationErrorsDish;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
  classes = {
    Validator.class,
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
class NewDishValidatorTest {

  @Autowired
  Validator validator;

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
  void checkUserValid() {
    ImmutableNewDish newDish = getImmutableNewDish();

    Set<ConstraintViolation<ImmutableNewDish>> violationSet = validator.validate(newDish);

    Assertions.assertEquals(1, violationSet.size());
    Assertions.assertEquals(
      ValidationErrorsDish.DISH_NAME_LENGTH_IS_INVALID,
      violationSet.stream().findFirst().orElseThrow().getMessage()
    );
  }

}
