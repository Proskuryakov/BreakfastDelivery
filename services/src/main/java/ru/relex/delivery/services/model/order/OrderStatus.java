package ru.relex.delivery.services.model.order;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(defaultAsDefault = true)
@JsonDeserialize(builder = ImmutableOrderStatus.Builder.class)
public interface OrderStatus {

default Statuses getStatus() {
        return Statuses.ORDER_IN_PROCESSING;
    }

}
