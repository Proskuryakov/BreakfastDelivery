package ru.relex.delivery.services.model.order;

import org.immutables.value.Value;

@Value.Immutable
public interface CreatedOrder extends BaseOrder {
    String getCreatedAt();

    long getId();

    OrderStatus getStatus();

    double getCheck();
}
