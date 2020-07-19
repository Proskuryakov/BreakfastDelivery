package ru.relex.delivery.services.facade.impl.order;

import ru.relex.delivery.services.facade.OrderFacade;
import ru.relex.delivery.services.internal.OrderService;
import ru.relex.delivery.services.meta.Facade;
import ru.relex.delivery.services.model.order.CreatedOrder;
import ru.relex.delivery.services.model.order.NewOrder;
import ru.relex.delivery.services.model.order.UpdatableOrder;

import javax.validation.Valid;

@Facade

public class OrderFacadeImpl implements OrderFacade {

    private final OrderService orderService;

    public OrderFacadeImpl(final OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CreatedOrder createOrder( @Valid final NewOrder order) {
        return orderService.createOrder(order);
    }

    @Override
    public CreatedOrder getOrderById(  long id) {
        return orderService.getOrderById(id);
    }

    @Override
    public boolean deleteOrderById(  long id) {
        return orderService.deleteOrderById(id);
    }

    @Override
    public CreatedOrder updateOrder(  long id, UpdatableOrder updatableOrder) {
        return orderService.updateOrder(id, updatableOrder);
    }
}


