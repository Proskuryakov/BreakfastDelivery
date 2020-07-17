package ru.relex.delivery.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.relex.delivery.rest.exception.ObjectNotExistsException;
import ru.relex.delivery.services.facade.UserFacade;
import ru.relex.delivery.services.model.user.ExistingUser;
import ru.relex.delivery.services.model.user.NewUser;
import ru.relex.delivery.services.model.user.UpdatableUser;


@RestController
@RequestMapping(
  value = "/users",
  consumes = "application/json",
  produces = "application/json"
)
public class UserApi {

  private static final Logger logger = LoggerFactory.getLogger(UserApi.class);

  private final UserFacade userFacade;

  @Autowired
  public UserApi(final UserFacade userFacade) {
    this.userFacade = userFacade;
  }


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  ExistingUser createUser(@RequestBody final NewUser user) {
    logger.info("Consumed: {}", user);
    return userFacade.createUser(user);
  }

  @GetMapping(path = "/{id}")
  ExistingUser getById(@PathVariable("id") long id) {

    final var user = userFacade.getById(id);
    if (user == null) {
      throw new ObjectNotExistsException();
    }

    return user;
  }

  @PutMapping(path = "/{id}")
  ExistingUser updateUser(@PathVariable("id") long id,
                          @RequestBody UpdatableUser updatableUser) {
    final var user = userFacade.update(id, updatableUser);

    if (user == null) {
      throw new ObjectNotExistsException();
    }

    return user;
  }

  @DeleteMapping(path = "/{id}")
  void deleteUser(@PathVariable("id") long id) {
    if (userFacade.deleteById(id)) {
      return;
    }

    throw new ObjectNotExistsException();
  }
}
