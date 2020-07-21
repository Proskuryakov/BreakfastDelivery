package ru.relex.delivery.services.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.delivery.services.internal.RestaurantService;
import ru.relex.delivery.services.mapper.RestaurantMapper;
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

  private final RestaurantMapper restaurantMapper;

  @Autowired
  public RestaurantServiceImpl(final RestaurantMapper restaurantMapper) {
    this.restaurantMapper = restaurantMapper;
  }


  @Override
  public CreatedRestaurant createRestaurant(final NewRestaurant restaurant) {
    long newId = lastId.addAndGet(1);

    CreatedRestaurant createdRestaurant = restaurantMapper.fromNewRestaurant(restaurant, newId);

    RESTAURANT.put(newId, createdRestaurant);

    return createdRestaurant;
  }

  @Override
  public CreatedRestaurant getById(long id) {
    return RESTAURANT.get(id);
  }

  @Override
  public CreatedRestaurant update(long id, UpdatableRestaurant updatableRestaurant) {
    CreatedRestaurant restaurant = RESTAURANT.get(id);

    if (restaurant == null)
      return null;

    CreatedRestaurant updateRestaurant = restaurantMapper.merge(restaurant, updatableRestaurant);

    RESTAURANT.put(id, updateRestaurant);

    return updateRestaurant;
  }

  @Override
  public boolean deleteById(long id) {
    return RESTAURANT.remove(id) != null;
  }

}
