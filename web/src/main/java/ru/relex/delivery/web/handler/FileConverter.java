package ru.relex.delivery.web.handler;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileConverter {
    public File convertMultiPartToFile(MultipartFile file) {
        if (check(file)) {
            try {
                File convFile = new File(file.getOriginalFilename());
                FileOutputStream fos = new FileOutputStream(convFile);
                fos.write(file.getBytes());
                fos.close();
                return convFile;
            } catch (IOException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    private boolean check(MultipartFile file) {
        return file != null;
    }
}
