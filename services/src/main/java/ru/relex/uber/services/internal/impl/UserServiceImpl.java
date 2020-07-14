package ru.relex.uber.services.internal.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.uber.services.internal.UserService;
import ru.relex.uber.services.mapper.UserMapper;
import ru.relex.uber.services.model.ExistingUser;
import ru.relex.uber.services.model.NewUser;
import ru.relex.uber.services.model.UpdatableUser;

@Service
public class UserServiceImpl implements UserService {

  private final Map<Long, ExistingUser> USERS = new ConcurrentHashMap<>();

  private final AtomicLong lastId = new AtomicLong(0);

  private final UserMapper userMapper;

  @Autowired
  public UserServiceImpl(final UserMapper userMapper) {
    this.userMapper = userMapper;
  }


  @Override
  public ExistingUser createUser(final NewUser user) {
    // Выделить ID для пользователя
    long newId = lastId.addAndGet(1);

    // Преобразовать NewUser в ExistingUser
    ExistingUser existingUser = userMapper.fromNewUser(user, newId);

    // Сохранить в HashMap
    USERS.put(newId, existingUser);

    // Вернуть ExistingUser клиенту
    return existingUser;
  }

  @Override
  public ExistingUser getById(long id) {
    return USERS.get(id);
  }

  @Override
  public ExistingUser update(long id, UpdatableUser updatableUser) {
    ExistingUser user = USERS.get(id);

    if (user == null) {
      return null;
    }

    ExistingUser updatedUser = userMapper.merge(user, updatableUser);

    USERS.put(id, updatedUser);

    return updatedUser;
  }

  @Override
  public boolean deleteById(long id) {
    return USERS.remove(id) != null;
  }
}
