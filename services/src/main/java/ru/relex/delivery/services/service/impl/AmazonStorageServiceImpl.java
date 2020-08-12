package ru.relex.delivery.services.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.relex.delivery.services.service.StorageService;

import javax.validation.Valid;
import java.io.File;
import java.util.Date;


@Service
@Validated
public class AmazonStorageServiceImpl implements StorageService {

    private final AmazonS3 s3client;

    @Value("${cloud.aws.s3.endpointUrl}")
    private String endpointUrl;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Autowired
    public AmazonStorageServiceImpl(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    @Override
    public String upload(@Valid File file) {
        String fileName = generateFileName(file);
        String fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;

        try {
            s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                    .withCannedAcl(CannedAccessControlList.PublicRead));


        } catch (Exception e) {
            return "File is not uploaded";
        }

        if (!file.delete()) {
            System.err.println("uploaded file not deleted");
        }

        return fileUrl;
    }

    @Override
    public void delete(String url) {
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
    }

    private String generateFileName(File file) {
        return new Date().getTime() + "-" + file.getName().replace(" ", "_");
    }
}
