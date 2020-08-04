package ru.relex.delivery.services.model.dishesFromBasket;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDishesFromBasketIds.Builder.class)

public interface DishesFromBasketIds {
    long getUserId();

    long getDishId();
}
