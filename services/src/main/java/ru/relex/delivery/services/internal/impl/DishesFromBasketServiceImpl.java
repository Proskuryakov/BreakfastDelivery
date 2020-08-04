package ru.relex.delivery.services.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.delivery.commons.model.PositionInOrder;
import ru.relex.delivery.commons.model.StatusesOfOrder;
import ru.relex.delivery.db.mapper.DishesFromBasketMapper;
import ru.relex.delivery.db.model.DishesFromBasketModel;
import ru.relex.delivery.db.model.OrderModel;
import ru.relex.delivery.db.model.PositionInOrderModel;
import ru.relex.delivery.services.internal.DishesFromBasketService;
import ru.relex.delivery.services.mapper.DishesFromBasketStruct;
import ru.relex.delivery.services.model.dishesFromBasket.BaseDishesFromBasket;
import ru.relex.delivery.services.model.dishesFromBasket.DishesFromBasketIds;
import ru.relex.delivery.services.model.order.CreatedOrder;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishesFromBasketServiceImpl implements DishesFromBasketService {
    private final DishesFromBasketStruct dishesFromBasketStruct;
    private final DishesFromBasketMapper dishesFromBasketMapper;

    @Autowired

    public DishesFromBasketServiceImpl(DishesFromBasketStruct dishesFromBasketStruct, DishesFromBasketMapper dishesFromBasketMapper) {
        this.dishesFromBasketStruct = dishesFromBasketStruct;
        this.dishesFromBasketMapper = dishesFromBasketMapper;
    }

    @Override
    public boolean addDishToBasket(BaseDishesFromBasket dish) {
        dishesFromBasketMapper.addDishToBasket(dish.getUserId(), dish.getDishId(), dish.getCount());
        return true;
    }

    @Override
    public List<BaseDishesFromBasket> getDishesFromBasket(long id) {
        List<DishesFromBasketModel> om = dishesFromBasketMapper.getDishesFromBasket(id);
        List<DishesFromBasketModel> updateDishList = new ArrayList<>();
        List<Long> dishsesId = new ArrayList<>();
        List<BaseDishesFromBasket> currList = new ArrayList<>();

        if (om != null) {
            for (int i = 0; i < om.size(); i++) {
                int finalI = i;

                long dishId = om.get(i).getDishId();
                if (!dishsesId.contains(dishId)) {
                    dishsesId.add(dishId);
                    long dishCount = 0;
                    for (int j = 0; j < om.size(); j++) {
                        if (dishId == om.get(j).getDishId()) {
                            dishCount += om.get(j).getCount();
                            dishesFromBasketMapper.deleteDishFromBasketIndex(om.get(j).getResId());
                        }
                    }
                    dishesFromBasketMapper.addDishToBasket(om.get(0).getUserId(), dishId, dishCount);
                    long finalDishCount = dishCount;
                    currList.add(new BaseDishesFromBasket() {

                        @Override
                        public long getUserId() {
                            return om.get(0).getUserId();
                        }

                        @Override
                        public long getDishId() {
                            return dishId;
                        }

                        @Override
                        public long getCount() {
                            return finalDishCount;
                        }
                    });
                }
            }


            return currList;
        } else return null;
    }

    @Override
    public boolean deleteDishFromBasket(DishesFromBasketIds ids) {
        DishesFromBasketModel dish = dishesFromBasketMapper.getDishFromUserIdDishId(ids.getUserId(), ids.getDishId());
        if (dish == null) {
            return false;
        }
        dishesFromBasketMapper.deleteDishFromBasket(ids.getUserId(), ids.getDishId());

        return true;
    }

    @Override
    public BaseDishesFromBasket updateDishCount(long user_id, long dish_id, long count) {
        DishesFromBasketModel dish = dishesFromBasketMapper.getDishFromUserIdDishId(user_id, dish_id);
        if (dish == null) {
            return null;
        }
        dishesFromBasketMapper.updateDishCount(user_id, dish_id, count);
        DishesFromBasketModel updatableDishModel = dishesFromBasketMapper.getDishFromUserIdDishId(user_id, dish_id);
        return dishesFromBasketStruct.toBaseDishesFromBasket(updatableDishModel);
    }
}
