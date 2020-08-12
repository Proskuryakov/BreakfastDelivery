package ru.relex.delivery.services.model.dish;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;
import ru.relex.delivery.commons.model.DishType;
import ru.relex.delivery.services.validation.ValidationErrorsDish;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Value.Immutable
@JsonDeserialize(builder = ImmutableUpdatableDish.Builder.class)
public interface UpdatableDish {




    @Nullable
    String getDishName();

    @Nullable
    Integer getDishPrice();

    @Nullable
    DishType getDishType();

    @Nullable
    Integer getDishCalories();

    @Nullable
    Integer getDishCookingTimeMinutes();

    @Nullable
    String getDishImage();
}
