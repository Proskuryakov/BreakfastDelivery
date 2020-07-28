package ru.relex.delivery.services.model.restaurant;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableUpdatableRestaurant.Builder.class)
public interface UpdatableRestaurant extends BaseRestaurant{
}
