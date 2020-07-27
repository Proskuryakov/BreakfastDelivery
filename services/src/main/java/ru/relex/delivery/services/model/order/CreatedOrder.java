package ru.relex.delivery.services.model.order;

import org.immutables.value.Value;
import ru.relex.delivery.commons.model.StatusesOfOrder;

import java.time.Instant;

@Value.Immutable
public interface CreatedOrder extends BaseOrder {
    Instant getCreatedAt();

    long getId();

    StatusesOfOrder getStatus();

    double getCheck();
}
