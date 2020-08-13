package ru.relex.delivery.services.security;

public interface PasswordEncryptor {

    String encode(final String password);
}
