package ru.relex.delivery.rest.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.relex.delivery.rest.exception.ObjectNotExistsException;
import ru.relex.delivery.services.facade.OrderFacade;
import ru.relex.delivery.services.model.order.CreatedOrder;
import ru.relex.delivery.services.model.order.NewOrder;


@RestController
@RequestMapping(
        value = "/orders",
        consumes = "application/json",
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
    @ResponseStatus(HttpStatus.CREATED)
    CreatedOrder createOrder(@RequestBody final NewOrder order) {
        logger.info("Consumed: {}", order);

        return orderFacade.createOrder(order);
    }

    @GetMapping(path="/{id}")
    CreatedOrder getOrderById(@PathVariable("id") long id) {
        final var order = orderFacade.getOrderById(id);
        if (order == null) {
             throw new ObjectNotExistsException();
        }

        return order;
    }

    @DeleteMapping(path="/{id}")
    void deleteOrder(@PathVariable("id") long id) {
        final var order = orderFacade.getOrderById(id);
        System.out.println(id);
        if (order!=null) {
             orderFacade.deleteOrderById(id);
        }
      throw new ObjectNotExistsException();
    }




}
