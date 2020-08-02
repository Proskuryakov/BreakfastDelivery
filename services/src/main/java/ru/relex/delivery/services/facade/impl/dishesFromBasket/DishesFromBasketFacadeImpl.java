package ru.relex.delivery.services.facade.impl.dishesFromBasket;

import ru.relex.delivery.services.facade.DishesFromBasketFacade;
import ru.relex.delivery.services.internal.DishesFromBasketService;
import ru.relex.delivery.services.meta.Facade;
import ru.relex.delivery.services.model.dishesFromBasket.BaseDishesFromBasket;

import javax.validation.Valid;
import java.util.List;
@Facade
public class DishesFromBasketFacadeImpl implements DishesFromBasketFacade {

    private final DishesFromBasketService dishesFromBasketService;

    public DishesFromBasketFacadeImpl(DishesFromBasketService dishesFromBasketService) {
        this.dishesFromBasketService = dishesFromBasketService;
    }

    @Override
    public void addDishToBasket(@Valid BaseDishesFromBasket dish) {
        dishesFromBasketService.addDishToBasket(dish);
    }

    @Override
    public boolean deleteDishFromBasket(long user_id, long dish_id, long count) {
        return dishesFromBasketService.deleteDishFromBasket(user_id, dish_id, count);
    }

    @Override
    public List<BaseDishesFromBasket> getDishesById(long id) {
        return   dishesFromBasketService.getDishesFromBasket(id);
    }
}
