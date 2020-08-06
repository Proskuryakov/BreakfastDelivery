package ru.relex.delivery.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.relex.delivery.rest.exception.ObjectNotExistsException;
import ru.relex.delivery.services.facade.DishesFromBasketFacade;
import ru.relex.delivery.services.model.dishesFromBasket.BaseDishesFromBasket;
import ru.relex.delivery.services.model.dishesFromBasket.DishesFromBasketIds;

import java.util.List;

@RestController
@RequestMapping(
        value = "/dishesFromBasket",
        produces = "application/json"
)
public class DishesFromBasketApi {
    private static final Logger logger = LoggerFactory.getLogger(OrdersApi.class);

    private final DishesFromBasketFacade dishesFromBasketFacade;

    @Autowired
    public DishesFromBasketApi(final DishesFromBasketFacade dishesFromBasketFacade) {
        this.dishesFromBasketFacade = dishesFromBasketFacade;
    }

    @DeleteMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    boolean deleteOrderByUserIdDishId(@RequestBody final DishesFromBasketIds ids) {
        if (dishesFromBasketFacade.deleteDishFromBasketByUserIdDishId(ids)) {
            logger.info("Order successful delete");
            return true;
        }
        logger.error("Delete Error.  ");
        throw new ObjectNotExistsException();

    }

    @DeleteMapping(
            value = "/{userId}"
    )
    boolean deleteOrderByUserId(@PathVariable("userId") long userId) {
        if (dishesFromBasketFacade.deleteDishesFromBasketByUserId(userId)) {
            logger.info("Order successful delete");
            return true;
        }
        logger.error("Delete Error.  ");
        return false;
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    BaseDishesFromBasket updateDishCount(@RequestBody final BaseDishesFromBasket dish) {
        final var updDish = dishesFromBasketFacade.updateDishCount(dish.getUserId(), dish.getDishId(), dish.getCount());

        if (updDish != null) {
            logger.info("Order successful update");
            logger.info("Consumed: {}", updDish);
            return updDish;
        }
        logger.error("Update Error.  ");
        throw new ObjectNotExistsException();
    }

    @PostMapping(consumes = "application/json")

    @ResponseStatus(HttpStatus.CREATED)
    boolean addDishToBasket(@RequestBody final BaseDishesFromBasket dish) {
        logger.info("Consumed: {}", dish);
        dishesFromBasketFacade.addDishToBasket(dish);
        return true;
    }

    @GetMapping
    @RequestMapping(
            value = "/{userId}"
    )
    List<BaseDishesFromBasket> getDishesByUserId(@PathVariable("userId") long userId) {
        final var dishes = dishesFromBasketFacade.getDishesById(userId);
        if (dishes == null) {
            logger.error("Basket is empty");
            throw new ObjectNotExistsException();
        }
        return dishes;
    }

    @GetMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    BaseDishesFromBasket getDishByUserIdDishId(@RequestBody final DishesFromBasketIds ids) {
        final var dish = dishesFromBasketFacade.getDishByUserIdDishId(ids);

        if (dish!=null) {
            logger.info("Order successful get");
            return dish;
        }
        logger.error(" Get error  .  ");
        return null;
      //  throw new ObjectNotExistsException();

    }
}
