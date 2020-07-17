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
  value = "/dishes",
  consumes = "application/json",
  produces = "application/json"
)
public class DishApi {

  private static final Logger logger = LoggerFactory.getLogger(DishApi.class);

  private final DishFacade dishFacade;

  @Autowired
  public DishApi(final DishFacade dishFacade) {
    this.dishFacade = dishFacade;
  }


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  CreatedDish createDish(@RequestBody final NewDish dish) {
    logger.info("Consumed: {}", dish);
    return dishFacade.createDish(dish);
  }

  @GetMapping(path = "/{dishId}")
  CreatedDish getById(@PathVariable("dishId") long id) {

    final var dish = dishFacade.getById(id);
    if (dish == null) {
      logger.error("GET request error. Dish with such id does not exist");
      throw new ObjectNotExistsException();
    }
    logger.info("Return {} by id = {}", dish, id);
    return dish;
  }

  @PutMapping(path = "/{dishId}")
  CreatedDish updateDish(@PathVariable("dishId") long id,
                          @RequestBody UpdatableDish updatableDish) {
    final var dish = dishFacade.update(id, updatableDish);

    if (dish == null) {
      logger.error("PUT request error. Dish with such id does not exist");
      throw new ObjectNotExistsException();
    }

    logger.info("Updatable: {}", dish);
    return dish;
  }

  @DeleteMapping(path = "/{dishId}")
  void deleteDish(@PathVariable("dishId") long id) {
    if (dishFacade.deleteById(id)) {
      logger.info("Delete dish by id = {}", id);
      return;
    }
    logger.error("DELETE request Error. Dish with such id does not exist");
    throw new ObjectNotExistsException();
  }
}
