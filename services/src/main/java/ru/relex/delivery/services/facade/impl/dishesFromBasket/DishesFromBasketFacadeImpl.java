package ru.relex.delivery.services.facade.impl.dishesFromBasket;

import ru.relex.delivery.services.facade.DishesFromBasketFacade;
import ru.relex.delivery.services.internal.DishesFromBasketService;
import ru.relex.delivery.services.meta.Facade;
import ru.relex.delivery.services.model.dishesFromBasket.BaseDishesFromBasket;
import ru.relex.delivery.services.model.dishesFromBasket.DishesFromBasketIds;

import javax.validation.Valid;
import java.util.List;
@Facade
public class DishesFromBasketFacadeImpl implements DishesFromBasketFacade {

    private final DishesFromBasketService dishesFromBasketService;

    public DishesFromBasketFacadeImpl(DishesFromBasketService dishesFromBasketService) {
        this.dishesFromBasketService = dishesFromBasketService;
    }

    @Override
    public boolean addDishToBasket(@Valid BaseDishesFromBasket dish) {
      return   dishesFromBasketService.addDishToBasket(dish);
    }

    @Override
    public boolean deleteDishFromBasketByUserIdDishId(DishesFromBasketIds ids ) {
        return dishesFromBasketService.deleteDishFromBasketByUserIdDishId(ids);
    }

    @Override
    public boolean deleteDishesFromBasketByUserId(long user_id) {
        return dishesFromBasketService.deleteDishesFromBasketByUserId(user_id);
    }

    @Override
    public BaseDishesFromBasket updateDishCount(long user_id, long dish_id, long count) {
        return dishesFromBasketService.updateDishCount(user_id, dish_id, count);
    }

    @Override
    public List<BaseDishesFromBasket> getDishesById(long id) {
        return   dishesFromBasketService.getDishesFromBasket(id);
    }
}
