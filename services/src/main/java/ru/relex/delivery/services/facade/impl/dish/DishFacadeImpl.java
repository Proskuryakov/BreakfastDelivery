package ru.relex.delivery.services.facade.impl.dish;

import ru.relex.delivery.services.facade.DishFacade;
import ru.relex.delivery.services.internal.DishService;
import ru.relex.delivery.services.meta.Facade;
import ru.relex.delivery.services.model.dish.CreatedDish;
import ru.relex.delivery.services.model.dish.NewDish;
import ru.relex.delivery.services.model.dish.UpdatableDish;

import javax.validation.Valid;

@Facade
public class DishFacadeImpl implements DishFacade {

  private final DishService dishService;

  public DishFacadeImpl(final DishService dishService) {
    this.dishService = dishService;
  }

  @Override
  public CreatedDish createDish(@Valid final NewDish dish) {
    return dishService.createDish(dish);
  }

  @Override
  public CreatedDish getById(long id) {
    return dishService.getById(id);
  }

  @Override
  public CreatedDish update(long id, UpdatableDish updatableDish) {
    return dishService.update(id, updatableDish);
  }

  @Override
  public boolean deleteById(long id) {
    return dishService.deleteById(id);
  }
}
