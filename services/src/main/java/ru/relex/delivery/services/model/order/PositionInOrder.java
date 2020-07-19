package ru.relex.delivery.services.model.order;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import ru.relex.delivery.services.model.dish.CreatedDish;
import ru.relex.delivery.services.model.dish.ImmutableCreatedDish;
import ru.relex.delivery.services.model.dish.MainDishInfo;

@Value.Immutable

@JsonDeserialize(builder = ImmutablePositionInOrder.Builder.class)
 public interface  PositionInOrder {
    @JsonDeserialize(builder = ImmutableCreatedDish.Builder.class)
    MainDishInfo getMainDishInfo();
    long getCount();
}
