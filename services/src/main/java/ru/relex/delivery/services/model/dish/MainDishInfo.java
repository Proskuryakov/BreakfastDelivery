package ru.relex.delivery.services.model.dish;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.validator.constraints.Length;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;
import ru.relex.delivery.services.validation.ValidationErrorsDish;

import javax.validation.constraints.NotNull;

@Value.Immutable
@JsonDeserialize(builder = ImmutableMainDishInfo.Builder.class)
public interface MainDishInfo {

  @Length(
    min = 1,
    max = 64,
    message = ValidationErrorsDish.DISH_NAME_LENGTH_IS_INVALID
  )
  @Nullable @NotNull(message = ValidationErrorsDish.DISH_NAME_IN_MAIN_DISH_INFO_MUST_BE_SET)
  String getDishName();

  @Nullable @NotNull(message = ValidationErrorsDish.DISH_PRICE_IN_MAIN_DISH_INFO_MUST_BE_SET)
  String getDishPrice();
}
