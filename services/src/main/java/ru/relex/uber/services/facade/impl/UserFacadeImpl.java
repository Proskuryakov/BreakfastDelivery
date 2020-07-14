package ru.relex.uber.services.facade.impl;

import javax.validation.Valid;
import ru.relex.uber.services.facade.UserFacade;
import ru.relex.uber.services.internal.UserService;
import ru.relex.uber.services.meta.Facade;
import ru.relex.uber.services.model.ExistingUser;
import ru.relex.uber.services.model.NewUser;
import ru.relex.uber.services.model.UpdatableUser;

@Facade
public class UserFacadeImpl implements UserFacade {

  private final UserService userService;

  public UserFacadeImpl(final UserService userService) {
    this.userService = userService;
  }

  @Override
  public ExistingUser createUser(@Valid final NewUser user) {
    return userService.createUser(user);
  }

  @Override
  public ExistingUser getById(long id) {
    return userService.getById(id);
  }

  @Override
  public ExistingUser update(long id, UpdatableUser updatableUser) {
    return userService.update(id, updatableUser);
  }

  @Override
  public boolean deleteById(long id) {
    return userService.deleteById(id);
  }
}
