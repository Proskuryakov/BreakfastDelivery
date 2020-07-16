package ru.relex.delivery.services.facade;

import ru.relex.delivery.services.model.order.CreatedOrder;
import ru.relex.delivery.services.model.order.NewOrder;
import ru.relex.delivery.services.model.order.UpdatableOrder;

import javax.validation.Valid;

public interface OrderFacade {
    CreatedOrder createOrder(@Valid NewOrder order);

    CreatedOrder getOrderById(@Valid  long id);

    boolean deleteOrderById(@Valid  long id);

    CreatedOrder updateOrder(@Valid  long id, UpdatableOrder updatableOrder);

 }
