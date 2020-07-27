package ru.relex.delivery.services.internal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.relex.delivery.commons.model.DishType;
import ru.relex.delivery.services.internal.impl.DishServiceImpl;
import ru.relex.delivery.services.mapper.DishStruct;
import ru.relex.delivery.services.model.dish.*;

class DishServiceTest {

  private static DishService dishService;

  private static final NewDish newDish = ImmutableNewDish
    .builder()
    .mainDishInfo(ImmutableMainDishInfo.builder()
      .dishName("Lasagna")
      .dishPrice("250")
      .build())
    .dishType(DishType.MAIN)
    .dishCalories(175)
    .dishCookingTimeMinutes(30)
    .build();

  private static final CreatedDish createdDish = ImmutableCreatedDish
    .builder()
    .from(newDish)
    .id(1)
    .build();

//  @BeforeEach
//  void setup() {
//
//    DishStruct mock = Mockito.mock(DishStruct.class);
//    Mockito.when(mock.fromNewDish(
//      Mockito.argThat(a -> !newDish.equals(a)),
//      Mockito.anyLong())
//    )
//      .thenThrow(new RuntimeException());
//    Mockito.when(mock.fromNewDish(newDish, 1)).thenReturn(createdDish);
//    Mockito.when(mock.merge(Mockito.any(), Mockito.any())).thenThrow(new RuntimeException());
//
//    dishService = new DishServiceImpl(mock);
//  }

  @Test
  void checkUserWillBeCreated() {
    Assertions.assertEquals(dishService.createDish(newDish), createdDish);
  }

  @Test
  void checkUserCanBeGet() {
    var createdDish = dishService.createDish(newDish);
    Assertions.assertEquals(dishService.getById(1L), createdDish);
  }

  @Test
  void checkUsersNotExistsAtStart() {
    Assertions.assertNull(dishService.getById(1));
  }

  @Test
  void checkNothingToDeleteBeDeleted() {
    Assertions.assertFalse(dishService.deleteById(1));
  }

  @Test
  void checkWillBeDeleted() {
    dishService.createDish(newDish);
    Assertions.assertTrue(dishService.deleteById(1));
  }

}
