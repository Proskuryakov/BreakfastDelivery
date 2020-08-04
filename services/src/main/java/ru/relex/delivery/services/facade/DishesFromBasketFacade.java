package ru.relex.delivery.services.facade;

import ru.relex.delivery.services.model.dishesFromBasket.BaseDishesFromBasket;
import ru.relex.delivery.services.model.dishesFromBasket.DishesFromBasketIds;
import ru.relex.delivery.services.model.order.CreatedOrder;
import ru.relex.delivery.services.model.order.NewOrder;

import javax.validation.Valid;
import java.util.List;

public interface DishesFromBasketFacade {
    boolean addDishToBasket(@Valid BaseDishesFromBasket dish);
    boolean deleteDishFromBasket(DishesFromBasketIds ids );
    BaseDishesFromBasket updateDishCount(long user_id, long dish_id, long count);

    List<BaseDishesFromBasket> getDishesById(long id);
}
