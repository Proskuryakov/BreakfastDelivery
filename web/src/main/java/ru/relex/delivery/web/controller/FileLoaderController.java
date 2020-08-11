package ru.relex.delivery.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.relex.delivery.services.service.StorageService;
import ru.relex.delivery.web.handler.FileConverter;

import javax.servlet.MultipartConfigElement;


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

    @PostMapping(path = "/upload" )
    public String uploadFile(@RequestPart(value = "file")  MultipartFile  file) {
        System.out.println(file);
        return storageService.upload(fileConverter.convertMultiPartToFile(file));
    }

    @DeleteMapping(path = "/delete")
    public void deleteFile(@RequestPart(value = "url") String url) {
        storageService.delete(url);
    }
}
