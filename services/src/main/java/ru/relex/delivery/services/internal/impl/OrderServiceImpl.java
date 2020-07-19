package ru.relex.delivery.services.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.delivery.services.internal.OrderService;
import ru.relex.delivery.services.mapper.OrderMapper;
import ru.relex.delivery.services.model.order.CreatedOrder;
import ru.relex.delivery.services.model.order.NewOrder;
import ru.relex.delivery.services.model.order.UpdatableOrder;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderServiceImpl implements OrderService {
    private final Map<Long, CreatedOrder> ORDERS = new ConcurrentHashMap<>();

    private final AtomicLong lastId = new AtomicLong(0);

    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(final OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public CreatedOrder createOrder(NewOrder order) {
        double check = 0;
        for (int i = 0; i < order.getListOfDishes().size(); i++) {
            check +=  order.getListOfDishes().get(i).getCount()  *  Double.parseDouble(order.getListOfDishes().get(i).getDishInfo().getMainDishInfo().getDishPrice()) ;
        }
        //double check = 1000;
        long newId = lastId.addAndGet(1);
        CreatedOrder createdOrder = orderMapper.fromNewOrder(order, newId, check);
        ORDERS.put(newId, createdOrder);
        return createdOrder;
    }

    @Override
    public CreatedOrder getOrderById(long id) {
        return ORDERS.get(id);
    }

    @Override
    public boolean deleteOrderById(long id) {
        return ORDERS.remove(id) != null;
    }

    @Override
    public CreatedOrder updateOrder(long id, UpdatableOrder updatableOrder) {
        CreatedOrder order = ORDERS.get(id);
        if (order == null) {
            return null;
        }
        CreatedOrder updatedOrder = orderMapper.merge(order, updatableOrder);
        ORDERS.put(id, updatedOrder);
        return updatedOrder;
    }
}
