package ru.relex.delivery.services.internal;

import ru.relex.delivery.commons.model.StatusesOfOrder;
import ru.relex.delivery.services.model.order.CreatedOrder;
import ru.relex.delivery.services.model.order.NewOrder;
import ru.relex.delivery.services.model.order.UpdatableOrder;

import java.util.List;

public interface OrderService {
    CreatedOrder createOrder(NewOrder order);
    CreatedOrder getOrderByOrderId(long id);
    boolean deleteOrderById(long id);
    CreatedOrder updateOrder(long id, UpdatableOrder updatableOrder);
    List<CreatedOrder> getOrders();
    List<Integer> getCountOrdersByStatus( );

    CreatedOrder getOrderByUserId(long id);
}
