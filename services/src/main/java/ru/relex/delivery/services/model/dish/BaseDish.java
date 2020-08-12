package ru.relex.delivery.services.model.dish;

import org.springframework.lang.Nullable;
import ru.relex.delivery.commons.model.DishType;
import ru.relex.delivery.services.validation.ValidationErrorsDish;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface BaseDish {

  @Valid
  @Nullable @NotNull(message = ValidationErrorsDish.MAIN_DISH_INFO_MUST_BE_SET)
  MainDishInfo getMainDishInfo();

  @Nullable @NotNull(message = ValidationErrorsDish.DISH_TYPE_MUST_BE_SET)
  DishType getDishType();

  @Nullable @NotNull(message = ValidationErrorsDish.DISH_CALORIES_MUST_BE_SET)
  Integer getDishCalories();

  @Nullable @NotNull(message = ValidationErrorsDish.DISH_COOKING_TIME_MINUTES_MUST_BE_SET)
  Integer getDishCookingTimeMinutes();

  String getDishImage();

}
