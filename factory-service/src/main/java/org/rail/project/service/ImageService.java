package org.rail.project.service;

import lombok.RequiredArgsConstructor;
import org.rail.project.model.ProductImage;
import org.rail.project.repository.ImageRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "image")
public class ImageService {

    private final ImageRepository imageRepository;

    public ProductImage saveFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if (fileName.contains("..")) {
                throw new RuntimeException(fileName);
            }
            ProductImage productImage = new ProductImage(1L,
                    fileName,
                    file.getContentType(),
                    file.getBytes());
            return imageRepository.save(productImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @CachePut
    public ProductImage getFile(Long fileId) throws Exception {
        return imageRepository
                .findById(fileId)
                .orElseThrow(() -> new Exception("File not found"));
    }
}
