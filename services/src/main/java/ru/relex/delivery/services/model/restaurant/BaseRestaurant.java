package ru.relex.delivery.services.model.restaurant;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;
import ru.relex.delivery.commons.model.RestaurantType;
import ru.relex.delivery.services.validation.ValidationErrorsRestaurant;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface BaseRestaurant {

  @Length(
    min = 1,
    max = 100,
    message = ValidationErrorsRestaurant.RESTAURANT_NAME_LENGTH_IS_INVALID
  )
  @Nullable
  @NotNull(message = ValidationErrorsRestaurant.RESTAURANT_NAME_MUST_BE_SET)
  String getRestaurantName();

  @Nullable
  @NotNull(message = ValidationErrorsRestaurant.RESTAURANT_TYPE_MUST_BE_SET)
  RestaurantType getRestaurantType();

  @Valid
  @Nullable
  @NotNull(message = ValidationErrorsRestaurant.RESTAURANT_ADDRESS_MUST_BE_SET)
  Address getAddress();

  @Valid
  @Nullable
  @NotNull(message = ValidationErrorsRestaurant.RESTAURANT_WORKING_HOURS_MUST_BE_SET)
  WorkingHours getWorkingHours();

  List<Long> getListOfDishes();

}
