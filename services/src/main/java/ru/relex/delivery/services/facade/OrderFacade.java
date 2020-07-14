package ru.relex.delivery.services.facade;

import ru.relex.delivery.services.model.order.CreatedOrder;
import ru.relex.delivery.services.model.order.NewOrder;

 public interface OrderFacade {
    CreatedOrder createOrder(NewOrder order);

    CreatedOrder getOrderById(long id);

    boolean deleteOrderById(long id);
}
