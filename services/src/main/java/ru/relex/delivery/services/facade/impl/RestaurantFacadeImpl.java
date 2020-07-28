package ru.relex.delivery.services.facade.impl;

import ru.relex.delivery.services.facade.RestaurantFacade;
import ru.relex.delivery.services.internal.RestaurantService;
import ru.relex.delivery.services.meta.Facade;
import ru.relex.delivery.services.model.restaurant.CreatedRestaurant;
import ru.relex.delivery.services.model.restaurant.NewRestaurant;
import ru.relex.delivery.services.model.restaurant.UpdatableRestaurant;

import javax.validation.Valid;

@Facade
public class RestaurantFacadeImpl implements RestaurantFacade {

  private final RestaurantService restaurantService;

  public RestaurantFacadeImpl(final RestaurantService restaurantService) {
    this.restaurantService = restaurantService;
  }

  @Override
  public CreatedRestaurant createRestaurant(@Valid final NewRestaurant restaurant) {
    return restaurantService.createRestaurant(restaurant);
  }

  @Override
  public CreatedRestaurant getById(long id) {
    return restaurantService.getById(id);
  }

  @Override
  public CreatedRestaurant update(long id, UpdatableRestaurant updatableRestaurant) {
    return restaurantService.update(id, updatableRestaurant);
  }

  @Override
  public boolean deleteById(long id) {
    return restaurantService.deleteById(id);
  }
}
