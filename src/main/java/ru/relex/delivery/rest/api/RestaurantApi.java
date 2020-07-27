package ru.relex.delivery.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.relex.delivery.rest.exception.ObjectNotExistsException;
import ru.relex.delivery.services.facade.RestaurantFacade;
import ru.relex.delivery.services.model.restaurant.CreatedRestaurant;
import ru.relex.delivery.services.model.restaurant.NewRestaurant;
import ru.relex.delivery.services.model.restaurant.UpdatableRestaurant;

@RestController
@RequestMapping(
  value = "/restaurants",
  consumes = "application/json",
  produces = "application/json"
)
public class RestaurantApi {

  private static final Logger logger = LoggerFactory.getLogger(RestaurantApi.class);

  private final RestaurantFacade restaurantFacade;

  @Autowired
  public RestaurantApi(final RestaurantFacade restaurantFacade) {
    this.restaurantFacade = restaurantFacade;
  }


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  CreatedRestaurant createRestaurant(@RequestBody final NewRestaurant restaurant) {
    logger.info("Consumed: {}", restaurant);

    return restaurantFacade.createRestaurant(restaurant);
  }

  @GetMapping(path = "/{restaurantId}")
  CreatedRestaurant getById(@PathVariable("restaurantId") long id){
    final var restaurant = restaurantFacade.getById(id);

    if(restaurant == null){
      logger.error("Get Error. Object with such id does not exist");
      throw new ObjectNotExistsException();
    }

    logger.info("Return {} by id = {}", restaurant, id);
    return restaurant;
  }

  @PutMapping(path = "/{restaurantId}")
  CreatedRestaurant updateRestaurant(
    @PathVariable("restaurantId") long id,
    @RequestBody UpdatableRestaurant updatableRestaurant
  ){
    final var restaurant = restaurantFacade.update(id, updatableRestaurant);

    if(restaurant == null){
      logger.error("Update Error. Object with such id does not exist");
      throw new ObjectNotExistsException();
    }

    logger.info("Updatable: {}", restaurant);
    return restaurant;
  }

  @DeleteMapping(path = "/{restaurantId}")
  void deleteRestaurant(@PathVariable("restaurantId") long id){
    if(restaurantFacade.deleteById(id)){
      logger.info("Delete restaurant by id = {}", id);
      return;
    }
    logger.error("Delete Error. Object with such id does not exist");
    throw new ObjectNotExistsException();
  }

}