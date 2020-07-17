package ru.relex.delivery.services.model.order;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableUpdatableOrder.Builder.class)
public interface UpdatableOrder extends BaseOrder{

    OrderStatus getStatus();


}
