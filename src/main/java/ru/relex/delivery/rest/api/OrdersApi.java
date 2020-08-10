package ru.relex.delivery.rest.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.relex.delivery.commons.model.StatusesOfOrder;
import ru.relex.delivery.rest.exception.ObjectNotExistsException;
import ru.relex.delivery.services.facade.OrderFacade;
import ru.relex.delivery.services.model.order.CreatedOrder;
import ru.relex.delivery.services.model.order.NewOrder;
import ru.relex.delivery.services.model.order.UpdatableOrder;

import java.util.List;


@RestController
@RequestMapping(
        value = "/orders",
        produces = "application/json"
)
public class OrdersApi {

    private static final Logger logger = LoggerFactory.getLogger(OrdersApi.class);

    private final OrderFacade orderFacade;

    @Autowired
    public OrdersApi(final OrderFacade userFacade) {
        this.orderFacade = userFacade;
    }

    @PostMapping
    @RequestMapping(
            consumes = "application/json"
    )
    @ResponseStatus(HttpStatus.CREATED)
    CreatedOrder createOrder(@RequestBody final NewOrder order) {
        logger.info("Consumed: {}", order);
        return orderFacade.createOrder(order);
    }

    @GetMapping
    @RequestMapping(
            value = "/{id}"
    )
    CreatedOrder getOrderByOrderId(@PathVariable("id") long id) {

        final var order = orderFacade.getOrderByOrderId(id);
        if (order == null) {
            logger.error("Order with such id does not exist");
            throw new ObjectNotExistsException();
        }
        logger.info("order with such id successfully get");
        return order;
    }
    @GetMapping
    @RequestMapping(
            value = "/byUserId/{id}"
    )
    CreatedOrder getOrderByUserId(@PathVariable("id") long id) {
         final var order = orderFacade.getOrderByUserId(id);
        if (order == null) {
            logger.error("Order with such id does not exist");
            throw new ObjectNotExistsException();
        }
        return order;
    }
    @GetMapping
    @RequestMapping(
            value = "/analysisOfOrders"
    )
    List<Integer> getCountOrdersByStatus() {
         final var order = orderFacade.getCountOrdersByStatus();
        return order;
    }

    @GetMapping
    List<CreatedOrder> getOrders() {
        final var order = orderFacade.getOrders();
        if (order == null) {
            logger.error("Order with such id does not exist");
            throw new ObjectNotExistsException();
        }
        logger.info("orders successfully get");
        return order;
    }

    @DeleteMapping(path = "/{id}")
    boolean deleteOrder(@PathVariable("id") long id) {
        if (orderFacade.deleteOrderById(id)) {
            logger.info("Order successful delete");
            return true;
        }
        logger.error("Delete Error. Order with such id does not exist");
       // throw new ObjectNotExistsException();
        return false;
    }

    @PutMapping
    @RequestMapping(
            value = "/{id}",
            consumes = "application/json"
    )
    CreatedOrder updateOrder(@PathVariable("id") long id,
                             @RequestBody UpdatableOrder updatableOrder) {
         final var order = orderFacade.updateOrder(id, updatableOrder);
        if (order == null) {
            logger.error("Update Error. Order with such id does not exist");
            throw new ObjectNotExistsException();
        }
        logger.info("Order successful update");
        return order;
    }


}
