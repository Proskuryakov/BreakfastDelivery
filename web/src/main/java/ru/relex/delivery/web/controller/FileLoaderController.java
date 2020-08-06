package ru.relex.delivery.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.relex.delivery.services.service.StorageService;
import ru.relex.delivery.web.handler.FileConverter;


@RestController
@RequestMapping(path = "/storage")
public class FileLoaderController {

    private final StorageService storageService;
    private final FileConverter fileConverter;

    @Autowired
    public FileLoaderController(StorageService storageService, FileConverter fileConverter) {
        this.storageService = storageService;
        this.fileConverter = fileConverter;
    }

    @PostMapping(path = "/upload")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return storageService.upload(fileConverter.convertMultiPartToFile(file));
    }

    @DeleteMapping(path = "/delete")
    public void deleteFile(@RequestPart(value = "url") String url) {
        storageService.delete(url);
    }
}
