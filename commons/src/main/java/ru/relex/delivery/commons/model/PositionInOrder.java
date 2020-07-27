package ru.relex.delivery.commons.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;


@Value.Immutable
@JsonDeserialize(builder = ImmutablePositionInOrder.Builder.class)
 public interface PositionInOrder {
   // @JsonDeserialize(builder = ImmutableCreatedDish.Builder.class)
    //MainDishInfo getMainDishInfo();
    long getDishId();
    long getCount();
}
