package com.aws.cartola.service;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;

@Service
public class S3Service {

    private final S3Client s3Client;
    private final String bucketName = "cartola-dsfr"; // Substitua pelo nome do seu bucket

    @Autowired
    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void uploadFile(String filePath) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(Paths.get(filePath).getFileName().toString())
                .build();

        // Realiza o upload do arquivo
        s3Client.putObject(putObjectRequest, RequestBody.fromFile(Paths.get(filePath)));
        System.out.println("Arquivo enviado com sucesso: " + filePath);
    }
}