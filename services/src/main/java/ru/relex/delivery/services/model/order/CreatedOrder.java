package ru.relex.delivery.services.model.order;

import org.immutables.value.Value;

import java.time.Instant;

@Value.Immutable
public interface CreatedOrder extends BaseOrder {
    Instant getCreatedAt();

    long getId();

    OrderStatus getStatus();

    double getCheck();
}
