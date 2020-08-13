package ru.relex.delivery.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.relex.delivery.rest.exception.ObjectNotExistsException;
import ru.relex.delivery.services.facade.DishFacade;
import ru.relex.delivery.services.model.dish.CreatedDish;
import ru.relex.delivery.services.model.dish.NewDish;
import ru.relex.delivery.services.model.dish.UpdatableDish;

@RestController
@RequestMapping(
        produces = "application/json"
)
public class DishApi {

    private static final Logger logger = LoggerFactory.getLogger(DishApi.class);

    private final DishFacade dishFacade;

    @Autowired
    public DishApi(final DishFacade dishFacade) {
        this.dishFacade = dishFacade;
    }


    @PostMapping(path = "/restaurants/{restaurant_id}/dishes", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    CreatedDish createDish(@RequestBody final NewDish dish,
                           @PathVariable("restaurant_id") long restaurant_id) {
        CreatedDish created = dishFacade.createDish(dish, restaurant_id);
        if(created!=null){
            logger.info("Consumed: {}", dish);
            return created;
        }else {
            return null;
        }
     }

    @GetMapping(path = "/dishes/{id}")
    CreatedDish getById(@PathVariable("id") long id) {

        final var dish = dishFacade.getById(id);
        if (dish == null) {
            logger.error("GET request error. Dish with such id does not exist");
//      throw new ObjectNotExistsException();
            return null;
        }
        logger.info("Return {} by id = {}", dish, id);
        return dish;
    }

    @GetMapping(path = "/restaurants/{restaurantId}/dishes")
    CreatedDish[] getByRestaurantId(@PathVariable("restaurantId") long id) {
        final var dish = dishFacade.getByRestaurantId(id);
        if (dish == null) {
            logger.error("GET request error. Dishes with such restaurant_id does not exist");
            throw new ObjectNotExistsException();
        }
        logger.info("Return {} by id = {}", dish, id);
        return dish;
    }

    @GetMapping(path = "/dishes")
    CreatedDish[] getAll() {

        final var dishes = dishFacade.getAll();
        if (dishes == null) {
            logger.error("GET request error. Dishes do not exist");
            throw new ObjectNotExistsException();
        }
        logger.info("Return {}", dishes);
        return dishes;
    }

    @PutMapping(path = "/dishes/{dishId}", consumes = "application/json")
    CreatedDish updateDish(@PathVariable("dishId") long id,
                           @RequestBody UpdatableDish updatableDish) {
        final var dish = dishFacade.update(id, updatableDish);

        if (dish == null) {
            logger.error("PUT request error. Dish with such id does not exist");
            return null;
        }

        logger.info("Updatable: {}", dish);
        return dish;
    }

    @DeleteMapping(path = "/dishes/{dishId}")
    boolean deleteDish(@PathVariable("dishId") long id) {
        if (dishFacade.deleteById(id)) {
            logger.info("Delete dish by id = {}", id);
            return true;
        }
        logger.error("DELETE request Error. Dish with such id does not exist");
        return false;
    }
}
