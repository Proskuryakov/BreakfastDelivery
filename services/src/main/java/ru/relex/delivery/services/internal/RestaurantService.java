package ru.relex.delivery.services.internal;

import ru.relex.delivery.services.model.restaurant.CreatedRestaurant;
import ru.relex.delivery.services.model.restaurant.NewRestaurant;
import ru.relex.delivery.services.model.restaurant.UpdatableRestaurant;

public interface RestaurantService {

  CreatedRestaurant createRestaurant(NewRestaurant restaurant);

  CreatedRestaurant getById(long id);

  CreatedRestaurant[] getAll();

  CreatedRestaurant update(long id, UpdatableRestaurant updatableRestaurant);

  boolean deleteById(long id);

}
