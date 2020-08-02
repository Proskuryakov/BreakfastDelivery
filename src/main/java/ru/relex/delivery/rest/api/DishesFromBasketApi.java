package ru.relex.delivery.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.relex.delivery.rest.exception.ObjectNotExistsException;
import ru.relex.delivery.services.facade.DishesFromBasketFacade;
import ru.relex.delivery.services.model.dishesFromBasket.BaseDishesFromBasket;
import ru.relex.delivery.services.model.order.CreatedOrder;

import java.util.List;

@RestController
@RequestMapping(
        value = "/dishesFromBasket",
        consumes = "application/json",
        produces = "application/json"
)
public class DishesFromBasketApi {
    private static final Logger logger = LoggerFactory.getLogger(OrdersApi.class);

    private final DishesFromBasketFacade dishesFromBasketFacade;

    @Autowired
    public DishesFromBasketApi(final DishesFromBasketFacade dishesFromBasketFacade) {
        this.dishesFromBasketFacade = dishesFromBasketFacade;
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.CREATED)
    void deleteOrder(@RequestBody final BaseDishesFromBasket dish) {
        if (dishesFromBasketFacade.deleteDishFromBasket(dish.getUserId() , dish.getDishId(), dish.getCount())) {
            logger.error("Order successful delete");

            return;
        }
        logger.error("Delete Error.  ");
        throw new ObjectNotExistsException();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void addDishToBasket(@RequestBody final BaseDishesFromBasket dish) {
        logger.info("Consumed: {}", dish);

         dishesFromBasketFacade.addDishToBasket(dish);
    }

    @GetMapping(path="/{userId}")
    List<BaseDishesFromBasket> getDishesById(@PathVariable("userId") long userId) {
        final var dishes = dishesFromBasketFacade.getDishesById(userId);
        if (dishes == null|| dishes.isEmpty()) {
            logger.error("Basket is empty");
            throw new ObjectNotExistsException();
        }

        return dishes;
    }


}
