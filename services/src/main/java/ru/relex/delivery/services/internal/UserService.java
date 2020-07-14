package ru.relex.delivery.services.internal;

import ru.relex.delivery.services.model.ExistingUser;
import ru.relex.delivery.services.model.NewUser;
import ru.relex.delivery.services.model.UpdatableUser;

public interface UserService {

  ExistingUser createUser(NewUser user);

  ExistingUser getById(long id);

  ExistingUser update(long id, UpdatableUser updatableUser);

  boolean deleteById(long id);
}
