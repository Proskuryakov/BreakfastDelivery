package ru.relex.delivery.db.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;
import ru.relex.delivery.commons.model.DishType;
import ru.relex.delivery.db.model.DishModel;

@Mapper
public interface DishMapper {

  DishModel createDish(DishModel dish);

  //language=PostgreSQL
  @Select("" +
    "SELECT u.dish_id AS id, " +
    "       dish_name," +
    "       dish_price," +
    "       dish_calories," +
    "       dish_cooking_time_minutes," +
    "       dish_type_id AS dish_type " +
    "FROM dishes u " +
    "INNER JOIN dish_types r ON u.dish_id = r.dish_type_id " +
    "WHERE u.dish_id = #{id}"
  )
  DishModel findById(@Param("id") long id);

  void saveDishType(@Param("dishId") long dishId, @Param("dishType") DishType dishType);
}
