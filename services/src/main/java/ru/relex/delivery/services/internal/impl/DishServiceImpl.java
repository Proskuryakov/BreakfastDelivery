package ru.relex.delivery.services.internal.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.delivery.commons.model.DishType;
import ru.relex.delivery.db.mapper.DishMapper;
import ru.relex.delivery.db.model.DishModel;
import ru.relex.delivery.db.model.UpdatableDishModel;
import ru.relex.delivery.services.mapper.DishStruct;
import ru.relex.delivery.services.model.dish.CreatedDish;
import ru.relex.delivery.services.model.dish.ImmutableCreatedDish;
import ru.relex.delivery.services.model.dish.NewDish;
import ru.relex.delivery.services.model.dish.UpdatableDish;
import ru.relex.delivery.services.internal.DishService;
import ru.relex.delivery.services.model.order.CreatedOrder;


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
    public CreatedDish[] getByRestaurantId(long id) {
        return dishStruct.toCreatedDishesByRestaurantId(dishMapper.findByRestaurantId(id));
    }

    public UpdatableDishModel toUpdatableDishModel(UpdatableDish model, CreatedDish cDish) {
        if (model == null) {
            return null;
        } else {
            UpdatableDishModel updatableDishModel = new UpdatableDishModel();
            if (model != null) {

                if (model.getDishName() != null) {
                    updatableDishModel.setDishName(model.getDishName());
                } else {
                    updatableDishModel.setDishName(cDish.getMainDishInfo().getDishName());
                }
                if (model.getDishPrice() != null) {
                    updatableDishModel.setDishPrice(model.getDishPrice());
                } else {
                    updatableDishModel.setDishPrice(Integer.parseInt(cDish.getMainDishInfo().getDishPrice()));
                }
            }
            if (model.getDishType() != null) {
                updatableDishModel.setDishType(model.getDishType());
            } else {
                updatableDishModel.setDishType(cDish.getDishType());

            }
            if (model.getDishCalories() != null) {
                updatableDishModel.setDishCalories(model.getDishCalories());
            } else {
                updatableDishModel.setDishCalories(cDish.getDishCalories());

            }
            if (model.getDishCookingTimeMinutes() != null) {
                updatableDishModel.setDishCookingTimeMinutes(model.getDishCookingTimeMinutes());
            } else {
                updatableDishModel.setDishCookingTimeMinutes(cDish.getDishCookingTimeMinutes());

            }
            if (model.getDishImage() != null) {
                updatableDishModel.setDishImage(model.getDishImage());
            } else {
                updatableDishModel.setDishImage(cDish.getDishImage());

            }
            updatableDishModel.setDishId(cDish.getId());
            updatableDishModel.setRestaurantId(cDish.getRestaurantId());

            return updatableDishModel;
        }
    }


    @Override
    public CreatedDish[] getAll() {
        return dishStruct.toCreatedDishes(dishMapper.findAll());
    }

    @Override
    public CreatedDish update(long id, UpdatableDish updatableDish) {
        CreatedDish dsh = getById(id);
        if (dsh != null) {
            UpdatableDishModel newUpdatableDishModel = toUpdatableDishModel(updatableDish, dsh);
            DishModel dishModel = dishMapper.updateDishById(newUpdatableDishModel);
            Integer idType = newUpdatableDishModel.getDishType().getId();
            dishMapper.updateDishType(id, idType);
            CreatedDish createdDish = dishStruct.toCreatedDish(dishModel, dishModel.getId(), dsh.getRestaurantId(), DishType.fromId(idType));

            return createdDish;
        } else return null;
    }

    @Override
    public boolean deleteById(long id) {
        CreatedDish dish = getById(id);
        if (dish == null) {
            return false;
        }
        dishMapper.deleteDishById(id);


        return true;
    }
}
