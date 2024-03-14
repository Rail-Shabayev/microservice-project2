package org.rail.project.controller;

import lombok.RequiredArgsConstructor;
import org.rail.project.model.ProductImage;
import org.rail.project.model.ResponseData;
import org.rail.project.service.ImageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam("file") MultipartFile file) {
        ProductImage productImage = imageService.saveFile(file);
        String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(String.valueOf(productImage.getId()))
                .toUriString();
        return new ResponseData(productImage.getFileName(),
                downloadUri,
                file.getContentType(),
                file.getSize());
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long fileId) throws Exception {
        ProductImage productImage = imageService.getFile(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(productImage.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "productImage; filename=\"" + productImage.getFileName() + "\"")
                .body(new ByteArrayResource(productImage.getData()));
    }
}
