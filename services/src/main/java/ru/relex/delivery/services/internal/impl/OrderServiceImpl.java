package ru.relex.delivery.services.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.delivery.commons.model.PositionInOrder;
import ru.relex.delivery.commons.model.StatusesOfOrder;
import ru.relex.delivery.db.mapper.DishMapper;
import ru.relex.delivery.db.mapper.OrderMapper;
import ru.relex.delivery.db.model.DishModel;
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
    private final DishMapper dishMapper;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper, OrderStruct orderStruct, DishMapper dishMapper) {
        this.orderMapper = orderMapper;
        this.orderStruct = orderStruct;
        this.dishMapper = dishMapper;
    }

    @Override
    public CreatedOrder createOrder(NewOrder order) {
        long check = 0;

        for (int i = 0; i < order.getListOfDishes().size(); i++) {
            DishModel dim = dishMapper.findById(order.getListOfDishes().get(i).getDishId());
            if (dim != null) {
                check += order.getListOfDishes().get(i).getCount() * dim.getDishPrice();
            }
        }

        final var model = orderStruct.fromNewOrder(order, (int)check, 1);
        OrderModel newOrder = orderMapper.createOrder(model);
        long id = newOrder.getId();
        for (int i = 0; i < order.getListOfDishes().size(); i++) {

            orderMapper.addPositionOfOrder(id, order.getListOfDishes().get(i).getDishId(), order.getListOfDishes().get(i).getCount());
        }


        //по id продукта получим финальную стоимость
//        for (int i = 0; i < order.getListOfDishes().size(); i++) {
//            check +=  order.getListOfDishes().get(i).getCount()*Double.parseDouble(order.getListOfDishes().get(i).getDishId()      ) ;
//        }
        // long newId = lastId.addAndGet(1);
        // CreatedOrder createdOrder = orderMapper.fromNewOrder(order, newId, check);
        //ORDERS.put(newId, createdOrder);
        return orderStruct.toCreatedOrder(model, newOrder.getId(), newOrder.getCreatedAt(),  (int)check, StatusesOfOrder.fromId(newOrder.getStatusId()));
    }

    @Override
    public CreatedOrder getOrderByOrderId(long id) {
        OrderModel om = orderMapper.getOrderByOrderId(id);
        if (om != null) {
            Integer idstatus = om.getStatusId();
            List<PositionInOrderModel> positions = orderMapper.getPositionOfOrder(id);
            List<PositionInOrder> createdList = new ArrayList<>();

            //получить
            for (int i = 0; i < positions.size(); i++) {
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
            return orderStruct.toCreatedOrder(om, StatusesOfOrder.fromId(idstatus), createdList);
        } else return orderStruct.toCreatedOrder(om);

    }


    @Override
    public boolean deleteOrderById(long id) {
        CreatedOrder order = getOrderByOrderId(id);
        if (order == null) {
            return false;
        }

        orderMapper.deletePosition(id);
        orderMapper.deleteOrder(id);

        return true;
    }

    @Override
    public CreatedOrder updateOrder(long id, UpdatableOrder updatableOrder) {
        CreatedOrder order = getOrderByOrderId(id);
        if (order == null) {
            return null;
        }

        Integer ind = updatableOrder.getStatus().getId();
        OrderModel updmodel = orderMapper.updateOrder(id, updatableOrder.getStatus().getId());
        CreatedOrder updatedOrder = orderStruct.merge(order, updatableOrder);

        return updatedOrder;
    }

    @Override
    public List<CreatedOrder> getOrders() {
        List<OrderModel> om = orderMapper.getOrders();
        if (om != null) {
            List<CreatedOrder> createdOrders = new ArrayList<>();
            List<PositionInOrder> createdList = new ArrayList<>();
            for (int i = 0; i < om.size(); i++) {
                Integer idstatus = om.get(i).getStatusId();
                List<PositionInOrderModel> positions = orderMapper.getPositionOfOrder(om.get(i).getId());
                List<PositionInOrder> createdListPos = new ArrayList<>();
                for (int j = 0; j < positions.size(); j++) {
                    int finalI = j;
                    createdListPos.add(new PositionInOrder() {
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
                createdOrders.add(orderStruct.toCreatedOrder(om.get(i), StatusesOfOrder.fromId(idstatus), createdListPos));
            }
            return createdOrders;
        } else return null;
    }

    @Override
    public List<Integer> getCountOrdersByStatus() {

        List<OrderModel> om1 = orderMapper.getCountOrdersByOrderStatus(StatusesOfOrder.ORDER_IN_PROCESSING.getId());
        List<OrderModel> om2 = orderMapper.getCountOrdersByOrderStatus(StatusesOfOrder.ORDER_PREPARING.getId());
        List<OrderModel> om3 = orderMapper.getCountOrdersByOrderStatus(StatusesOfOrder.ORDER_DELIVERY.getId());
        List<OrderModel> om4 = orderMapper.getCountOrdersByOrderStatus(StatusesOfOrder.ORDER_DELIVERED.getId());
        List<OrderModel> om5 = orderMapper.getCountOrdersByOrderStatus(StatusesOfOrder.ORDER_CANCELLED.getId());
        List<Integer> resList = new ArrayList<>();
        resList.add(om1.size());
        resList.add(om2.size());
        resList.add(om3.size());
        resList.add(om4.size());
        resList.add(om5.size());

        return resList;
    }

    @Override
    public CreatedOrder getOrderByUserId(long user_id) {
        OrderModel om = orderMapper.getOrderByUserId(user_id);
        if (om != null) {
            long order_id = om.getId();
            Integer idstatus = om.getStatusId();

            List<PositionInOrderModel> positions = orderMapper.getPositionOfOrder(order_id);
            List<PositionInOrder> createdList = new ArrayList<>();

            for (int i = 0; i < positions.size(); i++) {
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
            return orderStruct.toCreatedOrder(om, StatusesOfOrder.fromId(idstatus), createdList);
        } else return orderStruct.toCreatedOrder(om);
    }
}
