package ru.relex.delivery.services.internal;

import ru.relex.delivery.services.model.order.CreatedOrder;
import ru.relex.delivery.services.model.order.NewOrder;

public interface OrderService {
    CreatedOrder createOrder(NewOrder order);
    CreatedOrder getOrderById(long id);
    boolean deleteOrderById(long id);
}
