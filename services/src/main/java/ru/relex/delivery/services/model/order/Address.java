package ru.relex.delivery.services.model.order;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.validator.constraints.Length;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;
import ru.relex.delivery.services.validation.ValidationErrorsOrder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
@Value.Immutable
@JsonDeserialize(builder = ImmutableAddress.Builder.class)
public interface Address {
    @Nullable
    @NotNull
    @Length(
            min = 1,
            max = 100,
            message = ValidationErrorsOrder.CITY_LENGTH_IS_INVALID
    )      String getCity();

    @Nullable
    @Length(
            min = 1,
            max = 100,
            message = ValidationErrorsOrder.STREET_LENGTH_IS_INVALID
    )
    String getStreet();
    @Nullable
    @NotNull
    @Length(
            min = 1,
            max = 10,
            message = ValidationErrorsOrder.HOUSE_LENGTH_IS_INVALID
    )      String getHouse();
    @Nullable
    @NotNull
    @Length(
            min = 1,
            max = 10,
            message = ValidationErrorsOrder.FLAT_LENGTH_IS_INVALID
    )      String getFlat();
    @Nullable
    @NotNull
    @Length(
            min = 1,
            max = 10,
            message = ValidationErrorsOrder.ENTRANCE_LENGTH_IS_INVALID
    )      String getEntrance();
    @Nullable
    @NotNull
    @Length(
            min = 1,
            max = 10,
            message = ValidationErrorsOrder.FLOOR_LENGTH_IS_INVALID
    )      String getFloor();
}
