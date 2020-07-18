package ru.relex.delivery.services.model.dish;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableNewDish.Builder.class)
public interface NewDish extends BaseDish {

}
