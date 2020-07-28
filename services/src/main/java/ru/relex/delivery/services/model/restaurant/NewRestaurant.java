package ru.relex.delivery.services.model.restaurant;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableNewRestaurant.Builder.class)
public interface NewRestaurant extends BaseRestaurant{
}
