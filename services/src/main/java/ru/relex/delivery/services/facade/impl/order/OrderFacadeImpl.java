package ru.relex.delivery.services.facade.impl.order;

import org.springframework.transaction.annotation.Transactional;
import ru.relex.delivery.commons.model.StatusesOfOrder;
import ru.relex.delivery.services.facade.OrderFacade;
import ru.relex.delivery.services.internal.OrderService;
import ru.relex.delivery.services.meta.Facade;
import ru.relex.delivery.services.model.order.CreatedOrder;
import ru.relex.delivery.services.model.order.NewOrder;
import ru.relex.delivery.services.model.order.UpdatableOrder;

import javax.validation.Valid;
import java.util.List;

@Facade

public class OrderFacadeImpl implements OrderFacade {

    private final OrderService orderService;

    public OrderFacadeImpl(final OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    @Transactional
    public CreatedOrder createOrder( @Valid final NewOrder order) {
        return orderService.createOrder(order);
    }

    @Override
    public CreatedOrder getOrderByUserId(long id) {
        return orderService.getOrderByUserId(id);
    }

    @Override
    public CreatedOrder getOrderByOrderId(long id) {
        return orderService.getOrderByOrderId(id);
    }

    @Override
    public List<CreatedOrder> getOrders() {
        return orderService.getOrders();
    }

    @Override
    public boolean deleteOrderById(  long id) {
        return orderService.deleteOrderById(id);
    }

    @Override
    public CreatedOrder updateOrder(  long id, UpdatableOrder updatableOrder) {
        return orderService.updateOrder(id, updatableOrder);
    }

    @Override
    public List<Integer> getCountOrdersByStatus( ) {
        return orderService.getCountOrdersByStatus( );
    }
}


