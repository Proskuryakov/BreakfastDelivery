package ru.relex.delivery.services.facade;

import javax.validation.Valid;
import ru.relex.delivery.services.model.ExistingUser;
import ru.relex.delivery.services.model.NewUser;
import ru.relex.delivery.services.model.UpdatableUser;

public interface UserFacade {

  ExistingUser createUser(@Valid NewUser user);

  ExistingUser getById(long id);

  ExistingUser update(long id, UpdatableUser updatableUser);

  boolean deleteById(long id);
}
