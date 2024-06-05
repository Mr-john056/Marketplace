package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;

import java.io.IOException;

public interface ImageService {
    Image saveImage(MultipartFile imageFile) throws IOException;

    Image getImage(Integer imageId);

    Image updateImage(MultipartFile image, Integer imageId) throws IOException;

    byte[] getByteFromFile(String path) throws IOException;
}
