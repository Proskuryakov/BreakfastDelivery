package ru.relex.delivery.services.model.order;

import org.immutables.value.Value;

@Value.Immutable
public interface CreatedOrder extends BaseOrder {
    CreatedAt getCreatedAt();

    long getId();

    OrderStatus getStatus();

    double getCheck();
}
