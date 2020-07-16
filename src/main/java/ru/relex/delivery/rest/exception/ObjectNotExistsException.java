package ru.relex.delivery.rest.exception;

public class ObjectNotExistsException extends RuntimeException {

    public ObjectNotExistsException() {
        super(null, null, false, false);
    }

}
