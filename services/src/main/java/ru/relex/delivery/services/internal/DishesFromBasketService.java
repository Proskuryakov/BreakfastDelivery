package ru.relex.delivery.services.internal;

import ru.relex.delivery.services.model.dishesFromBasket.BaseDishesFromBasket;
import ru.relex.delivery.services.model.dishesFromBasket.DishesFromBasketIds;
import ru.relex.delivery.services.model.order.CreatedOrder;
import ru.relex.delivery.services.model.order.NewOrder;

import java.util.List;

public interface DishesFromBasketService {
    boolean addDishToBasket(BaseDishesFromBasket order);
    List<BaseDishesFromBasket> getDishesFromBasket(long id);
    boolean deleteDishFromBasket(DishesFromBasketIds ids);
    BaseDishesFromBasket updateDishCount(long user_id, long dish_id, long count);
}
