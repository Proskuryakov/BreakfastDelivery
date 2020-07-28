package ru.relex.delivery.services.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.delivery.commons.model.PositionInOrder;
import ru.relex.delivery.commons.model.StatusesOfOrder;
import ru.relex.delivery.db.mapper.OrderMapper;
import ru.relex.delivery.db.model.OrderModel;
import ru.relex.delivery.db.model.PositionInOrderModel;
import ru.relex.delivery.services.internal.OrderService;
import ru.relex.delivery.services.mapper.OrderStruct;
import ru.relex.delivery.services.model.order.CreatedOrder;
import ru.relex.delivery.services.model.order.NewOrder;
import ru.relex.delivery.services.model.order.UpdatableOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderServiceImpl implements OrderService {
    private final Map<Long, CreatedOrder> ORDERS = new ConcurrentHashMap<>();

    private final AtomicLong lastId = new AtomicLong(0);
    private final OrderStruct orderStruct;
    private final OrderMapper orderMapper;
    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper, OrderStruct orderStruct) {
        this.orderMapper = orderMapper;
        this.orderStruct = orderStruct;
    }

    @Override
    public CreatedOrder createOrder(NewOrder order) {
        double check = 0;


        final var model = orderStruct.fromNewOrder(order, 0,1 );
        OrderModel newOrder = orderMapper.createOrder(model);
        long id = newOrder.getId();
        for (int i = 0; i < order.getListOfDishes().size(); i++) {
            orderMapper.addPositionOfOrder(id, order.getListOfDishes().get(i).getDishId(),order.getListOfDishes().get(i).getCount() );
        }


         //по id продукта получим финальную стоимость
//        for (int i = 0; i < order.getListOfDishes().size(); i++) {
//            check +=  order.getListOfDishes().get(i).getCount()*Double.parseDouble(order.getListOfDishes().get(i).getDishId()      ) ;
//        }
        // long newId = lastId.addAndGet(1);
       // CreatedOrder createdOrder = orderMapper.fromNewOrder(order, newId, check);
        //ORDERS.put(newId, createdOrder);
        return orderStruct.toCreatedOrder(model, newOrder.getId(), newOrder.getCreatedAt(), 0, StatusesOfOrder.fromId(newOrder.getStatusId()));
    }

    @Override
    public CreatedOrder getOrderById(long id) {
OrderModel om = orderMapper.getOrder(id);
if(om!= null){
    Integer idstatus = om.getStatusId();
    System.out.println(idstatus);

    List<PositionInOrderModel> positions = orderMapper.getPositionOfOrder(id);
    List<PositionInOrder> createdList = new ArrayList<>();

    //получить
    for (int i = 0; i <positions.size() ; i++) {
        int finalI = i;
        createdList.add(new PositionInOrder() {
            @Override
            public long getDishId() {
                return positions.get(finalI).getDishid();
            }

            @Override
            public long getCount() {
                return positions.get(finalI).getCount();
            }
        });
    }


    return orderStruct.toCreatedOrder(orderMapper.getOrder(id), StatusesOfOrder.fromId(idstatus),createdList );
}else  return orderStruct.toCreatedOrder(orderMapper.getOrder(id) );

    }

//    @Override
//    public CreatedOrder getOrderById(long id) {
//        return orderStruct.toCreatedOrder(orderMapper.findById(id));
//    }

    @Override
    public boolean deleteOrderById(long id) {
        CreatedOrder order = getOrderById(id);
        if (order == null) {
            return false;
        }

        orderMapper.deletePosition(id);
        orderMapper.deleteOrder(id);

        return true;
    }

    @Override
    public CreatedOrder updateOrder(long id, UpdatableOrder updatableOrder) {

        //updateOrder
        CreatedOrder order = getOrderById(id);
        if (order == null) {
            return null;
        }
       Integer ind =  updatableOrder.getStatus().getId();
        OrderModel updmodel = orderMapper.updateOrder(id, updatableOrder.getStatus().getId());

        CreatedOrder updatedOrder = orderStruct.merge(order, updatableOrder );

        return updatedOrder;
    }

//    @Override
//    public CreatedOrder getOrderById(long id) {
//        return ORDERS.get(id);
//    }
//
//    @Override
//    public boolean deleteOrderById(long id) {
//        return ORDERS.remove(id) != null;
//    }
//
//    @Override
//    public CreatedOrder updateOrder(long id, UpdatableOrder updatableOrder) {
//        CreatedOrder order = ORDERS.get(id);
//        if (order == null) {
//            return null;
//        }
//        CreatedOrder updatedOrder = orderMapper.merge(order, updatableOrder);
//        ORDERS.put(id, updatedOrder);
//        return updatedOrder;
//    }
}
