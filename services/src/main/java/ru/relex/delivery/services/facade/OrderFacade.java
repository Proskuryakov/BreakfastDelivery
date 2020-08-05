package ru.relex.delivery.services.facade;

import ru.relex.delivery.services.model.order.CreatedOrder;
import ru.relex.delivery.services.model.order.NewOrder;
import ru.relex.delivery.services.model.order.UpdatableOrder;

import javax.validation.Valid;
import java.util.List;

public interface OrderFacade {
    CreatedOrder createOrder(@Valid NewOrder order);

    CreatedOrder getOrderById(  long id);
    List<CreatedOrder> getOrders(   );

    boolean deleteOrderById(  long id);

    CreatedOrder updateOrder(  long id, UpdatableOrder updatableOrder);

 }
