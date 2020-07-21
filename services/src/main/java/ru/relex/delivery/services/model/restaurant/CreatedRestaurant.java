package ru.relex.delivery.services.model.restaurant;

import org.immutables.value.Value;

@Value.Immutable
public interface CreatedRestaurant extends BaseRestaurant{

  long getId();

}
