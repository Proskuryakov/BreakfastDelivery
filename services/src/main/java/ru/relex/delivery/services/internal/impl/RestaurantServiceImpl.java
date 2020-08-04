package ru.relex.delivery.services.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.delivery.db.mapper.RestaurantMapper;
import ru.relex.delivery.db.model.RestaurantModel;
import ru.relex.delivery.services.internal.RestaurantService;
import ru.relex.delivery.services.mapper.RestaurantStruct;
import ru.relex.delivery.services.model.restaurant.CreatedRestaurant;
import ru.relex.delivery.services.model.restaurant.NewRestaurant;
import ru.relex.delivery.services.model.restaurant.UpdatableRestaurant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RestaurantServiceImpl implements RestaurantService {

  private final Map<Long, CreatedRestaurant> RESTAURANT = new ConcurrentHashMap<>();

  private final AtomicLong lastId = new AtomicLong(0);

  private final RestaurantStruct restaurantStruct;
  private final RestaurantMapper restaurantMapper;

  @Autowired
  public RestaurantServiceImpl(final RestaurantStruct restaurantStruct, final RestaurantMapper restaurantMapper) {
    this.restaurantStruct = restaurantStruct;
    this.restaurantMapper = restaurantMapper;
  }

  @Override
  public CreatedRestaurant createRestaurant(final NewRestaurant restaurant) {

    final var model = restaurantStruct.fromNewRestaurant(restaurant);

    RestaurantModel newRestaurant = restaurantMapper.createRestaurant(model);
    restaurantMapper.saveRestaurantType(newRestaurant.getId(), restaurant.getRestaurantType());

    return restaurantStruct.toCreatedRestaurant(model, restaurant.getRestaurantType(), newRestaurant.getId());
  }

  @Override
  public CreatedRestaurant getById(long id) {
    return restaurantStruct.toCreatedRestaurant(restaurantMapper.getById(id));
  }

  @Override
  public CreatedRestaurant[] getAll() {
    return restaurantStruct.toCreatedRestaurants(restaurantMapper.getAll());
  }

  @Override
  public CreatedRestaurant update(long id, UpdatableRestaurant updatableRestaurant) {
    CreatedRestaurant restaurant = RESTAURANT.get(id);

    if (restaurant == null)
      return null;

    CreatedRestaurant updateRestaurant = restaurantStruct.merge(restaurant, updatableRestaurant);

    RESTAURANT.put(id, updateRestaurant);

    return updateRestaurant;
  }

  @Override
  public boolean deleteById(long id) {
    return RESTAURANT.remove(id) != null;
  }

}
