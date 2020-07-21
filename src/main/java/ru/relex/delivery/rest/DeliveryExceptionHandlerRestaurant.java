package ru.relex.delivery.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.relex.delivery.rest.exception.ObjectNotExistsException;
import ru.relex.delivery.rest.model.ErrorModel;
import ru.relex.delivery.services.validation.ValidationErrorsRestaurant;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@ControllerAdvice
public class DeliveryExceptionHandlerRestaurant {

  @ExceptionHandler(ObjectNotExistsException.class)
  ResponseEntity<?> handleNotFoundException(ObjectNotExistsException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  @ExceptionHandler(ConstraintViolationException.class)
  ResponseEntity<List<ErrorModel>> handleConstraintViolationException(ConstraintViolationException e) {
    final Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
    final var errorModels = errors.stream()
      .map(err -> new ErrorModel(err.getMessage(), ValidationErrorsRestaurant.getMessageByCode(err.getMessage())))
      .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModels);
  }

}
