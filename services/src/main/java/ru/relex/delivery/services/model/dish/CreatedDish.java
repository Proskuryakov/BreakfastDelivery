package ru.relex.delivery.services.model.dish;

import org.immutables.value.Value;

@Value.Immutable
public interface CreatedDish extends BaseDish {

  long getId();
}
