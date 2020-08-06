package ru.relex.delivery.services.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.delivery.db.mapper.DishesFromBasketMapper;
import ru.relex.delivery.db.model.DishesFromBasketModel;
import ru.relex.delivery.services.internal.DishesFromBasketService;
import ru.relex.delivery.services.mapper.DishesFromBasketStruct;
import ru.relex.delivery.services.model.dishesFromBasket.BaseDishesFromBasket;
import ru.relex.delivery.services.model.dishesFromBasket.DishesFromBasketIds;

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
        List<BaseDishesFromBasket> currList = new ArrayList<>();

        if (om != null) {
            for (int i = 0; i < om.size(); i++) {
                currList.add(dishesFromBasketStruct.toBaseDishesFromBasket(om.get(i)));
            }
            return currList;
        } else return currList;
    }

    @Override
    public boolean deleteDishFromBasketByUserIdDishId(DishesFromBasketIds ids) {
        DishesFromBasketModel dish = dishesFromBasketMapper.getDishFromUserIdDishId(ids.getUserId(), ids.getDishId());
        if (dish == null) {
            return false;
        }
        dishesFromBasketMapper.deleteDishFromBasketBuUserIdDishId(ids.getUserId(), ids.getDishId());

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

    @Override
    public boolean deleteDishesFromBasketByUserId(long user_id) {
        List<DishesFromBasketModel> dish = dishesFromBasketMapper.getDishesFromBasket(user_id);
        if (dish == null|| dish.isEmpty()) {
            return false;
        }
        dishesFromBasketMapper.deleteDishesFromBasketByUserId(user_id);

        return true;
    }

    @Override
    public BaseDishesFromBasket getDishByUserIdDishId(long userId,long dishId) {
        DishesFromBasketModel dish = dishesFromBasketMapper.getDishFromUserIdDishId(userId, dishId);

        if (dish != null) {
            return dishesFromBasketStruct.toBaseDishesFromBasket(dish);
        } else return null;
    }
}
