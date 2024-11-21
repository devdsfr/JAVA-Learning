package com.aws.cartola.controller;

import com.aws.cartola.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/s3")
public class S3Controller {

    private final S3Service s3Service;

    @Autowired
    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("filePath") String filePath) {
        s3Service.uploadFile(filePath);
        return ResponseEntity.ok("Arquivo enviado com sucesso!");
    }
}