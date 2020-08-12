package ru.relex.delivery.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.relex.delivery.db.model.DishesFromBasketModel;
import ru.relex.delivery.db.model.OrderModel;
import ru.relex.delivery.services.model.dishesFromBasket.BaseDishesFromBasket;
import ru.relex.delivery.services.model.order.CreatedOrder;

@Mapper
public interface DishesFromBasketStruct {
    @Mapping(target = "count", source = "dishModel.count")
    @Mapping(target = "dishId", source = "dishModel.dishId")
    @Mapping(target = "userId", source = "dishModel.userId")
    BaseDishesFromBasket toBaseDishesFromBasket(DishesFromBasketModel dishModel);
}
