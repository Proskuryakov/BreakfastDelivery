package ru.relex.delivery.services.facade;

import ru.relex.delivery.db.model.RestaurantTypesModel;
import ru.relex.delivery.services.model.restaurant.CreatedRestaurant;
import ru.relex.delivery.services.model.restaurant.NewRestaurant;
import ru.relex.delivery.services.model.restaurant.UpdatableRestaurant;

import javax.validation.Valid;

public interface RestaurantFacade {

  CreatedRestaurant createRestaurant(@Valid NewRestaurant restaurant);

  CreatedRestaurant getById(long id);

  CreatedRestaurant update(long id, UpdatableRestaurant updatableRestaurant);

  boolean deleteById(long id);

  CreatedRestaurant[] getAll();

  RestaurantTypesModel[] getAllTypes();
}
