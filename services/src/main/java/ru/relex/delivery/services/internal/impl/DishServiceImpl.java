package ru.relex.delivery.services.internal.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.delivery.services.mapper.DishMapper;
import ru.relex.delivery.services.model.dish.CreatedDish;
import ru.relex.delivery.services.model.dish.NewDish;
import ru.relex.delivery.services.model.dish.UpdatableDish;
import ru.relex.delivery.services.internal.DishService;


@Service
public class DishServiceImpl implements DishService {

  private final Map<Long, CreatedDish> DISHES = new ConcurrentHashMap<>();

  private final AtomicLong lastId = new AtomicLong(0);

  private final DishMapper dishMapper;

  @Autowired
  public DishServiceImpl(final DishMapper dishMapper) {
    this.dishMapper = dishMapper;
  }


  @Override
  public CreatedDish createDish(final NewDish dish) {
    // Выделить ID для пользователя
    long newId = lastId.addAndGet(1);

    // Преобразовать NewUser в ExistingUser
    CreatedDish existingUser = dishMapper.fromNewDish(dish, newId);

    // Сохранить в HashMap
    DISHES.put(newId, existingUser);

    // Вернуть ExistingUser клиенту
    return existingUser;
  }

  @Override
  public CreatedDish getById(long id) {
    return DISHES.get(id);
  }

  @Override
  public CreatedDish update(long id, UpdatableDish updatableDish) {
    CreatedDish dish = DISHES.get(id);

    if (dish == null) {
      return null;
    }

    CreatedDish updatedDish = dishMapper.merge(dish, updatableDish);

    DISHES.put(id, updatedDish);

    return updatedDish;
  }

  @Override
  public boolean deleteById(long id) {
    return DISHES.remove(id) != null;
  }
}
