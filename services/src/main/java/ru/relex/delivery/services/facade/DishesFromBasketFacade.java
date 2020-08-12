package ru.relex.delivery.services.facade;

import ru.relex.delivery.services.model.dishesFromBasket.BaseDishesFromBasket;
import ru.relex.delivery.services.model.dishesFromBasket.DishesFromBasketIds;
import ru.relex.delivery.services.model.order.CreatedOrder;
import ru.relex.delivery.services.model.order.NewOrder;

import javax.validation.Valid;
import java.util.List;

public interface DishesFromBasketFacade {
    boolean addDishToBasket(@Valid BaseDishesFromBasket dish);
    boolean deleteDishFromBasketByUserIdDishId(DishesFromBasketIds ids );
    boolean deleteDishesFromBasketByUserId(long user_id);

    BaseDishesFromBasket updateDishCount(long user_id, long dish_id, long count);

    List<BaseDishesFromBasket> getDishesById(long id);

    BaseDishesFromBasket getDishByUserIdDishId(long userId,long dishId);
}
