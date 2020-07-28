package ru.relex.delivery.services.model.restaurant;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.validator.constraints.Length;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;
import ru.relex.delivery.services.validation.ValidationErrorsRestaurant;

import javax.validation.constraints.NotNull;

@Value.Immutable
@JsonDeserialize(builder = ImmutableAddress.Builder.class)
public interface Address {

  @Length(
    min = 1,
    max = 150,
    message = ValidationErrorsRestaurant.RESTAURANT_STREET_LENGTH_IS_INVALID
  )
  @Nullable
  @NotNull(message = ValidationErrorsRestaurant.RESTAURANT_STREET_IN_ADDRESS_MUST_BE_SET)
  String getStreet();

  @Length(
    min = 1,
    max = 5,
    message = ValidationErrorsRestaurant.RESTAURANT_BUILDING_LENGTH_IS_INVALID
  )
  @Nullable
  @NotNull(message = ValidationErrorsRestaurant.RESTAURANT_BUILDING_IN_ADDRESS_MUST_BE_SET)
  String getBuilding();

}
