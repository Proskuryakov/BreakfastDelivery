package ru.relex.delivery.services.service;

import javax.validation.Valid;
import java.io.File;

public interface StorageService {
    String upload(@Valid File file);

    void delete(String url);
}
