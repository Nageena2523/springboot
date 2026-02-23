package com.tejait.batch15.controller;

import java.io.IOException;
import java.nio.file.*;

import java.nio.file.Path;
import java.nio.file.Paths;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("file")
public class FilesController {

    private static final String UPLOAD_DIR =
            "C:\\Users\\shaik\\OneDrive\\Pictures";

    @PostMapping("uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file");
        }

        try {

            // Normalize path
            Path uploadPath = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath);

            // Clean filename (prevent path traversal)
            String fileName = Paths.get(file.getOriginalFilename())
                    .getFileName()
                    .toString();

            Path targetLocation = uploadPath.resolve(fileName);

            Files.copy(file.getInputStream(),
                    targetLocation,
                    StandardCopyOption.REPLACE_EXISTING);

            return ResponseEntity.ok("Upload Success: " + targetLocation);

        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Upload Failed: " + e.getMessage());
        }
    }
    @GetMapping("download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {

        try {
            Path filePath = Paths.get("C:\\Users\\shaik\\OneDrive\\Pictures")
                    .toAbsolutePath()
                    .normalize()
                    .resolve(fileName)
                    .normalize();

            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

