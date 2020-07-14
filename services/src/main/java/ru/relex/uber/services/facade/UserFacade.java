package ru.relex.uber.services.facade;

import javax.validation.Valid;
import ru.relex.uber.services.model.ExistingUser;
import ru.relex.uber.services.model.NewUser;
import ru.relex.uber.services.model.UpdatableUser;

public interface UserFacade {

  ExistingUser createUser(@Valid NewUser user);

  ExistingUser getById(long id);

  ExistingUser update(long id, UpdatableUser updatableUser);

  boolean deleteById(long id);
}
