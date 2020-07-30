package ru.relex.delivery.services.internal.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.delivery.db.mapper.DishMapper;
import ru.relex.delivery.db.model.DishModel;
import ru.relex.delivery.services.mapper.DishStruct;
import ru.relex.delivery.services.model.dish.CreatedDish;
import ru.relex.delivery.services.model.dish.NewDish;
import ru.relex.delivery.services.model.dish.UpdatableDish;
import ru.relex.delivery.services.internal.DishService;


@Service
public class DishServiceImpl implements DishService {

  private final Map<Long, CreatedDish> DISHES = new ConcurrentHashMap<>();

  private final DishStruct dishStruct;

  private final DishMapper dishMapper;

  @Autowired
  public DishServiceImpl(final DishStruct dishStruct,
                         final DishMapper dishMapper) {
    this.dishStruct = dishStruct;
    this.dishMapper = dishMapper;
  }

  @Override
  public CreatedDish createDish(final NewDish dish, long restaurantId) {
    // Преобразовать NewUser в UserModel
    final var model = dishStruct.fromNewDish(dish, restaurantId);

    DishModel newDish = dishMapper.createDish(model);
    dishMapper.saveDishType(newDish.getId(), dish.getDishType());

    // Сохранить в HashMap
    //USERS.put(newId, existingUser);

    // Вернуть ExistingUser клиенту
    return dishStruct.toCreatedDish(model, newDish.getId());
  }

  @Override
  public CreatedDish getById(long id) {
    return dishStruct.toCreatedDish(dishMapper.findById(id));
  }

  @Override
  public CreatedDish[] getAll() {
    return dishStruct.toCreatedDishes(dishMapper.findAll());
  }

  @Override
  public CreatedDish update(long id, UpdatableDish updatableDish) {
    CreatedDish dish = DISHES.get(id);

    if (dish == null) {
      return null;
    }

    CreatedDish updatedDish = dishStruct.merge(dish, updatableDish);

    DISHES.put(id, updatedDish);

    return updatedDish;
  }

  @Override
  public boolean deleteById(long id) {
    return DISHES.remove(id) != null;
  }
}
